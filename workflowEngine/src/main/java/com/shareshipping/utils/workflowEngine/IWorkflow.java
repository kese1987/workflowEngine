package com.shareshipping.utils.workflowEngine;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

public interface IWorkflow<T, C extends IWorkflowContext> {

	public abstract Class<C> withContext();

	public abstract Class<T> withReturnType();

	public abstract T execute();

	public abstract Future<T> executeAsync();

	static List<Class<? extends Stage<LoginResult, LoginContext>>> nodes() {
		return Collections.emptyList();

	};
}
