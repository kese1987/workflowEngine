package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowTask2Node.ID, to = LoginWorkflowGatewayNode.ID)
public class LoginWorkflowTask2Node extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "TASK2";

	@Override
	public void process(ICompletationToken token) {

		System.out.println(ID);
		token.done();

	}

}
