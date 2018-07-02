package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowParallel2.ID, to = LoginWorkflowJoinNode.ID)
public class LoginWorkflowParallel2 extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "LoginWorkflowParallel2";

	@Override
	public void process(ICompletationToken token) {
		System.out.println("\t" + Thread.currentThread().getId() + ": Parallel2");

	}

}
