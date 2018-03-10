package com.shareshipping.utils.workflowEngine.graphml.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.impl.Stage;

public class Grapher implements IGrapher {

	private final Injector injector;

	@Inject
	public Grapher(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <T, C extends IWorkflowContext> void draw(Class<? extends IWorkflow<T, C>> workflow, String path) {

		IWorkflow<T, C> wf = injector.getInstance(workflow);

		Collection<Class<? extends Stage<T, C>>> nodes = wf.nodes();

		for (Class<? extends Stage<T, C>> node : nodes) {
			Annotation[] annotations = node.getDeclaredAnnotations();

			System.out.println(Arrays.toString(annotations));

		}

	}

}
