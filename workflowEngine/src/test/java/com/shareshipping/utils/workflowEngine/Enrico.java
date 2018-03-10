package com.shareshipping.utils.workflowEngine;

public class Enrico implements IEnrico {

	int quanti = 0;

	@Override
	public void doEnrico() {
		System.out.println("doEnrico: " + quanti++);

	}

}
