import java.util.*;

public class EdgeListGraph implements Graph {
    private final List<Edge> edges = new ArrayList<>();
    private final Set<Integer> vertices = new HashSet<>();

    public void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
        vertices.add(u);
        vertices.add(v);
    }

    // Helper: Build adjacency list for DFS-based methods
    private Map<Integer, List<Integer>> buildAdjacencyList() {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (Edge e : edges) {
            adj.putIfAbsent(e.src, new ArrayList<>());
            adj.putIfAbsent(e.dest, new ArrayList<>());
            adj.get(e.src).add(e.dest);
            adj.get(e.dest).add(e.src); // undirected
        }
        return adj;
    }

    @Override
    public boolean isConnected() {
        if (vertices.isEmpty()) return true;

        Map<Integer, List<Integer>> adj = buildAdjacencyList();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Integer start = vertices.iterator().next();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return visited.size() == vertices.size();
    }

    @Override
    public List<Integer> reachable(int a) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> adj = buildAdjacencyList();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(a);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);
                for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Edge> mst() {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges); // sort edges by weight

        Map<Integer, Integer> parent = new HashMap<>();
        for (int v : vertices) parent.put(v, v);

        for (Edge e : edges) {
            int root1 = find(parent, e.src);
            int root2 = find(parent, e.dest);

            if (root1 != root2) {
                mst.add(e);
                union(parent, root1, root2);
            }

            if (mst.size() == vertices.size() - 1) break;
        }

        return mst;
    }

    private int find(Map<Integer, Integer> parent, int node) {
        if (parent.get(node) != node) {
            parent.put(node, find(parent, parent.get(node))); // path compression
        }
        return parent.get(node);
    }

    private void union(Map<Integer, Integer> parent, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);
        parent.put(rootU, rootV);
    }

    @Override
    public List<Integer> shortestPath(int a, int b) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (Edge e : edges) {
            adj.putIfAbsent(e.src, new ArrayList<>());
            adj.putIfAbsent(e.dest, new ArrayList<>());
            adj.get(e.src).add(new int[]{e.dest, e.weight});
            adj.get(e.dest).add(new int[]{e.src, e.weight});
        }

        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));

        for (int v : vertices) dist.put(v, Integer.MAX_VALUE);
        dist.put(a, 0);
        pq.add(new int[]{a, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            for (int[] neighbor : adj.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor[0];
                int weight = neighbor[1];
                int newDist = d + weight;

                if (newDist < dist.get(v)) {
                    dist.put(v, newDist);
                    prev.put(v, u);
                    pq.add(new int[]{v, newDist});
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        if (!dist.containsKey(b) || dist.get(b) == Integer.MAX_VALUE) return path;

        for (Integer at = b; at != null; at = prev.get(at)) {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
    }
}
