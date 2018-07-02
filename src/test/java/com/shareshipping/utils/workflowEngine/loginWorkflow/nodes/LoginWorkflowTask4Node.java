package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowTask4Node.ID, to = LoginWorkflowEndNode.ID)

public class LoginWorkflowTask4Node extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "TASK4";

	@Override
	public void process(ICompletationToken token) {

		System.out.println(ID);
		token.done();

	}

}
