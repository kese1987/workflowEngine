package com.shareshipping.utils.workflowEngine.impl;

import com.shareshipping.utils.workflowEngine.ICompletationToken;

public class CompletationToken implements ICompletationToken {

	private int flow = 0;
	private Throwable exception;

	@Override
	public void done() {
		flow = 0;
	}

	@Override
	public void done(boolean flowBool) {
		flow = flowBool ? 1 : 0;
	}

	@Override
	public void done(int flow) {
		this.flow = flow;
	}

	@Override
	public void doneWithError(Throwable e) {
		this.exception = e;
	}

	@Override
	public int getFlow() {
		return flow;
	}

	@Override
	public Throwable getException() {
		return exception;
	}

}
