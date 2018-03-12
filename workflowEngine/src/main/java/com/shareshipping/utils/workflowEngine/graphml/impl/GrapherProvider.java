package com.shareshipping.utils.workflowEngine.graphml.impl;

import java.awt.Color;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

import com.github.systemdir.gml.model.EdgeGraphicDefinition;
import com.github.systemdir.gml.model.GraphicDefinition;
import com.github.systemdir.gml.model.NodeGraphicDefinition;
import com.github.systemdir.gml.model.YedGmlGraphicsProvider;

public class GrapherProvider implements YedGmlGraphicsProvider<String, DefaultEdge, Object> {

	@Override
	public NodeGraphicDefinition getVertexGraphics(String vertex) {
		return new NodeGraphicDefinition.Builder().setFill(Color.LIGHT_GRAY).setLineColor(Color.black)
				.setFontStyle(GraphicDefinition.FontStyle.ITALIC).build();
	}

	@Override
	public EdgeGraphicDefinition getEdgeGraphics(DefaultEdge edge, String edgeSource, String edgeTarget) {
		return new EdgeGraphicDefinition.Builder().setTargetArrow(EdgeGraphicDefinition.ArrowType.SHORT_ARROW)
				.setLineType(GraphicDefinition.LineType.DASHED).build();
	}

	// we have no groups in this example
	@Override
	public NodeGraphicDefinition getGroupGraphics(Object group, Set<String> groupElements) {
		return null;
	}

}
