package com.shareshipping.utils.workflowEngine;

import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.exceptions.WorkflowException;
import com.shareshipping.utils.workflowEngine.guice.WorkflowEngineModule;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void doThings() throws WorkflowException {

		// Injector injector = LifecycleInjector.builder().withModules(Arrays.asList(new
		// WorkflowModule())).build()
		// .createInjector();

		Injector injector = Guice.createInjector(new WorkflowEngineModule(), new WorkflowModule());
		Application app = injector.getInstance(Application.class);
		app.run();

	}

}
