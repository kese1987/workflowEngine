package com.shareshipping.utils.workflowEngine.loginWorkflow;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.impl.Workflow;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowEndNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowErrorHandler;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowErrorHandlerNull;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowForkNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowGatewayNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowJoinNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowParallel1A;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowParallel1B;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowParallel2;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowStartNode;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask1Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask2Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask3Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask4Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowTask5Node;
import com.shareshipping.utils.workflowEngine.loginWorkflow.nodes.LoginWorkflowUserAlreadyLoggedIn;

public class LoginWorkflow extends Workflow<LoginResult, LoginContext> {

	@Inject
	public LoginWorkflow(	Injector injector,
							IGrapher grapher) {
		super(injector, grapher);
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
	public List<Class<? extends WorkflowTask<LoginResult, LoginContext>>> nodes() {

		return Arrays.asList(	LoginWorkflowStartNode.class,
								LoginWorkflowEndNode.class,
								LoginWorkflowTask1Node.class,
								LoginWorkflowTask2Node.class,
								LoginWorkflowTask3Node.class,
								LoginWorkflowTask4Node.class,
								LoginWorkflowTask5Node.class,
								LoginWorkflowUserAlreadyLoggedIn.class,
								LoginWorkflowGatewayNode.class,
								LoginWorkflowErrorHandler.class,
								LoginWorkflowErrorHandlerNull.class,
								LoginWorkflowForkNode.class,
								LoginWorkflowParallel1A.class,
								LoginWorkflowParallel1B.class,
								LoginWorkflowParallel2.class,
								LoginWorkflowJoinNode.class);
	}

}
