import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] buildings = new int[N][2];
            int[] dp = new int[N];
            Queue<Integer>[] order = new LinkedList[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                buildings[i][0] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                order[i] = new LinkedList<>();
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                order[start].add(end);
                buildings[end][1]++;
            }

            int W = Integer.parseInt(br.readLine())-1;
            Queue<Integer> q = new LinkedList<>();
            boolean flag = true;

            while (flag) {
                flag = false;
                for (int i = 0; i < N; i++) {
                    if (buildings[i][1] == 0) {
                        q.add(i);
                        buildings[i][1] = -1;
                        flag = true;
                    }
                }

                while (!q.isEmpty()) {
                    int cur = q.poll();

                    while (!order[cur].isEmpty()) {
                        int next = order[cur].poll();

                        dp[next] = Math.max(dp[next], dp[cur] + buildings[cur][0]);
                        buildings[next][1]--;
                    }
                }
            }

            System.out.println(dp[W] + buildings[W][0]);
        }
    }
}