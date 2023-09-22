import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class distance implements Comparable<distance> {
        int from;
        int to;
        int dist;

        public distance(int from, int to) {
            this.from = from;
            this.to = to;
            this.dist = (int) Math.pow(field[from][0] - field[to][0], 2) + (int) Math.pow(field[from][1] - field[to][1], 2);
        }

        @Override
        public int compareTo(distance o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static int[] parent;
    private static int[][] field;
    private static int N;
    private static int C;
    private static int  result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        parent = new int[N];
        PriorityQueue<distance> pq = new PriorityQueue<>();
        field = new int[N][2];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            field[i][0] = Integer.parseInt(st.nextToken());
            field[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                if ((int) Math.pow(field[i][0] - field[j][0], 2) + (int) Math.pow(field[i][1] - field[j][1], 2) < C)
                    continue;
                pq.add(new distance(i, j));
            }
        }

        make();
        int count = 0;

        while (!pq.isEmpty()) {
            distance cur = pq.poll();
            if (union(cur.from, cur.to)) {
                result += cur.dist;
                count++;
            }

            if(count==N-1){
                break;
            }
        }

        if(count!=N-1){
            System.out.println(-1);
            return;
        }

        System.out.println(result);
    }

    private static void make() {
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int from, int to) {
        int fromRoot = find(from);
        int toRoot = find(to);

        if (fromRoot == toRoot) return false;
        parent[toRoot] = fromRoot;
        return true;
    }
}