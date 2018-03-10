package com.shareshipping.utils.workflowEngine.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowStage;

public class WorkflowExecutor<T, C extends IWorkflowContext> implements Callable<T>, Supplier<T> {

	private final T returnType;
	private final C context;
	private final HashMap<String, IWorkflowStage<T, C>> nodes;
	private final String startNodeId;
	private final String endNodeId;

	private String currentNodeId;
	private HashMap<String, Map<Integer, String>> idToNextNodeId;

	public WorkflowExecutor(HashMap<String, IWorkflowStage<T, C>> nodes,
			HashMap<String, Map<Integer, String>> idToNextNodeId, String startNode, String endNode, T returnType,
			C context) {

		this.nodes = nodes;
		this.idToNextNodeId = idToNextNodeId;
		this.startNodeId = startNode;
		this.endNodeId = endNode;
		this.returnType = returnType;
		this.context = context;
	}

	private void runWorkflow() {

		String currentNode = startNodeId;
		while (StringUtils.isNotBlank(currentNode)) {

			IWorkflowStage<T, C> instance = nodes.get(currentNode);
			ICompletationToken token = new CompletationToken();

			instance.setContext(context);
			instance.setReturnValue(returnType);

			instance.process(token);

			if (!token.isErrored()) {

				int flow = token.getFlow();
				if (idToNextNodeId.containsKey(currentNode)) {

					Map<Integer, String> fromFlowToIdMap = idToNextNodeId.get(currentNode);
					currentNode = fromFlowToIdMap.get(flow);

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
