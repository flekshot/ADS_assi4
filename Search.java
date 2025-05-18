import java.util.List;

public interface Search<V> {
    List<Vertex<V>> findPath(Vertex<V> start, Vertex<V> end);
}