package com.shareshipping.utils.workflowEngine.graphml.impl;

import java.awt.Color;
import java.util.HashMap;
import java.util.Set;

import com.github.systemdir.gml.model.EdgeGraphicDefinition;
import com.github.systemdir.gml.model.GraphicDefinition;
import com.github.systemdir.gml.model.NodeGraphicDefinition;
import com.github.systemdir.gml.model.NodeGraphicDefinition.Builder;
import com.github.systemdir.gml.model.NodeGraphicDefinition.Form;
import com.shareshipping.utils.workflowEngine.enums.NodeTypeEnum;
import com.shareshipping.utils.workflowEngine.graphml.IGraphicsProvider;
import com.shareshipping.utils.workflowEngine.impl.WorkflowInfo;

public class GraphicsProvider<V, E, G> implements IGraphicsProvider<V, E, G> {

	private HashMap<String, WorkflowInfo> workflowInfo;

	@Override
	public NodeGraphicDefinition getVertexGraphics(V vertex) {

		WorkflowInfo node = workflowInfo.get(vertex);
		NodeTypeEnum nodeType = node.getNodeType();

		Builder nodeBuilder = new NodeGraphicDefinition.Builder();
		nodeBuilder.setFill(Color.LIGHT_GRAY).setLineColor(Color.black)
				.setFontStyle(GraphicDefinition.FontStyle.ITALIC);

		switch (nodeType) {
		case BOOLEAN_GATEWAY_ELEMENT:
		case GATEWAY_ELEMENT:
			nodeBuilder.setForm(Form.diamond);
			break;
		case END_ELEMENT:
			nodeBuilder.setFill(Color.RED);
			nodeBuilder.setForm(Form.ellipse);
			break;
		case START_ELEMENT:
			nodeBuilder.setFill(Color.GREEN);
			nodeBuilder.setForm(Form.ellipse);
			break;
		case ERROR_HANDLER_ELEMENT:
			nodeBuilder.setFill(Color.YELLOW);
			nodeBuilder.setForm(Form.triangle);
			break;
		case USER_TASK_ELEMENT:
			nodeBuilder.setForm(Form.rectangle);
			break;
		case JOIN_ELEMENT:
		case FORK_ELEMENT:
			nodeBuilder.setForm(Form.hexagon);
			nodeBuilder.setFill(Color.BLUE);
		default:
			break;

		}

		return nodeBuilder.build();
	}

	@Override
	public NodeGraphicDefinition getGroupGraphics(G group,
			Set<V> groupElements) {
		return null;
	}

	@Override
	public void setWorkflowInfo(HashMap<String, WorkflowInfo> workflowInfo) {
		this.workflowInfo = workflowInfo;
	}

	@Override
	public EdgeGraphicDefinition getEdgeGraphics(E edge,
			V edgeSource,
			V edgeTarget) {
		return new EdgeGraphicDefinition.Builder().setTargetArrow(EdgeGraphicDefinition.ArrowType.SHORT_ARROW)
				.setLineType(GraphicDefinition.LineType.DASHED).build();
	}

}
