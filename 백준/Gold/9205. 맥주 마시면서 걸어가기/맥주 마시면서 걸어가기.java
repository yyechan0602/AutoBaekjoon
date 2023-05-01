import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            BFS(br);
        }
    }

    static void BFS(BufferedReader br) throws IOException {
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] store = new int[n][2];
        boolean[] visited = new boolean[n];
        LinkedList<Integer[]> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        q.add(new Integer[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            store[i][0] = Integer.parseInt(st.nextToken());
            store[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] festival = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            int dis = Math.abs(festival[0] - cur[0]);

            if (Math.abs(festival[1] - cur[1]) <= 1000 - dis) {
                System.out.println("happy");
                return;
            }

            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                dis = Math.abs(store[i][0] - cur[0]);

                if (Math.abs(store[i][1] - cur[1]) <= 1000 - dis) {
                    visited[i] = true;
                    q.add(new Integer[]{store[i][0], store[i][1]});
                }
            }
        }

        System.out.println("sad");
    }
}