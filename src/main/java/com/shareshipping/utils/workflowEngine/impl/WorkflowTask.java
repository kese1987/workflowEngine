package com.shareshipping.utils.workflowEngine.impl;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowTask;

public abstract class WorkflowTask<T, C extends IWorkflowContext> implements IWorkflowTask<T, C> {

	private ICompletationToken token;
	protected T returnObject;
	protected C context;

	@Override
	public void setReturnValue(T returnValue) {
		this.returnObject = returnValue;
	}

	@Override
	public void setContext(C context) {
		this.context = context;
	}

	@Override
	public ICompletationToken call() throws Exception {
		process(token);
		return token;
	}

}
