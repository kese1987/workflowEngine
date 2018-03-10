package com.shareshipping.utils.workflowEngine.graphml;

import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;

public interface IGrapher {

	public abstract <T, C extends IWorkflowContext> void draw(Class<? extends IWorkflow<T, C>> wf, String path);

}
