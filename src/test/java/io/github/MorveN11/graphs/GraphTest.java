package io.github.MorveN11.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {

  private static Graph<String> graph;

  @BeforeEach
  void setup() {
    graph = new DirectedGraph<>();
    Node<String> sanFrancisco = new Node<>("San Francisco");
    Node<String> detroit = new Node<>("Detroit");
    Node<String> sanDiego = new Node<>("San Diego");
    graph.addEdge(5, sanFrancisco, detroit);
    graph.addEdge(3, sanFrancisco, sanDiego);
  }

  @Test
  void testIncreaseNumNodes() {
    graph.increaseNumNodes();
    assertEquals(4, graph.getNumNodes());
  }

  @Test
  void testDecreaseNumNodes() {
    graph.decreaseNumNodes();
    assertEquals(2, graph.getNumNodes());
  }

  @Test
  void testIncreaseNumEdges() {
    graph.increaseNumEdges();
    assertEquals(3, graph.getNumEdges());
  }

  @Test
  void testDecreaseNumEdges() {
    graph.decreaseNumEdges();
    assertEquals(1, graph.getNumEdges());
  }

  @Test
  void testDecreaseAnAmountNumEdges() {
    graph.decreaseAnAmountNumEdges(2);
    assertEquals(0, graph.getNumEdges());
  }
}
