package io.github.morven11.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UndirectedGraphEdgeTest {

  private static Graph<String> graph;
  private static int expectedNumberOfNodes;
  private static int expectedNumberOfEdges;

  @BeforeEach
  void setup() {
    graph = new UndirectedGraph<>();
    final Node<String> a = new Node<>("A");
    final Node<String> b = new Node<>("B");
    final Node<String> c = new Node<>("C");
    final Node<String> d = new Node<>("D");
    final Node<String> e = new Node<>("E");
    final Node<String> f = new Node<>("F");
    assertTrue(graph.addEdge(1, a, b));
    assertTrue(graph.addEdge(1, a, f));
    assertTrue(graph.addEdge(1, b, e));
    assertTrue(graph.addEdge(1, b, c));
    assertTrue(graph.addEdge(1, c, e));
    assertTrue(graph.addEdge(1, c, d));
    assertTrue(graph.addEdge(1, c, f));
    assertTrue(graph.addEdge(1, f, e));
    expectedNumberOfNodes = 6;
    expectedNumberOfEdges = 8;
  }

  @Test
  void testNumberOfEdges() {
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testAddEdge() {
    Node<String> o = new Node<>("O");
    Node<String> p = new Node<>("P");
    Edge<String> beforeAddEdge1 = graph.getEdge(4, o, p);
    Edge<String> beforeAddEdge2 = graph.getEdge(4, p, o);
    assertNull(beforeAddEdge1);
    assertNull(beforeAddEdge2);
    assertTrue(graph.addEdge(4, o, p));
    expectedNumberOfNodes += 2;
    expectedNumberOfEdges++;
    Edge<String> afterAddEdge1 = graph.getEdge(4, o, p);
    Edge<String> afterAddEdge2 = graph.getEdge(4, p, o);
    assertEquals("O -> P | weight: 4", afterAddEdge1.toString());
    assertEquals("P -> O | weight: 4", afterAddEdge2.toString());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testRemoveEdge() {
    Node<String> a = new Node<>("A");
    Node<String> b = new Node<>("B");
    Edge<String> beforeRemoveEdge1 = graph.getEdge(1, a, b);
    Edge<String> beforeRemoveEdge2 = graph.getEdge(1, b, a);
    assertEquals("A -> B | weight: 1", beforeRemoveEdge1.toString());
    assertEquals("B -> A | weight: 1", beforeRemoveEdge2.toString());
    assertTrue(graph.removeEdge(1, a, b));
    expectedNumberOfEdges--;
    Edge<String> afterRemoveEdge1 = graph.getEdge(1, a, b);
    Edge<String> afterRemoveEdge2 = graph.getEdge(1, b, a);
    assertNull(afterRemoveEdge1);
    assertNull(afterRemoveEdge2);
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testUpdateEdge() {
    Node<String> a = new Node<>("A");
    Node<String> b = new Node<>("B");
    Edge<String> beforeUpdateEdge1 = graph.getEdge(1, a, b);
    Edge<String> beforeUpdateEdge2 = graph.getEdge(1, b, a);
    assertEquals("A -> B | weight: 1", beforeUpdateEdge1.toString());
    assertEquals("B -> A | weight: 1", beforeUpdateEdge2.toString());
    assertTrue(graph.updateEdge(1, a, b, 5));
    Edge<String> afterUpdateEdge1 = graph.getEdge(1, a, b);
    Edge<String> afterUpdateEdge2 = graph.getEdge(1, b, a);
    assertNull(afterUpdateEdge1);
    assertNull(afterUpdateEdge2);
    Edge<String> verifyUpdateEdge1 = graph.getEdge(5, a, b);
    Edge<String> verifyUpdateEdge2 = graph.getEdge(5, b, a);
    assertEquals("A -> B | weight: 5", verifyUpdateEdge1.toString());
    assertEquals("B -> A | weight: 5", verifyUpdateEdge2.toString());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }
}
