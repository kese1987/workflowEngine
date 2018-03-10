package com.shareshipping.utils.workflowEngine;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.impl.Workflow;
import com.shareshipping.utils.workflowEngine.impl.WorkflowScope;

public class LoginWorkflow extends Workflow<String, LoginContext> {

	@Inject
	public LoginWorkflow(Injector injector, WorkflowScope scope) {
		super(injector, scope);
	}

	@Override
	public Class<String> withReturnType() {
		return String.class;
	}

	@Override
	public Class<LoginContext> withContext() {
		return LoginContext.class;
	}

	@Override
	protected List<Class<? extends Stage<String, LoginContext>>> nodes() {
		return Arrays.asList(s.class, ss.class);
	}

}
