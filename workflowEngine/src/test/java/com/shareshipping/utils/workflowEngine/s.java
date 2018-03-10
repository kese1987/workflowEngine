package com.shareshipping.utils.workflowEngine;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.impl.Stage;

public class s extends Stage<String, LoginContext> {

	private IEnrico enrico;

	@Inject
	public s(IEnrico enrico) {
		this.enrico = enrico;
	}

	@Override
	public void process(ICompletationToken token) {
		System.out.println("s:" + context.getQuanti());

		enrico.doEnrico();
	}

}
