package com.shareshipping.utils.workflowEngine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowTask;
import com.shareshipping.utils.workflowEngine.enums.NodeTypeEnum;
import com.shareshipping.utils.workflowEngine.exceptions.InvalidWorkflowStructure;

public class WorkflowExecutor<T, C extends IWorkflowContext> implements Callable<T>, Supplier<T> {

	private final T returnType;
	private final C context;
	private final String startFromNode;
	private final HashMap<String, WorkflowInfo> nodes;
	private final HashMap<Class<? extends Throwable>, String> errorHandlerMap;
	private String endNodeId;
	private final ExecutorService parallelExecutor = Executors.newCachedThreadPool();

	public WorkflowExecutor(HashMap<String, WorkflowInfo> nodes,
							HashMap<Class<? extends Throwable>, String> errorHandlerMap,
							String startFromNode,
							T returnType,
							C context) {

		this(nodes, errorHandlerMap, startFromNode, null, returnType, context);
	}

	public WorkflowExecutor(HashMap<String, WorkflowInfo> nodes,
							HashMap<Class<? extends Throwable>, String> errorHandlerMap,
							String startNodeId,
							String endNodeId,
							T returnType,
							C context) {

		this.nodes = nodes;
		this.errorHandlerMap = errorHandlerMap;
		this.startFromNode = startNodeId;
		this.endNodeId = endNodeId;
		this.returnType = returnType;
		this.context = context;
	}

	private void runWorkflow() {

		String currentNode = startFromNode;
		while (StringUtils.isNotBlank(currentNode)
				&& (StringUtils.isNotBlank(endNodeId) ? !currentNode.equals(endNodeId) : true)) {

			WorkflowInfo nodeInfo = nodes.get(currentNode);

			@SuppressWarnings("unchecked")
			IWorkflowTask<T, C> instance = (IWorkflowTask<T, C>) nodeInfo.getInstance();

			ICompletationToken token = new CompletationToken();

			instance.setContext(context);
			instance.setReturnValue(returnType);

			try {
				instance.process(token);

				if (nodeInfo.getNodeType().equals(NodeTypeEnum.FORK_ELEMENT)) {

					final List<Callable<T>> parallelFlows = Lists.newArrayList();
					Optional<Entry<String, WorkflowInfo>> joinElement = nodes.entrySet().stream()
							.filter(entry -> entry.getValue().nodeType.equals(NodeTypeEnum.JOIN_ELEMENT)).findFirst();

					if (joinElement.isPresent()) {

						String joinElementId = joinElement.get().getKey();

						nodeInfo.exitFlows.entrySet().stream().forEach(entry -> {

							parallelFlows.add(new WorkflowExecutor<T, C>(nodes, errorHandlerMap, entry
									.getValue(), joinElementId, returnType, context));

						});

						parallelExecutor.invokeAll(parallelFlows);

						currentNode = joinElementId;

					} else {
						throw new InvalidWorkflowStructure("No join element found for the current workflow. Fork/Join element must coexist");
					}

				} else {
					int flow = token.getFlow();
					if (nodeInfo.exitFlows.containsKey(flow)) {
						currentNode = nodeInfo.exitFlows.get(flow);
					}
				}

			} catch (Throwable e) {

				boolean found = false;

				for (Map.Entry<Class<? extends Throwable>, String> handledException : errorHandlerMap.entrySet()) {
					Class<? extends Throwable> exception = handledException.getKey();
					if (exception.isAssignableFrom(e.getClass()) && e.getClass().isAssignableFrom(exception)) {
						currentNode = handledException.getValue();
						found = true;
						break;
					}
				}

				if (!found) {
					for (Map.Entry<Class<? extends Throwable>, String> handledException : errorHandlerMap.entrySet()) {
						Class<? extends Throwable> exception = handledException.getKey();
						if (exception.isAssignableFrom(e.getClass())) {
							currentNode = handledException.getValue();
							break;
						}
					}
				}

			}

		}
	}

	@Override
	public T call() {
		runWorkflow();
		return returnType;
	}

	@Override
	public T get() {
		runWorkflow();
		return returnType;
	}

}
