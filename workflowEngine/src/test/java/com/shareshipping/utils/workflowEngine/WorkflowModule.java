package com.shareshipping.utils.workflowEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class WorkflowModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IEnrico.class).to(Enrico.class).in(Scopes.SINGLETON);

	}

}
