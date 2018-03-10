package com.shareshipping.utils.workflowEngine.loginWorkflow;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.impl.Stage;
import com.shareshipping.utils.workflowEngine.impl.Workflow;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowEndNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowStartNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask1Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask2Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowUserAlreadyLoggedIn;

public class LoginWorkflow extends Workflow<LoginResult, LoginContext> {

	@Inject
	public LoginWorkflow(Injector injector) {
		super(injector);
	}

	@Override
	public Class<LoginResult> withReturnType() {
		return LoginResult.class;
	}

	@Override
	public Class<LoginContext> withContext() {
		return LoginContext.class;
	}

	@Override
	protected List<Class<? extends Stage<LoginResult, LoginContext>>> nodes() {
		return Arrays.asList(LoginWorkflowStartNode.class, 
							LoginWorkflowEndNode.class, 
							LoginWorkflowTask1Node.class,
							LoginWorkflowTask2Node.class, 
							LoginWorkflowUserAlreadyLoggedIn.class);
	}

}
