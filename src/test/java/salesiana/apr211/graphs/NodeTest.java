package salesiana.apr211.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NodeTest {

  @Test
  void testSetNode() {
    Node<String> node = new Node<>("San Diego");
    node.setElement("San Francisco");
    assertEquals("San Francisco", node.getElement());
  }
}
