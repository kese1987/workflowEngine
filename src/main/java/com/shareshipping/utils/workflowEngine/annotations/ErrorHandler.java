package com.shareshipping.utils.workflowEngine.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ErrorHandler {
	Class<? extends Throwable> exceptionClass();

	String id();

	String to();
}
