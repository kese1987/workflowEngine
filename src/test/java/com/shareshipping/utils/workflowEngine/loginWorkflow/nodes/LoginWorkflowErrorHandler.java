package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@ErrorHandler(id = LoginWorkflowErrorHandler.ID, to = LoginWorkflowEndNode.ID, exceptionClass = Exception.class)
public class LoginWorkflowErrorHandler extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "ErrorHandler";

	@Override
	public void process(ICompletationToken token) {
		System.out.println("errorHandler");
	}

}
