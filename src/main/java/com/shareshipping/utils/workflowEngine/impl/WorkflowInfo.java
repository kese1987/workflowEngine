package com.shareshipping.utils.workflowEngine.impl;

import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.shareshipping.utils.workflowEngine.IWorkflowTask;
import com.shareshipping.utils.workflowEngine.enums.NodeTypeEnum;

public class WorkflowInfo {

	IWorkflowTask<?, ?> instance;
	NodeTypeEnum nodeType;
	String nodeId;
	BiMap<Integer, String> exitFlows = HashBiMap.create();

	public void addExitFlow(Integer flowId, String nodeId) {
		exitFlows.put(flowId, nodeId);
	}

	public IWorkflowTask<?, ?> getInstance() {
		return instance;
	}

	public void setInstance(IWorkflowTask<?, ?> instance) {
		this.instance = instance;
	}

	public NodeTypeEnum getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeTypeEnum nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public BiMap<Integer, String> getExitFlows() {
		return exitFlows;
	}

	public void setExitFlows(Map<Integer, String> exitFlows) {
		this.exitFlows = HashBiMap.create(exitFlows);
	}

}
