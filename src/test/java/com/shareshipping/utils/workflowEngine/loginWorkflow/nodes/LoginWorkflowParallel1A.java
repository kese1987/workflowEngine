package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@UserTaskElement(id = LoginWorkflowParallel1A.ID, to = LoginWorkflowParallel1B.ID)
public class LoginWorkflowParallel1A extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "LoginWorkflowParallel1A";

	@Override
	public void process(ICompletationToken token) {

		System.out.println("\t" + Thread.currentThread().getId() + ": ParallelA");

		token.done();
	}

}
