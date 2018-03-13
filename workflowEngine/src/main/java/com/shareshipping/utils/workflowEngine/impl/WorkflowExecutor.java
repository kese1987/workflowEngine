package com.shareshipping.utils.workflowEngine.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowTask;

public class WorkflowExecutor<T, C extends IWorkflowContext> implements Callable<T>, Supplier<T> {

	private final T returnType;
	private final C context;
	private final String startFromNode;
	private final HashMap<String, WorkflowInfo> nodes;
	private final HashMap<Class<? extends Throwable>, String> errorHandlerMap;

	public WorkflowExecutor(HashMap<String, WorkflowInfo> nodes,
			HashMap<Class<? extends Throwable>, String> errorHandlerMap, String startFromNode, T returnType,
			C context) {

		this.nodes = nodes;
		this.errorHandlerMap = errorHandlerMap;
		this.startFromNode = startFromNode;
		this.returnType = returnType;
		this.context = context;
	}

	private void runWorkflow() {

		String currentNode = startFromNode;
		while (StringUtils.isNotBlank(currentNode)) {

			WorkflowInfo nodeInfo = nodes.get(currentNode);

			@SuppressWarnings("unchecked")
			IWorkflowTask<T, C> instance = (IWorkflowTask<T, C>) nodeInfo.getInstance();

			ICompletationToken token = new CompletationToken();

			instance.setContext(context);
			instance.setReturnValue(returnType);

			try {
				instance.process(token);

				int flow = token.getFlow();
				if (nodeInfo.exitFlows.containsKey(flow)) {
					currentNode = nodeInfo.exitFlows.get(flow);
				}

			} catch (Throwable e) {

				for (Map.Entry<Class<? extends Throwable>, String> handledException : errorHandlerMap.entrySet()) {
					Class<? extends Throwable> exception = handledException.getKey();
					if (exception.isAssignableFrom(e.getClass()) && e.getClass().isAssignableFrom(exception)) {
						currentNode = handledException.getValue();
						break;
					}
				}

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
