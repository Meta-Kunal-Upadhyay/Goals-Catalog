import java.util.List;

public interface Graph {
    boolean isConnected();
    List<Integer> reachable(int a);
    List<Edge> mst();
    List<Integer> shortestPath(int a, int b);
}
