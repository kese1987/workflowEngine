package com.shareshipping.utils.workflowEngine;

public interface ICompletationToken {

	public abstract int getFlow();

	public abstract Throwable getException();

	public abstract void done();

	public abstract void done(boolean flow);

	public abstract void done(int flow);

	public abstract void doneWithError(Throwable e);

}
