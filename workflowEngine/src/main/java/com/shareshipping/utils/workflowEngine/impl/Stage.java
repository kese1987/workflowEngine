package com.shareshipping.utils.workflowEngine.impl;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowStage;

public abstract class Stage<T, C extends IWorkflowContext> implements IWorkflowStage {

	private ICompletationToken token;
	protected T returnObject;
	protected C context;

	public void setReturnValue(T returnValue) {
		this.returnObject = returnValue;
	}

	public void setContext(C context) {
		this.context = context;
	}

	@Override
	public ICompletationToken call() throws Exception {
		process(token);
		return token;
	}

}
