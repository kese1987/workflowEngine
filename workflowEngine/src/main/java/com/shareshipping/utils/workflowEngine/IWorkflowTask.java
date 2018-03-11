package com.shareshipping.utils.workflowEngine;

import java.util.concurrent.Callable;

public interface IWorkflowTask<T, C extends IWorkflowContext> extends Callable<ICompletationToken> {

	public abstract void process(ICompletationToken token);

	public abstract void setReturnValue(T returnValue);

	public abstract void setContext(C context);
}