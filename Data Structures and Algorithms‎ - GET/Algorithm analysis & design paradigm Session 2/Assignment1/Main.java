public class Main {
    public static void main(String[] args) {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
        
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 3, 5);

        System.out.println("Is Connected: " + graph.isConnected());
        System.out.println("Reachable from 0: " + graph.reachable(0));
        System.out.println("MST: ");
        for (Edge e : graph.mst()) {
            System.out.println(e.src + " - " + e.dest + " : " + e.weight);
        }
        System.out.println("Shortest path from 0 to 3: " + graph.shortestPath(0, 3));
    }
}