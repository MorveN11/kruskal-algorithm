package salesiana.apr211.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UndirectedGraphNodeTest {

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
    expectedNumberOfEdges = 16;
  }

  @Test
  void testNumberOfNodes() {
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
  }

  @Test
  void testAddNode() {
    Node<String> o = new Node<>("O");
    Node<String> beforeAddNode = graph.getNode("O");
    assertNull(beforeAddNode);
    assertTrue(graph.addNode(o));
    expectedNumberOfNodes++;
    Node<String> afterAddNode = graph.getNode("O");
    assertEquals("O", afterAddNode.getElement());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testRemoveNode() {
    Node<String> a = new Node<>("A");
    Node<String> beforeRemoveNode = graph.getNode("A");
    assertEquals("A", beforeRemoveNode.getElement());
    assertTrue(graph.removeNode(a));
    expectedNumberOfNodes--;
    expectedNumberOfEdges -= 4;
    Node<String> afterRemoveNode = graph.getNode("A");
    assertNull(afterRemoveNode);
    Set<Edge<String>> allEdgesGraph = graph.getAllEdges();
    for (Edge<String> edge : allEdgesGraph) {
      Node<String> source = edge.getSource();
      Node<String> destination = edge.getDestination();
      assertNotEquals(source, a);
      assertNotEquals(destination, a);
    }
    assertFalse(graph.containsNode(a));
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testUpdateNode() {
    Node<String> a = new Node<>("A");
    Node<String> beforeUpdateNode = graph.getNode("A");
    assertEquals("A", beforeUpdateNode.getElement());
    assertTrue(graph.updateNode(a, "Z"));
    Node<String> afterUpdateNode = graph.getNode("A");
    assertNull(afterUpdateNode);
    Node<String> verifyUpdateNode = graph.getNode("Z");
    assertEquals("Z", verifyUpdateNode.getElement());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }
}
