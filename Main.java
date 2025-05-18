import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");

        graph.addEdge(a, b, 1.0);
        graph.addEdge(b, c, 2.0);
        graph.addEdge(a, c, 4.0);
        graph.addEdge(c, d, 1.0);

        Search<String> bfs = new BreadthFirstSearch<>(graph);
        List<Vertex<String>> bfsPath = bfs.findPath(a, d);
        System.out.println("BFS path from A to D: " + bfsPath);

        Search<String> dijkstra = new DijkstraSearch<>(graph);
        List<Vertex<String>> dijkstraPath = dijkstra.findPath(a, d);
        System.out.println("Dijkstra path from A to D: " + dijkstraPath);
    }
}