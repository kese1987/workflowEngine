package com.shareshipping.utils.workflowEngine;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.impl.EmptyContext;
import com.shareshipping.utils.workflowEngine.impl.Workflow;

public class LoginWorkflow extends Workflow<String, EmptyContext> {

	@SuppressWarnings("unchecked")
	@Inject
	public LoginWorkflow(Injector injector) {
		super(s.class, ss.class);
	}

	@Override
	public Class<? extends String> withReturnType() {
		return String.class;
	}

	@Override
	public Class<? extends IWorkflowContext> withContext() {
		return EmptyContext.class;
	}

}
