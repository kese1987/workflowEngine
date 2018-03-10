package com.shareshipping.utils.workflowEngine;

import java.util.Collection;
import java.util.concurrent.Future;

import com.shareshipping.utils.workflowEngine.impl.Stage;

public interface IWorkflow<T, C extends IWorkflowContext> {

	public abstract Class<C> withContext();

	public abstract Class<T> withReturnType();

	public abstract T execute();

	public abstract Future<T> executeAsync();

	public abstract Collection<Class<? extends Stage<T, C>>> nodes();
}
