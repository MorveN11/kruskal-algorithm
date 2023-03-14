package io.github.morven11.graphs;

/**
 * This is Node class T extends Comparable T and
 * Node implements Comparable Node T.
 *
 * @param <T> The Generic Parameter.
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

  private T element;

  public Node(T element) {
    this.element = element;
  }

  public T getElement() {
    return this.element;
  }

  public void setElement(T element) {
    this.element = element;
  }

  @Override
  public String toString() {
    return this.element.toString();
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    Node<?> obj = (Node<?>) object;
    return this.element.equals(obj.getElement());
  }

  @Override
  public int hashCode() {
    return this.element.hashCode();
  }

  @Override
  public int compareTo(Node<T> o) {
    return this.element.compareTo(o.getElement());
  }
}
