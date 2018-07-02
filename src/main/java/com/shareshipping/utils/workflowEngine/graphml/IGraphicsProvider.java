package com.shareshipping.utils.workflowEngine.graphml;

import java.util.HashMap;

import com.github.systemdir.gml.model.YedGmlGraphicsProvider;
import com.shareshipping.utils.workflowEngine.impl.WorkflowInfo;

public interface IGraphicsProvider<V, E, G> extends YedGmlGraphicsProvider<V, E, G> {

	public abstract void setWorkflowInfo(HashMap<String, WorkflowInfo> workflowInfo);

}
