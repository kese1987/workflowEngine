package com.shareshipping.utils.workflowEngine.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowFactory;

public class WorkflowFactory implements IWorkflowFactory {

	private final Injector injector;

	@Inject
	public WorkflowFactory(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <T, C extends IWorkflowContext> IWorkflow<T, C> create(Class<? extends IWorkflow<T, C>> wf) {
		return injector.getInstance(wf);

	}

}
