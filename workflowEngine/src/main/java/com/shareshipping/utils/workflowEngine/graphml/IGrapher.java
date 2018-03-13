package com.shareshipping.utils.workflowEngine.graphml;

import java.util.HashMap;

import com.shareshipping.utils.workflowEngine.impl.WorkflowInfo;

public interface IGrapher {

	public abstract void draw(HashMap<String, WorkflowInfo> workflowInfo, String path);

}
