package io.github.MorveN11.algorithm;

import io.github.MorveN11.graphs.Node;

/**
 * This is Subset Class, This class has the Parent and Rank of a Node.
 *
 * @param <T> T the Generic Parameter.
 */
public class Subset<T extends Comparable<T>> {

  private Node<T> parent;
  private Integer rank;

  public Subset(Node<T> parent) {
    this.parent = parent;
    this.rank = 0;
  }

  public Node<T> getParent() {
    return this.parent;
  }

  public void setParent(Node<T> parent) {
    this.parent = parent;
  }

  public Integer getRank() {
    return this.rank;
  }

  public void increaseRank() {
    this.rank++;
  }
}
