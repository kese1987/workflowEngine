package com.shareshipping.utils.workflowEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.graphml.impl.Grapher;
import com.shareshipping.utils.workflowEngine.impl.WorkflowFactory;

public class WorkflowEngineModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(IWorkflowFactory.class).to(WorkflowFactory.class).in(Scopes.SINGLETON);

		bind(IGrapher.class).to(Grapher.class).in(Scopes.SINGLETON);

		bindListener(Matchers.any(), new TypeListener() {
			@Override
			public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
				encounter.register(new InjectionListener<I>() {
					@Override
					public void afterInjection(I injectee) {

						Method[] methods = MethodUtils.getMethodsWithAnnotation(injectee.getClass(),
								PostConstruct.class, true, true);
						for (Method m : methods) {
							m.setAccessible(true);

							try {
								m.invoke(injectee);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				});
			}
		});

	}

}
