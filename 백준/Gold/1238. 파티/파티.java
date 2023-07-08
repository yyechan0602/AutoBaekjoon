import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static final int INF = Integer.MAX_VALUE;

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (this.weight < o.weight) return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        List<ArrayList<Node>> returnlist = new ArrayList<>();
        List<ArrayList<Node>> golist = new ArrayList<>();
        int[] returnDist = new int[N + 1];
        int[] goDist = new int[N + 1];
        Arrays.fill(returnDist, INF);
        Arrays.fill(goDist, INF);

        for (int i = 0; i <= N; i++) {
            returnlist.add(new ArrayList<>());
            golist.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            returnlist.get(start).add(new Node(end, time));
            golist.get(end).add(new Node(start, time));
        }

        Dijkstra(X, returnlist, returnDist);
        Dijkstra(X, golist, goDist);

        int max = 0;

        for (int i = 1; i <= N; i++) {
            max = Math.max(returnDist[i] + goDist[i], max);
        }
        System.out.println(max);
    }

    static void Dijkstra(int start, List<ArrayList<Node>> list, int[] dist) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;

            if (visited[cur]) continue;
            visited[cur] = true;

            for (Node node :
                    list.get(cur)) {
                if (!visited[node.end] && dist[node.end] > dist[cur] + node.weight) {
                    dist[node.end] = dist[cur] + node.weight;
                    pq.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}