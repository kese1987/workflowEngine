package com.shareshipping.utils.workflowEngine;

import java.util.concurrent.Future;

public interface IWorkflow<T, C extends IWorkflowContext> {

	public abstract Class<C> withContext();

	public abstract Class<T> withReturnType();

	public abstract T execute();

	public abstract Future<T> executeAsync();
}
