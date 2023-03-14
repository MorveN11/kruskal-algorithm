package salesiana.apr211.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import salesiana.apr211.graphs.Edge;
import salesiana.apr211.graphs.Graph;
import salesiana.apr211.graphs.Node;
import salesiana.apr211.graphs.UndirectedGraph;

class KruskalUndirectedGraphTest {

  private static final Node<Integer> node1 = new Node<>(1);
  private static final Node<Integer> node2 = new Node<>(2);
  private static final Node<Integer> node3 = new Node<>(3);
  private static final Node<Integer> node4 = new Node<>(4);
  private static final Node<Integer> node5 = new Node<>(5);
  private static final Node<Integer> node6 = new Node<>(6);
  private static Graph<Integer> graph;

  @BeforeEach
  void setup() {
    graph = new UndirectedGraph<>();
    assertTrue(graph.addEdge(3, node1, node2));
    assertTrue(graph.addEdge(5, node1, node5));
    assertTrue(graph.addEdge(2, node5, node6));
    assertTrue(graph.addEdge(7, node6, node4));
    assertTrue(graph.addEdge(9, node4, node3));
    assertTrue(graph.addEdge(5, node2, node3));
    assertTrue(graph.addEdge(6, node2, node5));
    assertTrue(graph.addEdge(3, node3, node6));
  }

  @Test
  void testKruskalAlgorithm() {
    Set<Edge<Integer>> expectedGraph = new TreeSet<>();
    expectedGraph.add(new Edge<>(2, node5, node6));
    expectedGraph.add(new Edge<>(3, node1, node2));
    expectedGraph.add(new Edge<>(3, node3, node6));
    expectedGraph.add(new Edge<>(5, node1, node5));
    expectedGraph.add(new Edge<>(7, node4, node6));
    assertEquals(6, graph.getNumNodes());
    assertEquals(16, graph.getNumEdges());
    Kruskal<Integer> kruskal = new Kruskal<>(graph);
    assertEquals(6, kruskal.getGraph().getNumNodes());
    assertEquals(5, kruskal.getGraph().getNumEdges());
    assertEquals(20, kruskal.getMinimumCost());
    assertEquals(expectedGraph, kruskal.getGraph().getAllEdges());
  }
}
