import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N = 날자수
        int M = Integer.parseInt(st.nextToken()); // M = 디저트 종류의 수
        int[][] dessert = new int[M][N];
        int[][] dp = new int[M][N];
        int max = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dessert[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            dp[i][0] = dessert[i][0];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < M; k++) {
                    if (j != k) {
                        dp[j][i] = Math.max(dp[k][i-1] + dessert[j][i], dp[j][i]);
                    } else {
                        dp[j][i] = Math.max(dp[k][i-1] + dessert[j][i] / 2, dp[j][i]);
                    }
                }
                //System.out.print(dp[j][i] + " ");
            }
            //System.out.println();
        }

        for (int i = 0; i < M; i++) {
            max = Math.max(dp[i][N - 1], max);
        }

        System.out.println(max);
    }
}