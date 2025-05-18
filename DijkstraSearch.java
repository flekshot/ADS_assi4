import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> findPath(Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap<>();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<Vertex<V>> visited = new HashSet<>();

        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        pq.add(start);
        parentMap.put(start, null);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            if (current.equals(end)) {
                break;
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (Edge<Vertex<V>> edge : graph.getEdges(current)) {
                Vertex<V> neighbor = edge.getDest();
                if (visited.contains(neighbor)) {
                    continue;
                }

                double newDist = distances.get(current) + edge.getWeight();
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    parentMap.put(neighbor, current);
                    pq.add(neighbor);
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