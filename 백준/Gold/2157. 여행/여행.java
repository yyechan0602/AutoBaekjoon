import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] airport = new int[N][N];
        int[][] dp = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            airport[start][end] = Math.max(airport[start][end], Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                for (int k = 1; k < M; k++) {
                    if (dp[i][k - 1] == -1 || airport[i][j] == 0) continue;

                    dp[j][k] = Math.max(dp[i][k - 1] + airport[i][j], dp[j][k]);
                }
            }
        }

        int result = 0;

        for (int i = 0; i < M; i++) {
            result = Math.max(result, dp[N - 1][i]);
        }

        System.out.println(result);
    }
}