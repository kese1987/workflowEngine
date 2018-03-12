package com.shareshipping.utils.workflowEngine.utils;

import java.lang.annotation.Annotation;

import com.shareshipping.utils.workflowEngine.annotations.BooleanGateway;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.annotations.Gateway;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;

public class WorkflowUtils {

	public static boolean isAWorkflowEngineAnnotation(Annotation a) {
		return a instanceof BooleanGateway || a instanceof EndElement || a instanceof ErrorHandler
				|| a instanceof Gateway || a instanceof StartElement || a instanceof UserTaskElement;
	}

}
