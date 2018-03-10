package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IEnrico;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;

@StartElement(id = LoginWorkflowStartNode.ID, to = LoginWorkflowEndNode.ID)
public class LoginWorkflowStartNode extends Stage<String, LoginContext> {

	public final static String ID = "StartNode";

	private IEnrico enrico;

	@Inject
	public LoginWorkflowStartNode(IEnrico enrico) {
		this.enrico = enrico;
	}

	@Override
	public void process(ICompletationToken token) {
		System.out.println(ID);
		enrico.doEnrico();
		token.done();
	}

}
