package com.shareshipping.utils.workflowEngine;

import java.util.concurrent.Future;

public interface IWorkflow<T> {

	public abstract Class<? extends IWorkflowContext> withContext();

	public abstract Class<? extends T> withReturnType();

	public abstract T execute();

	public abstract Future<T> executeAsync();
}
