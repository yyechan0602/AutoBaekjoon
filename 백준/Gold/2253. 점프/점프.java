import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_JUMP = 300;
    private static final int INIT = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][MAX_JUMP + 1];
        boolean[] stones = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], INIT);
        }
        dp[1][0] = 0;
        for (int i = 0; i < M; i++) {
            stones[Integer.parseInt(br.readLine())] = true;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < MAX_JUMP; j++) {
                if (i + j + 1 < N + 1 && !stones[i + j + 1]) {
                    dp[i + j + 1][j + 1] = Math.min(dp[i][j] + 1, dp[i + j + 1][j + 1]);
                }
                if (i + j < N + 1 && !stones[i + j]) {
                    dp[i + j][j] = Math.min(dp[i][j] + 1, dp[i + j][j]);
                }
                if (i + j - 1 < N + 1 && j - 1 > 0 && !stones[i + j - 1]) {
                    dp[i + j - 1][j - 1] = Math.min(dp[i][j] + 1, dp[i + j - 1][j - 1]);
                }
            }

/*            for (int k = 0; k < N; k++) {
                for (int j = 0; j < 30; j++) {
                    if (dp[k][j] == INIT) System.out.print(". ");
                    else System.out.print(dp[k][j] + " ");
                }
                System.out.println();
            }
            System.out.println("=====================");*/
        }

        int result = INIT;

        for (int i = 0; i < MAX_JUMP; i++) {
            result = Math.min(result, dp[N][i]);
        }

/*        for (int i = 0; i < N + 1; i++) {
            System.out.print(i);
            for (int j = 0; j < 30; j++) {
                if (dp[i][j] == INIT) System.out.print(". ");
                else System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=====================");*/

        if (result == INIT) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }
}