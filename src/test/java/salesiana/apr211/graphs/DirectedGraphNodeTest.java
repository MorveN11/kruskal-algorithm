package salesiana.apr211.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DirectedGraphNodeTest {

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
    final Node<String> nYork = new Node<>("New York");
    final Node<String> sanFrancisco = new Node<>("San Francisco");
    assertTrue(graph.addEdge(1, chicago, boston));
    assertTrue(graph.addEdge(1, boston, chicago));
    assertTrue(graph.addEdge(1, detroit, chicago));
    assertTrue(graph.addEdge(2, philadelphia, boston));
    assertTrue(graph.addEdge(1, philadelphia, detroit));
    assertTrue(graph.addEdge(2, nYork, philadelphia));
    assertTrue(graph.addEdge(2, nYork, boston));
    assertTrue(graph.addEdge(1, nYork, sanFrancisco));
    assertTrue(graph.addEdge(2, sanFrancisco, philadelphia));
    expectedNumberOfNodes = 6;
    expectedNumberOfEdges = 9;
  }

  @Test
  void testNumberOfNodes() {
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
  }

  @Test
  void testAddNode() {
    Node<String> sanDiego = new Node<>("San Diego");
    Node<String> beforeAddNode = graph.getNode("San Diego");
    assertNull(beforeAddNode);
    assertTrue(graph.addNode(sanDiego));
    expectedNumberOfNodes++;
    Node<String> afterAddNode = graph.getNode("San Diego");
    assertEquals("San Diego", afterAddNode.getElement());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testRemoveNode() {
    Node<String> detroit = new Node<>("Detroit");
    Node<String> beforeRemoveNode = graph.getNode("Detroit");
    assertEquals("Detroit", beforeRemoveNode.getElement());
    assertTrue(graph.removeNode(detroit));
    expectedNumberOfNodes--;
    expectedNumberOfEdges -= 2;
    Node<String> afterRemoveNode = graph.getNode("Detroit");
    assertNull(afterRemoveNode);
    Set<Edge<String>> allEdgesGraph = graph.getAllEdges();
    for (Edge<String> edge : allEdgesGraph) {
      Node<String> source = edge.getSource();
      Node<String> destination = edge.getDestination();
      assertNotEquals(source, detroit);
      assertNotEquals(destination, detroit);
    }
    assertFalse(graph.containsNode(detroit));
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }

  @Test
  void testUpdateNode() {
    Node<String> detroit = new Node<>("Detroit");
    Node<String> beforeUpdateNode = graph.getNode("Detroit");
    assertEquals("Detroit", beforeUpdateNode.getElement());
    assertTrue(graph.updateNode(detroit, "San Diego"));
    Node<String> afterUpdateNode = graph.getNode("Detroit");
    assertNull(afterUpdateNode);
    Node<String> verifyUpdateNode = graph.getNode("San Diego");
    assertEquals("San Diego", verifyUpdateNode.getElement());
    assertEquals(expectedNumberOfNodes, graph.getNumNodes());
    assertEquals(expectedNumberOfEdges, graph.getNumEdges());
  }
}
