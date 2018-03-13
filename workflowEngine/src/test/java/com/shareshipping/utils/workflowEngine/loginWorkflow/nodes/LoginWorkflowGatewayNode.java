package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.Gateway;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@Gateway(id = LoginWorkflowGatewayNode.ID, flows = { LoginWorkflowTask3Node.ID, LoginWorkflowTask4Node.ID,
		LoginWorkflowTask5Node.ID })
public class LoginWorkflowGatewayNode extends WorkflowTask<LoginResult, LoginContext> {

	public static final String ID = "Gw";

	@Override
	public void process(ICompletationToken token) {

		token.done(0);

	}

}
