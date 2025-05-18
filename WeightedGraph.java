import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<Edge<Vertex<V>>>> map;

    public WeightedGraph() {
        this.map = new HashMap<>();
    }

    public void addVertex(Vertex<V> vertex) {
        map.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Vertex<V> source, Vertex<V> dest, double weight) {
        addVertex(source);
        addVertex(dest);
        map.get(source).add(new Edge<>(source, dest, weight));
        map.get(dest).add(new Edge<>(dest, source, weight));
    }

    public List<Edge<Vertex<V>>> getEdges(Vertex<V> vertex) {
        return map.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Vertex<V>> getVertices() {
        return map.keySet();
    }
}