package com.shareshipping.utils.workflowEngine.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowTask;
import com.shareshipping.utils.workflowEngine.annotations.BooleanGateway;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.annotations.Gateway;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.exceptions.InvalidWorkflowStructure;
import com.shareshipping.utils.workflowEngine.utils.WorkflowUtils;

public abstract class Workflow<T, C extends IWorkflowContext> implements IWorkflow<T, C> {

	private final ExecutorService workflowExecutorService;
	private Collection<Class<? extends WorkflowTask<T, C>>> workflowNodes;
	private final HashMap<String, IWorkflowTask<T, C>> idToInstanceNode;
	private final HashMap<String, Map<Integer, String>> idToNextNodeId;

	private Class<? extends WorkflowTask<T, C>> startNode;
	private Class<? extends WorkflowTask<T, C>> endNode;

	private final Injector injector;
	private String startNodeId;
	private String endNodeId;

	@Inject
	public Workflow(Injector injector) {
		this.injector = injector;
		this.idToInstanceNode = Maps.newHashMap();
		this.idToNextNodeId = Maps.newHashMap();
		this.workflowExecutorService = Executors.newCachedThreadPool();
	};

	public abstract Collection<Class<? extends WorkflowTask<T, C>>> nodes();

	@PostConstruct
	public void configureModule() throws InvalidWorkflowStructure {
		workflowNodes = nodes();

		initializeNodeIdMap();

		startNode = Iterables.getFirst(getStartNode(), null);
		if (startNode == null) {
			throw new InvalidWorkflowStructure("No start node defined");
		}
		StartElement startAnnotation = startNode.getDeclaredAnnotationsByType(StartElement.class)[0];
		startNodeId = startAnnotation.id();

		endNode = Iterables.getFirst(getEndNode(), null);
		if (endNode == null) {
			throw new InvalidWorkflowStructure("No end node defined");
		}
		EndElement endAnnotation = endNode.getDeclaredAnnotationsByType(EndElement.class)[0];
		endNodeId = endAnnotation.id();
	}

	@Override
	public CompletableFuture<T> executeAsync() {

		T returnVal = injector.getInstance(withReturnType());
		C context = injector.getInstance(withContext());

		return CompletableFuture.supplyAsync(new WorkflowExecutor<T, C>(idToInstanceNode, idToNextNodeId, startNodeId,
				endNodeId, returnVal, context));

	}

	@Override
	public T execute() {

		T returnVal = injector.getInstance(withReturnType());
		C context = injector.getInstance(withContext());

		try {

			T x = workflowExecutorService.submit(new WorkflowExecutor<T, C>(idToInstanceNode, idToNextNodeId,
					startNodeId, endNodeId, returnVal, context)).get();

			return x;

		} catch (InterruptedException | ExecutionException e) {
			return null;
		}

	}

	private void initializeNodeIdMap() {
		workflowNodes.stream().forEach((node) -> {
			Annotation[] annotations = node.getDeclaredAnnotations();

			Arrays.asList(annotations).stream().filter(a -> WorkflowUtils.isAWorkflowEngineAnnotation(a))
					.forEach((ann) -> {

						String id = "";
						Map<Integer, String> toMap = Maps.newHashMap();

						if (ann instanceof BooleanGateway) {
							BooleanGateway bgAnn = (BooleanGateway) ann;
							id = bgAnn.id();
							toMap.put(0, bgAnn.noFlow());
							toMap.put(1, bgAnn.yesFlow());
						} else if (ann instanceof EndElement) {
							EndElement eeAnn = (EndElement) ann;
							id = eeAnn.id();
							toMap.put(0, null);
						} else if (ann instanceof ErrorHandler) {
							ErrorHandler ehAnn = (ErrorHandler) ann;
							id = ehAnn.id();
							toMap.put(0, null);
						} else if (ann instanceof Gateway) {
							Gateway gAnn = (Gateway) ann;
							toMap.put(0, gAnn.flow1());
							toMap.put(1, gAnn.flow2());
							toMap.put(2, gAnn.flow3());
							toMap.put(3, gAnn.flow4());
							toMap.put(4, gAnn.flow5());
						} else if (ann instanceof StartElement) {
							StartElement stAnn = (StartElement) ann;
							id = stAnn.id();
							toMap.put(0, stAnn.to());
						} else if (ann instanceof UserTaskElement) {
							UserTaskElement utAnn = (UserTaskElement) ann;
							id = utAnn.id();
							toMap.put(0, utAnn.to());
						}

						idToInstanceNode.put(id, injector.getInstance(node));
						idToNextNodeId.put(id, toMap);
					});

		});

	}

	private Collection<Class<? extends WorkflowTask<T, C>>> getEndNode() {
		return getNodeByAnnotation(EndElement.class);
	}

	private Collection<Class<? extends WorkflowTask<T, C>>> getStartNode() {
		return getNodeByAnnotation(StartElement.class);
	}

	private Collection<Class<? extends WorkflowTask<T, C>>> getNodeByAnnotation(Class<? extends Annotation> cls) {
		return workflowNodes.stream().filter(c -> c.getDeclaredAnnotationsByType(cls).length == 1)
				.collect(Collectors.toList());

	}

}
