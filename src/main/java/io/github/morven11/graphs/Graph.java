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

  /**
   * This is the constructor of the Graph class.
   * Initialize the map and the num of nodes and edges.
   */
  protected Graph() {
    adjSets = new TreeMap<>();
    numEdges = 0;
    numNodes = 0;
  }

  /**
   * This method gives the Adjacency Set of the Graph.
   *
   * @return The Adjacency Set.
   */
  public Map<Node<T>, Set<Edge<T>>> getAdjSets() {
    return this.adjSets;
  }

  /**
   * This method gives the Number of Nodes in the graph.
   *
   * @return The Number of Nodes.
   */
  public Integer getNumNodes() {
    return this.numNodes;
  }

  /**
   * This method gives the number of Edges in the Graph.
   *
   * @return The number of Edges.
   */
  public Integer getNumEdges() {
    return this.numEdges;
  }

  private void increaseNumNodes() {
    this.numNodes++;
  }

  /**
   * This method decrease in one the number of nodes in the Graph.
   */
  protected void decreaseNumNodes() {
    this.numNodes--;
  }

  /**
   * This method increase in one the number of nodes in the Graph.
   */
  protected void increaseNumEdges() {
    this.numEdges++;
  }

  /**
   * This method decrease in one the number of edges in the Graph.
   */
  protected void decreaseNumEdges() {
    this.numEdges--;
  }

  /**
   * This method decease a give number of Edges in the Graph.
   *
   * @param num The number to decrease.
   */
  protected void decreaseAnAmountNumEdges(int num) {
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

  /**
   * This method gives all nodes in the Graph.
   *
   * @return The nodes.
   */
  public Set<Node<T>> getAllNodes() {
    return adjSets.keySet();
  }

  /**
   * This method verify if a node exists or not in the Graph.
   *
   * @param node The node to search.
   * @return True if the Graph contains the node and False when not.
   */
  public boolean containsNode(Node<T> node) {
    return adjSets.containsKey(node);
  }

  /**
   * This method gives all the Edges of a source Node.
   *
   * @param node The source Node.
   * @return The Edges.
   */
  public Set<Edge<T>> getEdgesNode(Node<T> node) {
    return adjSets.get(node);
  }

  /**
   * This method gives a Node of the Graph.
   *
   * @param node The element of the node to Search.
   * @return The node.
   */
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
   * This method remove a Node of the Graph.
   *
   * @param node The node to Remove.
   * @return True if the method can remove the node and False when not.
   */
  public abstract boolean removeNode(Node<T> node);

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

  /**
   * This method add an Edge in the graph.
   *
   * @param weight      The weight of the Edge.
   * @param source      The source of the Edge.
   * @param destination The destination of the Edge.
   * @return True if the method can Add the Edge and False when not.
   */
  public abstract boolean addEdge(Integer weight, Node<T> source, Node<T> destination);

  /**
   * This method remove an Edge og the Graph.
   *
   * @param weight      The weight of the Edge.
   * @param source      The source of the Edge.
   * @param destination The destination of the Edge.
   * @return True if the method can remove the Edge and False when not.
   */
  public abstract boolean removeEdge(Integer weight, Node<T> source, Node<T> destination);

  /**
   * This method update and Edge of the Graph.
   *
   * @param weight      The weight of the Edge.
   * @param source      The source of the Edge.
   * @param destination The destination of the Edge.
   * @param newWeight   The New Weight of the Edge.
   * @return True if the method can update the Edge and False when not.
   */
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
  protected boolean addEdgeFromTo(Integer weight, Node<T> source, Node<T> destination) {
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

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    if (object == this) {
      return true;
    }
    Graph<?> obj = (Graph<?>) object;
    return this.getAllEdges().equals(obj.getAllEdges());
  }

  @Override
  public int hashCode() {
    return this.adjSets.hashCode();
  }
}
