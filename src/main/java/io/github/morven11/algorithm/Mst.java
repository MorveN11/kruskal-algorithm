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
public class Mst<T extends Comparable<T>> {

  private final Graph<T> graph;
  private Map<Node<T>, Subset<T>> subsets;
  private Integer minimumCost;

  /**
   * This is the Constructor of Kruskal class.
   *
   * @param graph The graph to convert into a minimum Spanning Tree.
   */
  public Mst(Graph<T> graph) {
    this.subsets = new TreeMap<>();
    this.minimumCost = 0;
    this.graph = kruskal(graph);
  }

  /**
   * This class gives the cost of the Spanning Tree.
   *
   * @return The minimum cost of the Spanning Tree.
   */
  public Integer getMinimumCost() {
    return minimumCost;
  }

  /**
   * This method gives the graph of the MST.
   *
   * @return The Graph.
   */
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
    Map<Node<T>, Subset<T>> tmpSubsets = new TreeMap<>();
    for (Node<T> node : graph.getAllNodes()) {
      tmpSubsets.put(node, new Subset<>(node, 0));
    }
    return tmpSubsets;
  }

  private Node<T> find(Node<T> node) {
    if (subsets.get(node).getParent() == node) {
      return node;
    }
    return find(subsets.get(node).getParent());
  }

  private void union(Node<T> source, Node<T> destination) {
    Node<T> rootSource = find(source);
    Node<T> rootDestination = find(destination);
    if (subsets.get(rootSource).getRank() < subsets.get(rootDestination).getRank()) {
      subsets.get(rootSource).setParent(rootDestination);
    } else if (subsets.get(rootSource).getRank() > subsets.get(rootDestination).getRank()) {
      subsets.get(rootDestination).setParent(rootSource);
    } else {
      subsets.get(rootDestination).setParent(rootSource);
      subsets.get(rootSource).increaseRank();
    }
  }

  private Graph<T> convertToUndirectGraph(Graph<T> graphToConvert) {
    Graph<T> undirectedGraph = new UndirectedGraph<>();
    for (Edge<T> edge : graphToConvert.getAllEdges()) {
      undirectedGraph.addEdge(edge.getWeight(), edge.getSource(), edge.getDestination());
    }
    return undirectedGraph;
  }

  private Graph<T> kruskal(Graph<T> graphToConvert) {
    Graph<T> mst = new UndirectedGraph<>();
    graphToConvert = graphToConvert instanceof DirectedGraph<T>
            ? convertToUndirectGraph(graphToConvert) : graphToConvert;
    Graph<T> cleanGraph = cleanGraph(graphToConvert);
    subsets = fillSubset(cleanGraph);
    for (Edge<T> edge : cleanGraph.getAllEdges()) {
      Node<T> source = find(edge.getSource());
      Node<T> destination = find(edge.getDestination());
      if (!source.equals(destination)) {
        mst.addEdge(edge.getWeight(), edge.getSource(), edge.getDestination());
        union(source, destination);
        this.minimumCost += edge.getWeight();
      }
      if (mst.getNumEdges() == cleanGraph.getNumNodes() - 1) {
        return mst;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.graph.toString()
            + "Minimum Cost: " + this.getMinimumCost();
  }
}
