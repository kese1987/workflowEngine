package com.shareshipping.utils.workflowEngine;

public interface IWorkflowFactory {

	public abstract <T, C extends IWorkflowContext> IWorkflow<T, C> create(Class<? extends IWorkflow<T, C>> wf);

}
