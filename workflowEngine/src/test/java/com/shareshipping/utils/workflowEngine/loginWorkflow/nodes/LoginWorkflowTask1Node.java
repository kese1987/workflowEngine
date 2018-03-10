package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowTask1Node.ID, to = LoginWorkflowEndNode.ID)
public class LoginWorkflowTask1Node extends Stage<LoginResult, LoginContext> {

	public static final String ID = "TASK1";

	@Override
	public void process(ICompletationToken token) {
		System.out.print(ID);
		token.done();
	}

}
