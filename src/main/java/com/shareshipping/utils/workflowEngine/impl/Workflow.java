package com.shareshipping.utils.workflowEngine.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.annotations.BooleanGateway;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.annotations.ForkElement;
import com.shareshipping.utils.workflowEngine.annotations.Gateway;
import com.shareshipping.utils.workflowEngine.annotations.JoinElement;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.enums.NodeTypeEnum;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.utils.WorkflowUtils;

public abstract class Workflow<T, C extends IWorkflowContext> implements IWorkflow<T, C> {

	private final ExecutorService workflowExecutorService;

	private static final HashMap<String, WorkflowInfo> workflowInfo = Maps.newHashMap();
	private static final HashMap<Class<? extends Throwable>, String> errorHandlerMap = Maps.newHashMap();

	private final Injector injector;
	private final IGrapher grapher;

	private static String startNodeId = "";

	@Inject
	public Workflow(Injector injector,
					IGrapher grapher) {
		this.injector = injector;
		this.grapher = grapher;
		this.workflowExecutorService = Executors.newCachedThreadPool();
	};

	@Override
	public abstract Collection<Class<? extends WorkflowTask<T, C>>> nodes();

	@PostConstruct
	public void initializeWorkflow() {
		initializeNodeIdMap();
		grapher.draw(	workflowInfo,
						"");
	}

	@Override
	public CompletableFuture<T> executeAsync() {

		T returnVal = injector.getInstance(withReturnType());
		C context = injector.getInstance(withContext());

		return CompletableFuture
				.supplyAsync(new WorkflowExecutor<T, C>(workflowInfo, errorHandlerMap, startNodeId, returnVal, context));

	}

	@Override
	public T execute() {

		T returnVal = injector.getInstance(withReturnType());
		C context = injector.getInstance(withContext());

		try {

			return workflowExecutorService
					.submit(new WorkflowExecutor<T, C>(workflowInfo, errorHandlerMap, startNodeId, returnVal, context))
					.get();

		} catch (InterruptedException | ExecutionException e) {
			return null;
		}

	}

	private void initializeNodeIdMap() {
		nodes().stream().forEach((node) -> {
			Annotation[] annotations = node.getDeclaredAnnotations();

			Arrays.asList(annotations).stream().filter(a -> WorkflowUtils.isAWorkflowEngineAnnotation(a))
					.forEach((ann) -> {

						WorkflowInfo infoNode = new WorkflowInfo();
						WorkflowTask<T, C> instanceNode = injector.getInstance(node);
						infoNode.setInstance(instanceNode);

						if (ann instanceof BooleanGateway) {
							BooleanGateway bgAnn = (BooleanGateway) ann;
							infoNode.setNodeId(bgAnn.id());
							infoNode.setNodeType(NodeTypeEnum.BOOLEAN_GATEWAY_ELEMENT);
							infoNode.addExitFlow(	0,
													bgAnn.noFlow());
							infoNode.addExitFlow(	1,
													bgAnn.yesFlow());
						} else if (ann instanceof EndElement) {
							EndElement eeAnn = (EndElement) ann;
							infoNode.setNodeId(eeAnn.id());
							infoNode.setNodeType(NodeTypeEnum.END_ELEMENT);
							infoNode.addExitFlow(	0,
													null);
						} else if (ann instanceof ErrorHandler) {
							ErrorHandler ehAnn = (ErrorHandler) ann;
							errorHandlerMap.put(ehAnn.exceptionClass(),
												ehAnn.id());
							infoNode.setNodeId(ehAnn.id());
							infoNode.setNodeType(NodeTypeEnum.ERROR_HANDLER_ELEMENT);
							infoNode.addExitFlow(	0,
													ehAnn.to());
						} else if (ann instanceof Gateway) {
							Gateway gAnn = (Gateway) ann;
							infoNode.setNodeId(gAnn.id());
							infoNode.setNodeType(NodeTypeEnum.GATEWAY_ELEMENT);
							int flowId = 0;
							for (String flow : gAnn.flows()) {
								infoNode.addExitFlow(	flowId++,
														flow);
							}
						} else if (ann instanceof StartElement) {
							StartElement stAnn = (StartElement) ann;
							startNodeId = stAnn.id();
							infoNode.setNodeId(stAnn.id());
							infoNode.setNodeType(NodeTypeEnum.START_ELEMENT);
							infoNode.addExitFlow(	0,
													stAnn.to());
						} else if (ann instanceof UserTaskElement) {
							UserTaskElement utAnn = (UserTaskElement) ann;
							infoNode.setNodeId(utAnn.id());
							infoNode.setNodeType(NodeTypeEnum.USER_TASK_ELEMENT);
							infoNode.addExitFlow(	0,
													utAnn.to());
						} else if (ann instanceof ForkElement) {
							ForkElement forkAnn = (ForkElement) ann;
							infoNode.setNodeId(forkAnn.id());
							infoNode.setNodeType(NodeTypeEnum.FORK_ELEMENT);
							int flowId = 0;
							for (String flow : forkAnn.parallelFlows()) {
								infoNode.addExitFlow(	flowId++,
														flow);
							}
						} else if (ann instanceof JoinElement) {
							JoinElement jAnn = (JoinElement) ann;
							infoNode.setNodeId(jAnn.id());
							infoNode.setNodeType(NodeTypeEnum.JOIN_ELEMENT);
							infoNode.addExitFlow(	0,
													jAnn.to());
						}

						workflowInfo.put(	infoNode.nodeId,
											infoNode);

					});

		});

	}

}
