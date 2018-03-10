package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IEnrico;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@EndElement(id = LoginWorkflowEndNode.ID)
public class LoginWorkflowEndNode extends Stage<LoginResult, LoginContext> {

	public final static String ID = "EndNode";

	private IEnrico enrico;

	@Inject
	public LoginWorkflowEndNode(IEnrico enrico) {
		this.enrico = enrico;
	}

	@Override
	public void process(ICompletationToken token) {
		System.out.println(ID);
		context.setQuanti(context.getQuanti() + 1);
		returnObject.s = new String("Ho finito sul serio");
		enrico.doEnrico();
	}

}