import java.util.*;

public class UndirectedWeightedGraph implements Graph {
    private final Map<Integer, List<Edge>> adjList = new HashMap<>();

    public void addEdge(int u, int v, int w) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(new Edge(u, v, w));
        adjList.get(v).add(new Edge(v, u, w));
    }   

    @Override
    public boolean isConnected() {
        if (adjList.isEmpty()) return true;

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Integer start = adjList.keySet().iterator().next();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                for (Edge edge : adjList.get(node)) {
                    if (!visited.contains(edge.dest)) {
                        stack.push(edge.dest);
                    }
                }
            }
        }
        return visited.size() == adjList.size();
    }

    @Override
    public List<Integer> reachable(int a) {
        List<Integer> reachable = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(a);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                reachable.add(node);
                for (Edge edge : adjList.getOrDefault(node, new ArrayList<>())) {
                    if (!visited.contains(edge.dest)) {
                        stack.push(edge.dest);
                    }
                }
            }
        }
        return reachable;
    }

    @Override
    public List<Edge> mst() {
        List<Edge> result = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        Integer start = adjList.keySet().iterator().next();

        visited.add(start);
        pq.addAll(adjList.get(start));

        while (!pq.isEmpty() && visited.size() < adjList.size()) {
            Edge edge = pq.poll();
            if (!visited.contains(edge.dest)) {
                visited.add(edge.dest);
                result.add(edge);
                for (Edge next : adjList.get(edge.dest)) {
                    if (!visited.contains(next.dest)) {
                        pq.add(next);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Integer> shortestPath(int a, int b) {
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        Set<Integer> visited = new HashSet<>();

        for (Integer node : adjList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(a, 0);
        pq.add(new int[]{a, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int cost = current[1];

            if (visited.contains(node)) continue;
            visited.add(node);

            for (Edge edge : adjList.get(node)) {
                int neighbor = edge.dest;
                int newDist = cost + edge.weight;
                if (newDist < dist.get(neighbor)) {
                    dist.put(neighbor, newDist);
                    prev.put(neighbor, node);
                    pq.add(new int[]{neighbor, newDist});
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