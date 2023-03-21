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

  /**
   * This is the constructor of the Subset class.
   *
   * @param parent The parent of the node.
   * @param rank   The rank of the node.
   */
  public Subset(Node<T> parent, Integer rank) {
    this.parent = parent;
    this.rank = rank;
  }

  /**
   * This method gives the Parent of the Subset.
   *
   * @return The Parent.
   */
  public Node<T> getParent() {
    return this.parent;
  }

  /**
   * This method update the parent of the Subset.
   *
   * @param parent The new Parent.
   */
  public void setParent(Node<T> parent) {
    this.parent = parent;
  }

  /**
   * This method gives the Rank of the subset.
   *
   * @return The Rank.
   */
  public Integer getRank() {
    return this.rank;
  }

  /**
   * This method increase in one the Rank
   * of the Subset.
   */
  protected void increaseRank() {
    this.rank++;
  }
}
