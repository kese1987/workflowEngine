package com.shareshipping.utils.workflowEngine;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.shareshipping.utils.workflowEngine.annotations.AWorkflowScope;
import com.shareshipping.utils.workflowEngine.impl.WorkflowScope;

public class WorkflowEngineModule extends AbstractModule {

	@Override
	protected void configure() {

		WorkflowScope wfScope = new WorkflowScope();

		// tell Guice about the scope
		bindScope(AWorkflowScope.class, wfScope);

		// make our scope instance injectable
		bind(WorkflowScope.class).annotatedWith(Names.named("WorkflowScope")).toInstance(wfScope);
	}

}
