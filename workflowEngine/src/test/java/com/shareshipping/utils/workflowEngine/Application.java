package com.shareshipping.utils.workflowEngine;

import javax.annotation.PostConstruct;

import com.google.inject.Inject;

public class Application {

	private LoginWorkflow loginWf;

	@PostConstruct
	public void configure() {
		System.out.println("post Application");
	}

	@Inject
	public Application(LoginWorkflow loginWf) {
		this.loginWf = loginWf;

	}

	public void run() {
		loginWf.execute();
	}

}
