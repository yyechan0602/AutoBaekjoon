import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 2];
        int[][] dp = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] + arr[i], dp[i][j]);
                if(i+j<=N) {
                    dp[i + j][0] = Math.max(dp[i+j][0], dp[i][j]);
                }
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i][0]);
        }

        int result = 0;

        for (int i = 0; i <= N; i++) {
            result = Math.max(result, dp[i][0]);
        }

        System.out.println(result);
    }
}