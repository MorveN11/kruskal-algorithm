package salesiana.apr211.graphs;

import java.util.Set;

/**
 * This is UndirectedGraph class T -> extends Comparable T and
 * UndirectedGraph extends Graph T.
 *
 * @param <T> The Generic Parameter.
 */
public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

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
        super.decreaseNumEdges();
      }
      boolean containsE2 = edges.stream().anyMatch(e -> e.equals(e2));
      if (!result2 && containsE2) {
        result2 = edges.removeIf(e -> e.equals(e2));
        super.decreaseNumEdges();
      }
      if (result1 && result2) {
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
}
