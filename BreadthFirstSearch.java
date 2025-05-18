import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> findPath(Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        Queue<Vertex<V>> queue = new LinkedList<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();
            if (current.equals(end)) {
                break;
            }

            for (Edge<Vertex<V>> edge : graph.getEdges(current)) {
                Vertex<V> neighbor = edge.getDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return reconstructPath(parentMap, start, end);
    }

    private List<Vertex<V>> reconstructPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> start, Vertex<V> end) {
        List<Vertex<V>> path = new ArrayList<>();
        Vertex<V> current = end;

        if (!parentMap.containsKey(end)) {
            return path;
        }

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}