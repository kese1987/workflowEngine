package com.shareshipping.utils.workflowEngine.graphml.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.github.systemdir.gml.YedGmlWriter;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.shareshipping.utils.workflowEngine.IWorkflow;
import com.shareshipping.utils.workflowEngine.IWorkflowContext;
import com.shareshipping.utils.workflowEngine.annotations.BooleanGateway;
import com.shareshipping.utils.workflowEngine.annotations.EndElement;
import com.shareshipping.utils.workflowEngine.annotations.ErrorHandler;
import com.shareshipping.utils.workflowEngine.annotations.Gateway;
import com.shareshipping.utils.workflowEngine.annotations.StartElement;
import com.shareshipping.utils.workflowEngine.annotations.UserTaskElement;
import com.shareshipping.utils.workflowEngine.graphml.IGrapher;
import com.shareshipping.utils.workflowEngine.impl.WorkflowTask;
import com.shareshipping.utils.workflowEngine.utils.GraphInfo;
import com.shareshipping.utils.workflowEngine.utils.WorkflowUtils;

public class Grapher implements IGrapher {

	private final Injector injector;

	@Inject
	public Grapher(Injector injector) {
		this.injector = injector;
	}

	@Override
	public <T, C extends IWorkflowContext> void draw(Class<? extends IWorkflow<T, C>> workflow, String path) {

		IWorkflow<T, C> wf = injector.getInstance(workflow);

		Collection<Class<? extends WorkflowTask<T, C>>> nodes = wf.nodes();

		// get graph from user
		SimpleGraph<String, DefaultEdge> toDraw = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		// define the look and feel of the graph
		GrapherProvider graphicsProvider = new GrapherProvider();

		// get the gml writer
		YedGmlWriter<String, DefaultEdge, Object> writer = new YedGmlWriter.Builder<String, DefaultEdge, Object>(
				graphicsProvider, YedGmlWriter.PrintLabels.PRINT_VERTEX_LABELS).build();

		int verticeAmount = nodes.size();

		SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		for (Class<? extends WorkflowTask<T, C>> node : nodes) {
			Annotation[] annotations = node.getDeclaredAnnotations();

			Optional<GraphInfo> metadata = extractGraphInfoFromAnnotation(annotations);

			// System.out.println(Arrays.toString(annotations));

			System.out.println("SONO IN DRAW");

			if (metadata.isPresent()) {

				graph.addVertex(metadata.get().getId());

				System.out.println(metadata.get().getId());
				System.out.println("EXITTTT FLOWSS");

				System.out.println(metadata.get().getExitFlows().values());
			}

		}

		for (Class<? extends WorkflowTask<T, C>> node : nodes) {

			Annotation[] annotations = node.getDeclaredAnnotations();

			Optional<GraphInfo> metadata = extractGraphInfoFromAnnotation(annotations);

			Arrays.asList(annotations).stream().filter(a -> WorkflowUtils.isAWorkflowEngineAnnotation(a))
					.forEach((ann) -> {

						String actualNode = "";
						String pointedNode = "";

						if (ann instanceof StartElement) {
							actualNode = metadata.get().getId();
							pointedNode = metadata.get().getExitFlows().get(0);

							System.out.println("POINTED NODESAAAAAAAAAAAAAaa");
							graph.addEdge(actualNode, pointedNode);

						} else if (ann instanceof BooleanGateway) {
							actualNode = metadata.get().getId();
							pointedNode = metadata.get().getExitFlows().get(0);

							graph.addEdge(actualNode, pointedNode);

							pointedNode = metadata.get().getExitFlows().get(1);

							// System.out.println("BOOLEAN GATEWAYY");
							// System.out.println(metadata.get().getExitFlows().get(0));
							graph.addEdge(actualNode, pointedNode);
						} else if (ann instanceof UserTaskElement) {

							actualNode = metadata.get().getId();
							pointedNode = metadata.get().getExitFlows().get(0);
							graph.addEdge(actualNode, pointedNode);
						}
					});

			/*
			 * if (metadata.isPresent()) {
			 * 
			 * if (metadata.get().getId().equals("StartNode")) {
			 * 
			 * } else if (metadata.get().getId().equals("booleanGw")) {
			 * 
			 * actualNode = metadata.get().getId(); pointedNode =
			 * metadata.get().getExitFlows().get(0);
			 * 
			 * graph.addEdge(actualNode, pointedNode);
			 * 
			 * pointedNode = metadata.get().getExitFlows().get(1);
			 * 
			 * // System.out.println("BOOLEAN GATEWAYY"); //
			 * System.out.println(metadata.get().getExitFlows().get(0));
			 * graph.addEdge(actualNode, pointedNode);
			 * 
			 * }
			 * 
			 * }
			 * 
			 */ }
		// write to file
		new File("gml-output").mkdir();// create folder
		File outputFile = new File("gml-output" + File.separator + "example2.gml");
		try (Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"))) {
			writer.export(output, graph);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Exported to: " + outputFile.getAbsolutePath());
	}

	public Optional<GraphInfo> extractGraphInfoFromAnnotation(Annotation[] annotations) {

		GraphInfo info = new GraphInfo();

		Arrays.asList(annotations).stream().filter(a -> WorkflowUtils.isAWorkflowEngineAnnotation(a)).forEach((ann) -> {

			String id = "";

			Map<Integer, String> exitFlows = Maps.newHashMap();

			if (ann instanceof BooleanGateway) {
				BooleanGateway bgAnn = (BooleanGateway) ann;
				id = bgAnn.id();
				exitFlows.put(0, bgAnn.noFlow());

				exitFlows.put(1, bgAnn.yesFlow());
			} else if (ann instanceof EndElement) {
				EndElement eeAnn = (EndElement) ann;
				id = eeAnn.id();
				exitFlows.put(0, null);
			} else if (ann instanceof ErrorHandler) {
				ErrorHandler ehAnn = (ErrorHandler) ann;
				id = ehAnn.id();
				exitFlows.put(0, null);
			} else if (ann instanceof Gateway) {
				Gateway gAnn = (Gateway) ann;
				exitFlows.put(0, gAnn.flow1());
				exitFlows.put(1, gAnn.flow2());
				exitFlows.put(2, gAnn.flow3());
				exitFlows.put(3, gAnn.flow4());
				exitFlows.put(4, gAnn.flow5());
			} else if (ann instanceof StartElement) {
				StartElement stAnn = (StartElement) ann;
				id = stAnn.id();
				exitFlows.put(0, stAnn.to());
			} else if (ann instanceof UserTaskElement) {
				UserTaskElement utAnn = (UserTaskElement) ann;
				id = utAnn.id();
				exitFlows.put(0, utAnn.to());
			}

			info.setId(id);
			info.setExitFlows(exitFlows);

		});

		return StringUtils.isBlank(info.getId()) ? Optional.empty() : Optional.ofNullable(info);

	}

	/**
	 * Asks the user to input an graph
	 *
	 * @return graph inputted by the user
	 */
	public static SimpleGraph<String, DefaultEdge> getSimpleGraphFromUser() {
		Scanner sc = new Scanner(System.in);
		SimpleGraph<String, DefaultEdge> graph = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);

		// input number of vertices
		int verticeAmount;
		do {
			System.out.print("Enter the number of vertices (more than one): ");
			verticeAmount = sc.nextInt();
		} while (verticeAmount <= 1);

		// input vertices
		for (int i = 1; i <= verticeAmount; i++) {
			System.out.print("Enter vertex name " + i + ": ");
			String input = sc.next();
			if (graph.vertexSet().contains(input)) {
				System.err.println("vertex with that name already exists");
				i--;
			} else {
				graph.addVertex(input);
			}
		}

		// input edges
		System.out.println("\nEnter edge (name => name)");
		String userWantsToContinue;
		do {
			String e1, e2;

			do {
				System.out.print("Edge from: ");
				e1 = sc.next();
			} while (!graph.vertexSet().contains(e1));
			do {
				System.out.print("Edge to: ");
				e2 = sc.next();
			} while (!graph.vertexSet().contains(e2));

			graph.addEdge(e1, e2);

			// add another edge?
			System.out.print("Add more edges? (y/n): ");
			userWantsToContinue = sc.next();
		} while (userWantsToContinue.equals("y") || userWantsToContinue.equals("yes")
				|| userWantsToContinue.equals("1"));

		return graph;
	}

}
