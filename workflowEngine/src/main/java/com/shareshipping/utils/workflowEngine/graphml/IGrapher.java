package com.shareshipping.utils.workflowEngine.graphml;

import com.shareshipping.utils.workflowEngine.IWorkflow;

public interface IGrapher {

	public abstract void draw(Class<? extends IWorkflow> workflow, String path);

}
