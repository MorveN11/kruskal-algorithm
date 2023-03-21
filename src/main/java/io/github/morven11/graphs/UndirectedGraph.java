package io.github.morven11.graphs;

import java.util.Set;

/**
 * This is UndirectedGraph class T -> extends Comparable T and
 * UndirectedGraph extends Graph T.
 *
 * @param <T> The Generic Parameter.
 */
public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

  /**
   * This is the constructor of the UndirectedGraph.
   */
  public UndirectedGraph() {
    super();
  }

  @Override
  public boolean addEdge(Integer weight, Node<T> source, Node<T> destination) {
    addNode(source);
    addNode(destination);
    if (!addEdgeFromTo(weight, source, destination)
            || !addEdgeFromTo(weight, destination, source)) {
      return false;
    }
    super.increaseNumEdges();
    return true;
  }

  @Override
  public boolean removeEdge(Integer weight, Node<T> source, Node<T> destination) {
    Edge<T> e1 = new Edge<>(weight, source, destination);
    Edge<T> e2 = new Edge<>(weight, destination, source);
    boolean result1 = false;
    boolean result2 = false;
    for (Node<T> node : super.getAllNodes()) {
      Set<Edge<T>> edges = super.getEdgesNode(node);
      boolean containsE1 = edges.stream().anyMatch(e -> e.equals(e1));
      if (!result1 && containsE1) {
        result1 = edges.removeIf(e -> e.equals(e1));
      }
      boolean containsE2 = edges.stream().anyMatch(e -> e.equals(e2));
      if (!result2 && containsE2) {
        result2 = edges.removeIf(e -> e.equals(e2));
      }
      if (result1 && result2) {
        super.decreaseNumEdges();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateEdge(Integer weight, Node<T> source, Node<T> destination, int newWeight) {
    Edge<T> e1 = new Edge<>(weight, source, destination);
    Edge<T> e2 = new Edge<>(weight, destination, source);
    boolean result1 = false;
    boolean result2 = false;
    for (Node<T> current : super.getAllNodes()) {
      Set<Edge<T>> edges = super.getEdgesNode(current);
      boolean containsE1 = edges.stream().anyMatch(e -> e.equals(e1));
      if (!result1 && containsE1) {
        edges.stream().filter(e -> e.equals(e1)).findFirst().orElseThrow().setWeight(newWeight);
        result1 = true;
      }
      boolean containsE2 = edges.stream().anyMatch(e -> e.equals(e2));
      if (!result2 && containsE2) {
        edges.stream().filter(e -> e.equals(e2)).findFirst().orElseThrow().setWeight(newWeight);
        result2 = true;
      }
      if (result1 && result2) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean removeNode(Node<T> node) {
    if (!super.containsNode(node)) {
      return false;
    }
    int count = 0;
    for (Node<T> source : super.getAllNodes()) {
      if (!source.equals(node)) {
        continue;
      }
      Set<Edge<T>> edges = this.getEdgesNode(source);
      count = edges.size();
      for (Edge<T> edge : edges) {
        Integer weight = edge.getWeight();
        Node<T> destination = edge.getDestination();
        Edge<T> imageEdge = new Edge<>(weight, destination, source);
        super.getEdgesNode(destination).remove(imageEdge);
        super.getAdjSets().get(destination).remove(imageEdge);
      }
    }
    super.getAdjSets().remove(node);
    super.decreaseAnAmountNumEdges(count);
    super.decreaseNumNodes();
    return true;
  }
}
