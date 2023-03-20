package io.github.morven11.algorithm;

import io.github.morven11.graphs.Node;

/**
 * This is Subset Class, This class has the Parent and Rank of a Node.
 *
 * @param <T> T the Generic Parameter.
 */
public class Subset<T extends Comparable<T>> {

  private Node<T> parent;
  private Integer rank;

  public Subset(Node<T> parent, Integer rank) {
    this.parent = parent;
    this.rank = rank;
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

  protected void increaseRank() {
    this.rank++;
  }
}
