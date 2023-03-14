package io.github.MorveN11.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EdgeTest {

  private static Edge<String> edge;

  @BeforeEach
  void setup() {
    final Node<String> sanFrancisco = new Node<>("San Francisco");
    final Node<String> philadelphia = new Node<>("Philadelphia");
    edge = new Edge<>(4, sanFrancisco, philadelphia);
  }

  @Test
  void testGetWeight() {
    assertEquals(4, edge.getWeight());
  }

  @Test
  void testSetWeight() {
    edge.setWeight(8);
    assertEquals(8, edge.getWeight());
  }
}
