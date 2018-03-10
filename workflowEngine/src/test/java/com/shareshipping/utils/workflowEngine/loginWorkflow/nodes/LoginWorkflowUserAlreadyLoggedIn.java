package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import java.util.Random;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.BooleanGateway;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@BooleanGateway(id = LoginWorkflowUserAlreadyLoggedIn.ID, yesFlow = LoginWorkflowTask1Node.ID, noFlow = LoginWorkflowTask2Node.ID)
public class LoginWorkflowUserAlreadyLoggedIn extends Stage<LoginResult, LoginContext> {

	public static final String ID = "booleanGw";

	@Override
	public void process(ICompletationToken token) {

		Random rnd = new Random();
		token.done(true);

	}

}
