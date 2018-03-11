package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IEnrico;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@StartElement(id = LoginWorkflowStartNode.ID, to = LoginWorkflowUserAlreadyLoggedIn.ID)
public class LoginWorkflowStartNode extends WorkflowTask<LoginResult, LoginContext> {

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

		context.setQuanti(context.getQuanti() + 1);
		token.done();
	}

}
