package com.shareshipping.utils.workflowEngine;

public class LoginContext implements IWorkflowContext {

	private int quanti;

	public int getQuanti() {
		return quanti++;
	}

	public void setQuanti(int quanti) {
		this.quanti = quanti;
	}

}
