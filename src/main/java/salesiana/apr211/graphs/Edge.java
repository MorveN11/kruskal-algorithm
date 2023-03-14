package salesiana.apr211.graphs;

/**
 * This is Edge class T extends -> Comparable T
 * and Edge implements Comparable Edge T.
 *
 * @param <T> The generic parameter.
 */
public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

  private final Node<T> source;
  private final Node<T> destination;
  private Integer weight;

  /**
   * This is the constructor for Edge class.
   * Source -> Destination | Weight.
   *
   * @param weight      the weight of the Edge.
   * @param source      the source of the Edge.
   * @param destination the destination of the Edge.
   */
  public Edge(Integer weight, Node<T> source, Node<T> destination) {
    this.weight = weight;
    this.source = source;
    this.destination = destination;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Node<T> getDestination() {
    return destination;
  }

  public Node<T> getSource() {
    return source;
  }

  @Override
  public String toString() {
    return this.source.toString() + " -> "
            + this.destination.toString() + " | " + "weight: " + this.weight;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    if (object == this) {
      return true;
    }
    Edge<?> obj = (Edge<?>) object;
    return this.source.equals(obj.getSource()) && this.destination.equals(obj.getDestination())
            && this.weight.equals(obj.getWeight());
  }

  @Override
  public int hashCode() {
    return this.source.hashCode() + this.destination.hashCode() + this.weight.hashCode();
  }

  @Override
  public int compareTo(Edge<T> o) {
    if (!this.weight.equals(o.getWeight()) && this.source == o.getSource()
            && this.destination == o.getDestination()) {
      return 0;
    }
    if (!this.weight.equals(o.getWeight())) {
      return this.weight - o.getWeight();
    }
    if (this.source != o.getSource() && this.destination != o.getDestination()) {
      return this.source.compareTo(o.getSource());
    }
    if (this.source == o.getSource() && this.destination != o.getDestination()) {
      return this.destination.compareTo(o.getDestination());
    }
    if (this.destination == o.getDestination() && this.source != o.getSource()) {
      return this.source.compareTo(o.getDestination());
    }
    return 0;
  }
}
