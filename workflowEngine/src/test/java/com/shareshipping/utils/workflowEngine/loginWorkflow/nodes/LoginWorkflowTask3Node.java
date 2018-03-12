package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowTask3Node.ID, to = LoginWorkflowEndNode.ID)

public class LoginWorkflowTask3Node extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "TASK3";

	@Override
	public void process(ICompletationToken token) {

		System.out.println(ID);
		token.done();

	}

}
