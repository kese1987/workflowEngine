package com.shareshipping.utils.workflowEngine.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Gateway {

	String id();

	String flow1();

	String flow2() default "";

	String flow3() default "";

	String flow4() default "";

	String flow5() default "";
}
