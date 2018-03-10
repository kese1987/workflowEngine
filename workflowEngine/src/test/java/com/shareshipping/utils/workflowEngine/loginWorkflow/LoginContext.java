package com.shareshipping.utils.workflowEngine.loginWorkflow;

import com.shareshipping.utils.workflowEngine.IWorkflowContext;

public class LoginContext implements IWorkflowContext {

	private int quanti;

	public int getQuanti() {
		return quanti++;
	}

	public void setQuanti(int quanti) {
		this.quanti = quanti;
	}

}
