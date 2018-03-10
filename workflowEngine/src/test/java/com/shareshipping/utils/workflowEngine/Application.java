package com.shareshipping.utils.workflowEngine;

import javax.annotation.PostConstruct;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginWorkflow;

public class Application {

	private LoginWorkflow loginWf;

	@PostConstruct
	public void configure() {
		System.out.println("post Application");
	}

	@Inject
	public Application(IWorkflowFactory wfFcatory) {
		loginWf = (LoginWorkflow) wfFcatory.create(LoginWorkflow.class);

	}

	public void run() {
		loginWf.execute();
	}

}
