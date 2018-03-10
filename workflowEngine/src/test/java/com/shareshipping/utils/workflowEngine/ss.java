package com.shareshipping.utils.workflowEngine;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.impl.Stage;

public class ss extends Stage<String, LoginContext> {

	private IEnrico enrico;

	@Inject
	public ss(IEnrico enrico) {
		this.enrico = enrico;
	}

	@Override
	public void process(ICompletationToken token) {
		System.out.println("ss:" + context.getQuanti());

		enrico.doEnrico();
	}

}