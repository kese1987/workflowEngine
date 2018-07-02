package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@ErrorHandler(id = LoginWorkflowErrorHandlerNull.ID, to = LoginWorkflowEndNode.ID, exceptionClass = NullPointerException.class)
public class LoginWorkflowErrorHandlerNull extends WorkflowTask<LoginResult, LoginContext> {

	public final static String ID = "ErrorHandlerNull";

	@Override
	public void process(ICompletationToken token) {
		System.out.println("errorHandlerNull");
	}

}
