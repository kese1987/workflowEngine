package com.shareshipping.utils.workflowEngine.loginWorkflow.nodes;

import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.annotations.ForkElement;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginContext;
import com.shareshipping.utils.workflowEngine.loginWorkflow.LoginResult;

@ForkElement(id = LoginWorkflowForkNode.ID, parallelFlows = { LoginWorkflowParallel1A.ID, LoginWorkflowParallel2.ID })
public class LoginWorkflowForkNode extends WorkflowTask<LoginResult, LoginContext> {

	public static final String ID = "ForkNode";

	@Override
	public void process(ICompletationToken token) {

		System.out.println("Forking");

		token.done();

	}

}
