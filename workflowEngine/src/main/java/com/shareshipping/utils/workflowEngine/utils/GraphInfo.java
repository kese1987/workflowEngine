package com.shareshipping.utils.workflowEngine.utils;

import java.util.Map;

public class GraphInfo {

	String id;
	Map<Integer, String> exitFlows;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<Integer, String> getExitFlows() {
		return exitFlows;
	}

	public void setExitFlows(Map<Integer, String> exitFlows) {
		this.exitFlows = exitFlows;
	}

}
