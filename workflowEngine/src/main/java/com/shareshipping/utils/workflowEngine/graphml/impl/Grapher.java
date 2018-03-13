package com.shareshipping.utils.workflowEngine.graphml.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.github.systemdir.gml.YedGmlWriter;
import com.github.systemdir.gml.YedGmlWriter.Builder;
import com.google.inject.Inject;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.graphml.IGraphicsProvider;
import com.shareshipping.utils.workflowEngine.impl.WorkflowInfo;

public class Grapher implements IGrapher {

	private final IGraphicsProvider<String, DefaultEdge, Object> graphicsProvider = new GraphicsProvider<>();

	@Inject
	public Grapher() {
	}

	@Override
	public void draw(HashMap<String, WorkflowInfo> workflowInfo, String path) {

		graphicsProvider.setWorkflowInfo(workflowInfo);

		Builder<String, DefaultEdge, Object> writerBuilder = new YedGmlWriter.Builder<String, DefaultEdge, Object>(
				graphicsProvider, YedGmlWriter.PrintLabels.PRINT_VERTEX_LABELS,
				YedGmlWriter.PrintLabels.PRINT_EDGE_LABELS);

		writerBuilder.setEdgeLabelProvider(new Function<DefaultEdge, String>() {

			@Override
			public String apply(DefaultEdge t) {

				String[] fromTo = t.toString().replace("(", "").replace(")", "").replaceAll("\\s+", " ")
						.split("\\s:\\s");

				WorkflowInfo nodeInfoFrom = workflowInfo.get(fromTo[0]);
				Integer toNodeId = nodeInfoFrom.getExitFlows().inverse().get(fromTo[1]);

				switch (nodeInfoFrom.getNodeType()) {
				case BOOLEAN_GATEWAY_ELEMENT:
					if (toNodeId == 0) {
						return "false";
					} else {
						return "true";
					}
				case GATEWAY_ELEMENT:
					return "flow " + toNodeId;
				default:
					return "";
				}
			}
		});

		// get the gml writer
		YedGmlWriter<String, DefaultEdge, Object> writer = writerBuilder.build();

		SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		for (Map.Entry<String, WorkflowInfo> node : workflowInfo.entrySet()) {
			System.out.println("vertex:" + node.getKey());
			graph.addVertex(node.getKey());

		}

		for (Map.Entry<String, WorkflowInfo> node : workflowInfo.entrySet()) {

			for (Map.Entry<Integer, String> flow : node.getValue().getExitFlows().entrySet()) {
				String nodeFrom = node.getKey();
				String nodeTo = flow.getValue();
				try {
					if (StringUtils.isNotBlank(nodeTo)) {
						graph.addEdge(nodeFrom, nodeTo);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage() + "from: " + nodeFrom + " to: " + nodeTo);
				}

			}

		}

		// write to file
		new File("gml-output").mkdir();
		// create folder
		File outputFile = new File("gml-output" + File.separator + "example2.gml");

		Writer output;
		try {
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
			writer.export(output, graph);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("Exported to: " + outputFile.getAbsolutePath());

	}

}
