package com.shareshipping.utils.workflowEngine;

import com.shareshipping.utils.workflowEngine.annotations.AWorkflowScope;

@AWorkflowScope
public class Enrico implements IEnrico {

	int quanti = 0;

	@Override
	public void doEnrico() {
		System.out.println("doEnrico: " + quanti++);

	}

}
