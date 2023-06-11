import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][3][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) {
            dp[0][i][1] = dp[0][i][0];
            dp[0][i][2] = dp[0][i][0];
        }

        for (int i = 1; i < N; i++) {
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][1]) + dp[i][0][0];
            dp[i][0][2] = Math.min(dp[i - 1][0][2], dp[i - 1][1][2]) + dp[i][0][0];

            dp[i][1][1] = Math.max(Math.max(dp[i - 1][0][1], dp[i - 1][1][1]), dp[i - 1][2][1]) + dp[i][1][0];
            dp[i][1][2] = Math.min(Math.min(dp[i - 1][0][2], dp[i - 1][1][2]), dp[i - 1][2][2]) + dp[i][1][0];


            dp[i][2][1] = Math.max(dp[i - 1][1][1], dp[i - 1][2][1]) + dp[i][2][0];
            dp[i][2][2] = Math.min(dp[i - 1][1][2], dp[i - 1][2][2]) + dp[i][2][0];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(Math.max(Math.max(dp[N - 1][0][1], dp[N - 1][1][1]), dp[N - 1][2][1])).append(" ");
        sb.append(Math.min(Math.min(dp[N - 1][0][2], dp[N - 1][1][2]), dp[N - 1][2][2]));

        System.out.println(sb);
    }
}