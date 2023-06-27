import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) return -1;
            return 1;
        }
    }

    static int[] parents;
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parents = new int[V];
        PriorityQueue<Edge> pQ = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            pQ.add(new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }

        int length = 0;
        int weight = 0;
        make();

        while (!pQ.isEmpty()) {
            Edge cur = pQ.poll();
            if (union(cur.v1, cur.v2)) {
                weight += cur.weight;
                length++;
            }

            if (length == V - 1) break;
        }

        System.out.println(weight);
    }

    static boolean union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return false;

        parents[toRoot] = fromRoot;
        return true;
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
    }

    static void make() {
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }
}