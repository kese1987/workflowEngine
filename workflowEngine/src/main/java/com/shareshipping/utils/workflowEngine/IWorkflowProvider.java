package com.shareshipping.utils.workflowEngine;

import com.google.inject.Provider;
import com.shareshipping.utils.workflowEngine.impl.Workflow;

public interface IWorkflowProvider<T, C extends IWorkflowContext> extends Provider<Workflow<T, C >> {

}
