package io.github.morven11.algorithm;

import io.github.morven11.graphs.DirectedGraph;
import io.github.morven11.graphs.Edge;
import io.github.morven11.graphs.Graph;
import io.github.morven11.graphs.Node;
import io.github.morven11.graphs.UndirectedGraph;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This is Kruskal class T extends comparable T.
 *
 * @param <T> T is the Generic Parameter.
 */
public class Kruskal<T extends Comparable<T>> {

  private final Graph<T> graph;
  private Map<Node<T>, Subset<T>> subset;
  private Integer minimumCost;

  /**
   * This is the Constructor of Kruskal class.
   *
   * @param graph The graph to convert into a minimum Spanning Tree.
   */
  public Kruskal(Graph<T> graph) {
    this.subset = new TreeMap<>();
    this.minimumCost = 0;
    this.graph = getMinimumSpanningTree(graph);
  }

  public Integer getMinimumCost() {
    return minimumCost;
  }

  public Graph<T> getGraph() {
    return this.graph;
  }

  private Graph<T> cleanGraph(Graph<T> graph) {
    Set<Edge<T>> visited = new TreeSet<>();
    Graph<T> cleanGraph = new DirectedGraph<>();
    for (Edge<T> edge : graph.getAllEdges()) {
      if (visited.contains(edge)) {
        continue;
      }
      Node<T> source = edge.getSource();
      Node<T> destination = edge.getDestination();
      Integer weight = edge.getWeight();
      Edge<T> imageEdge = new Edge<>(weight, destination, source);
      visited.add(edge);
      visited.add(imageEdge);
      cleanGraph.addEdge(weight, source, destination);
    }
    return cleanGraph;
  }

  private Map<Node<T>, Subset<T>> fillSubset(Graph<T> graph) {
    Map<Node<T>, Subset<T>> subset = new TreeMap<>();
    for (Node<T> node : graph.getAllNodes()) {
      subset.put(node, new Subset<>(node));
    }
    return subset;
  }

  private Node<T> find(Node<T> node) {
    if (subset.get(node).getParent() == node) {
      return node;
    }
    return find(subset.get(node).getParent());
  }

  private void union(Node<T> source, Node<T> destination) {
    Node<T> rootSource = find(source);
    Node<T> rootDestination = find(destination);
    if (subset.get(rootSource).getRank() < subset.get(rootDestination).getRank()) {
      subset.get(rootSource).setParent(rootDestination);
    } else if (subset.get(rootSource).getRank() > subset.get(rootDestination).getRank()) {
      subset.get(rootDestination).setParent(rootSource);
    } else {
      subset.get(rootDestination).setParent(rootSource);
      subset.get(rootSource).increaseRank();
    }
  }

  private Graph<T> getMinimumSpanningTree(Graph<T> graphToConvert) {
    Graph<T> minimumSpanningTree = new DirectedGraph<>();
    Graph<T> graph = graphToConvert instanceof UndirectedGraph<T>
            ? cleanGraph(graphToConvert) : graphToConvert;
    subset = fillSubset(graph);
    for (Edge<T> edge : graph.getAllEdges()) {
      Node<T> source = find(edge.getSource());
      Node<T> destination = find(edge.getDestination());
      if (!source.equals(destination)) {
        minimumSpanningTree.addEdge(edge.getWeight(), edge.getSource(), edge.getDestination());
        union(source, destination);
        this.minimumCost += edge.getWeight();
      }
      if (minimumSpanningTree.getNumEdges() == graph.getNumNodes() - 1) {
        return minimumSpanningTree;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Edge<T> edge : graph.getAllEdges()) {
      sb.append(edge).append("\n");
    }
    sb.append("Minimum Cost: ").append(this.getMinimumCost());
    return sb.toString();
  }
}
