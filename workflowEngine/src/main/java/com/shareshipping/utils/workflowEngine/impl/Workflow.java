package com.shareshipping.utils.workflowEngine.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.shareshipping.utils.workflowEngine.ICompletationToken;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;

public abstract class Workflow<T, C extends IWorkflowContext> implements IWorkflow<T> {

	private final ExecutorService stageExecutors;
	private final Collection<Class<? extends Stage<T, C>>> nodes;
	private final ConcurrentMap<Object, Object> nodeByIdMap;

	@Inject
	@Named("WorkflowScope")
	WorkflowScope scope;

	@Inject
	Injector injector;

	@SuppressWarnings("unchecked")
	public Workflow(Class<? extends Stage<T, C>>... nodes) {
		this.nodes = Arrays.asList(nodes);
		this.nodeByIdMap = Maps.newConcurrentMap();
		this.stageExecutors = Executors.newCachedThreadPool();

	};

	@PostConstruct
	public void configureModule() {
		System.out.println("post Workflow");
	}

	@Override
	public Future<T> executeAsync() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T execute() {
		try {
			T returnVal = withReturnType().newInstance();
			C context = (C) withContext().newInstance();

			// scope.enter();
			for (Class<? extends Stage<T, C>> step : nodes) {

				Stage<T, C> task = injector.getInstance(step);
				task.setReturnValue(returnVal);
				task.setContext(context);
				try {
					ICompletationToken token = stageExecutors.submit(task).get();

				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// scope.exit();
			return returnVal;
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

}
