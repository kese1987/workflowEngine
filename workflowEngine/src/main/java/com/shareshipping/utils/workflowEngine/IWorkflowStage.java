package com.shareshipping.utils.workflowEngine;

import java.util.concurrent.Callable;

public interface IWorkflowStage extends Callable<ICompletationToken> {

	public abstract void process(ICompletationToken token);

}