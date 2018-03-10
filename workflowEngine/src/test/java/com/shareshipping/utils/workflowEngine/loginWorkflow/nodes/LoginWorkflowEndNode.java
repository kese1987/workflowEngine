package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IEnrico;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;

@EndElement(id = LoginWorkflowEndNode.ID)
public class LoginWorkflowEndNode extends Stage<String, LoginContext> {

	public final static String ID = "EndNode";

	private IEnrico enrico;

	@Inject
	public LoginWorkflowEndNode(IEnrico enrico) {
		this.enrico = enrico;
	}

	@Override
	public void process(ICompletationToken token) {
		System.out.println(ID);

		enrico.doEnrico();
	}

}