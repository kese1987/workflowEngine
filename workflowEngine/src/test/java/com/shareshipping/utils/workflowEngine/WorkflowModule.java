package com.shareshipping.utils.workflowEngine;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class WorkflowModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IEnrico.class).to(Enrico.class).in(Scopes.SINGLETON);

		install(new FactoryModuleBuilder().implement(Payment.class, RealPayment.class).build(PaymentFactory.class));

	}

	@Provides
	LoginWorkflow getSomething(Injector injector) {
		return new LoginWorkflow(injector);
	}

}
