package com.shareshipping.utils.workflowEngine;

import javax.annotation.PostConstruct;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;
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
		System.out.println("dentro");

	}

	public void run() {
		@SuppressWarnings("unused")
		LoginResult result = loginWf.execute();
		// System.out.println(result.s);
		// CompletableFuture<Void> future = loginWf.executeAsync().thenAccept(s ->
		// System.out.println(s.s));

		// while (!future.isDone())
		// ;

	}

}
