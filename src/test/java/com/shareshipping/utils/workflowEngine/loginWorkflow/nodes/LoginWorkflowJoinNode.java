package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.JoinElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@JoinElement(id = LoginWorkflowJoinNode.ID, to = LoginWorkflowEndNode.ID)
public class LoginWorkflowJoinNode extends WorkflowTask<LoginResult, LoginContext> {

	public static final String ID = "joinNode";

	@Override
	public void process(ICompletationToken token) {
		System.out.println("Join");

		token.done();

	}

}
