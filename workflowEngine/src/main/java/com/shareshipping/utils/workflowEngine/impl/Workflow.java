package com.shareshipping.utils.workflowEngine.impl;

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

public abstract class Workflow<T, C extends IWorkflowContext> implements IWorkflow<T, C> {

	private final ExecutorService stageExecutors;
	private Collection<Class<? extends Stage<T, C>>> workflowNodes;
	private final ConcurrentMap<Object, Object> nodeByIdMap;

	private final Injector injector;
	private final WorkflowScope scope;

	@Inject
	public Workflow(Injector injector, @Named("WorkflowScope") WorkflowScope scope) {
		this.injector = injector;
		this.scope = scope;
		this.nodeByIdMap = Maps.newConcurrentMap();
		this.stageExecutors = Executors.newCachedThreadPool();
	};

	protected abstract Collection<Class<? extends Stage<T, C>>> nodes();

	@PostConstruct
	public void configureModule() {
		workflowNodes = nodes();
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
			C context = withContext().newInstance();

			for (Class<? extends Stage<T, C>> step : workflowNodes) {

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
			return returnVal;
		} catch (InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

}
