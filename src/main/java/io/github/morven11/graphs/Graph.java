package io.github.morven11.graphs;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This is Graph abstract class T -> extends Comparable T.
 *
 * @param <T> The Generic Parameter.
 */
public abstract class Graph<T extends Comparable<T>> {

  private final Map<Node<T>, Set<Edge<T>>> adjSets;
  private Integer numEdges;
  private Integer numNodes;

  protected Graph() {
    adjSets = new TreeMap<>();
    numEdges = 0;
    numNodes = 0;
  }

  public Integer getNumNodes() {
    return this.numNodes;
  }

  public Integer getNumEdges() {
    return this.numEdges;
  }

  public void increaseNumNodes() {
    this.numNodes++;
  }

  public void decreaseNumNodes() {
    this.numNodes--;
  }

  public void increaseNumEdges() {
    this.numEdges++;
  }

  public void decreaseNumEdges() {
    this.numEdges--;
  }

  public void decreaseAnAmountNumEdges(int num) {
    this.numEdges -= num;
  }

  /**
   * This method gives all the Edges there are exists in the
   * Adjacency Set.
   *
   * @return The Tree Set of them.
   */
  public Set<Edge<T>> getAllEdges() {
    Set<Edge<T>> allEdges = new TreeSet<>();
    for (Node<T> node : getAllNodes()) {
      Set<Edge<T>> edges = getEdgesNode(node);
      allEdges.addAll(edges);
    }
    return allEdges;
  }

  public Set<Node<T>> getAllNodes() {
    return adjSets.keySet();
  }

  public boolean containsNode(Node<T> node) {
    return adjSets.containsKey(node);
  }

  public Set<Edge<T>> getEdgesNode(Node<T> node) {
    return adjSets.get(node);
  }

  public Node<T> getNode(T node) {
    Node<T> nodeToFind = new Node<>(node);
    return this.getAllNodes().stream().filter(n -> n.equals(nodeToFind)).findFirst().orElse(null);
  }

  /**
   * This method add a Node to the Adjacency Set.
   *
   * @param newNode The new node to add.
   * @return True if the method can add the Node and False when not.
   */
  public boolean addNode(Node<T> newNode) {
    if (newNode == null || this.containsNode(newNode)) {
      return false;
    }
    Set<Edge<T>> newAdjacencySet = new TreeSet<>();
    adjSets.put(newNode, newAdjacencySet);
    this.increaseNumNodes();
    return true;
  }

  /**
   * This method remove if exists a Node into the Adjacency Set.
   *
   * @param node The node to remove.
   * @return True if the method can remove the Node and False when not.
   */
  public boolean removeNode(Node<T> node) {
    for (Set<Edge<T>> edges : this.adjSets.values()) {
      int count = this.adjSets.get(node).equals(edges)
              ? (int) edges.stream().filter(e -> e.getSource()
              .equals(node) || e.getDestination().equals(node)).count()
              : 1;
      boolean result = edges.removeIf(e -> e.getSource().equals(node)
              || e.getDestination().equals(node));
      if (result) {
        this.decreaseAnAmountNumEdges(count);
      }
    }
    this.adjSets.remove(node);
    this.decreaseNumNodes();
    return true;
  }

  /**
   * This method update the T element in a Node.
   *
   * @param node       The node to update.
   * @param newElement The new element T of the node.
   * @return True if the method can update the Node and False when not.
   */
  public boolean updateNode(Node<T> node, T newElement) {
    try {
      this.getAllNodes().stream()
              .filter(n -> n.equals(node)).findFirst().orElseThrow().setElement(newElement);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * This method get an Edge of the Adjacency Set.
   *
   * @param weight      The weight of the Edge.
   * @param source      The source of the Edge.
   * @param destination The destination of the Edge.
   * @return True if the method can get the Edge and False when not.
   */
  public Edge<T> getEdge(Integer weight, Node<T> source, Node<T> destination) {
    if (!this.containsNode(source)) {
      return null;
    }
    Edge<T> edge = new Edge<>(weight, source, destination);
    Set<Edge<T>> sourceEdges = this.getEdgesNode(source);
    boolean containsEdge = sourceEdges.stream().anyMatch(e -> e.equals(edge));
    if (!containsEdge) {
      return null;
    }
    return sourceEdges.stream().filter(e -> e.equals(edge)).findFirst().orElse(null);
  }

  public abstract boolean addEdge(Integer weight, Node<T> source, Node<T> destination);

  public abstract boolean removeEdge(Integer weight, Node<T> source, Node<T> destination);

  public abstract boolean updateEdge(Integer weight,
                                     Node<T> source, Node<T> destination, int newWeight);

  /**
   * This method add an Edge into the Adjacency Set.
   *
   * @param weight      The weight of the Edge.
   * @param source      The source of the Edge.
   * @param destination The destination of the Edge.
   * @return True if the method can add the Edge and False when not.
   */
  public boolean addEdgeFromTo(Integer weight, Node<T> source, Node<T> destination) {
    Edge<T> newEdge = new Edge<>(weight, source, destination);
    Set<Edge<T>> sourceEdges = this.getEdgesNode(source);
    boolean containsEdge = sourceEdges.stream().anyMatch(e -> e.equals(newEdge));
    if (containsEdge) {
      return false;
    }
    sourceEdges.add(newEdge);
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Node<T> node : getAllNodes()) {
      sb.append(node).append(" -> {");
      for (Edge<T> edge : getEdgesNode(node)) {
        sb.append(" [ ").append(edge).append(" ],");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append(" }\n");
    }
    return sb.toString();
  }
}
