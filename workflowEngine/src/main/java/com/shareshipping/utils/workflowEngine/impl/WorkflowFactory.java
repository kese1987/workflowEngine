package com.shareshipping.utils.workflowEngine.impl;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.IWorkflowFactory;

public class WorkflowFactory implements IWorkflowFactory {

	private final Injector injector;

	private HashMap<String, IWorkflow<?, ?>> workflowInstances = Maps.newHashMap();

	@Inject
	public WorkflowFactory(Injector injector) {
		this.injector = injector;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, C extends IWorkflowContext> IWorkflow<T, C> create(Class<? extends IWorkflow<T, C>> wf) {

		String wfKey = wf.getName();
		if (workflowInstances.containsKey(wfKey)) {
			return (IWorkflow<T, C>) workflowInstances.get(wfKey);
		} else {
			IWorkflow<T, C> wfInstance = injector.getInstance(wf);
			workflowInstances.put(wfKey, wfInstance);
			return wfInstance;
		}

	}
}
