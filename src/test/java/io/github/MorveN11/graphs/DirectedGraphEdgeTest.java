package io.github.MorveN11.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectedGraphEdgeTest {

  private static Graph<String> graph;
  private static int expectedNumberOfNodes;
  private static int expectedNumberOfEdges;

  @BeforeEach
  void setup() {
    graph = new DirectedGraph<>();
    final Node<String> detroit = new Node<>("Detroit");
    final Node<String> chicago = new Node<>("Chicago");
    final Node<String> philadelphia = new Node<>("Philadelphia");
    final Node<String> boston = new Node<>("Boston");
    final Node<String> newYork = new Node<>("New York");
    final Node<String> sanFrancisco = new Node<>("San Francisco");
    assertTrue(graph.addEdge(1, chicago, boston));
    assertTrue(graph.addEdge(1, boston, chicago));
    assertTrue(graph.addEdge(1, detroit, chicago));
    assertTrue(graph.addEdge(2, philadelphia, boston));
    assertTrue(graph.addEdge(1, philadelphia, detroit));
    assertTrue(graph.addEdge(2, newYork, philadelphia));
    assertTrue(graph.addEdge(2, newYork, boston));
    assertTrue(graph.addEdge(1, newYork, sanFrancisco));
    assertTrue(graph.addEdge(2, sanFrancisco, philadelphia));
    expectedNumberOfNodes = 6;
    expectedNumberOfEdges = 9;
  }

  @Test
  void testNumberOfEdges() {
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testAddEdge() {
    Node<String> sanFrancisco = new Node<>("San Francisco");
    Node<String> sanDiego = new Node<>("San Diego");
    Edge<String> beforeAddEdge = graph.getEdge(4, sanFrancisco, sanDiego);
    assertNull(beforeAddEdge);
    assertTrue(graph.addEdge(4, sanFrancisco, sanDiego));
    expectedNumberOfNodes++;
    expectedNumberOfEdges++;
    Edge<String> afterAddEdge = graph.getEdge(4, sanFrancisco, sanDiego);
    assertEquals("San Francisco -> San Diego | weight: 4", afterAddEdge.toString());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testRemoveEdge() {
    Node<String> sanFrancisco = new Node<>("San Francisco");
    Node<String> newYork = new Node<>("New York");
    Edge<String> beforeRemoveEdge = graph.getEdge(1, newYork, sanFrancisco);
    assertEquals("New York -> San Francisco | weight: 1", beforeRemoveEdge.toString());
    assertTrue(graph.removeEdge(1, newYork, sanFrancisco));
    expectedNumberOfEdges--;
    Edge<String> afterRemoveEdge = graph.getEdge(1, newYork, sanFrancisco);
    assertNull(afterRemoveEdge);
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testUpdateEdge() {
    Node<String> sanFrancisco = new Node<>("San Francisco");
    Node<String> newYork = new Node<>("New York");
    Edge<String> beforeUpdateEdge = graph.getEdge(1, newYork, sanFrancisco);
    assertEquals("New York -> San Francisco | weight: 1", beforeUpdateEdge.toString());
    assertTrue(graph.updateEdge(1, newYork, sanFrancisco, 5));
    Edge<String> afterUpdateEdge = graph.getEdge(1, newYork, sanFrancisco);
    assertNull(afterUpdateEdge);
    Edge<String> verifyUpdateEdge = graph.getEdge(5, newYork, sanFrancisco);
    assertEquals("New York -> San Francisco | weight: 5", verifyUpdateEdge.toString());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }
}
