import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] nums;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        MakeDP();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]) {
                bw.write(1 + "\n");
            } else {
                bw.write(0 + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static void MakeDP() {
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
            int range = i;
            range = Math.min(range, N - i - 1);
            for (int j = 1; j <= range; j++) {
                if (dp[i - j + 1][i + j - 1] && nums[i - j] == nums[i + j]) {
                    dp[i - j][i + j] = true;
                }
            }
        }
        for (int i = 1; i < N; i++) {
            if (nums[i - 1] == nums[i]) {
                dp[i - 1][i] = true;
            }
        }
        for (int i = 1; i < N - 1; i++) {
            int range = i;
            range = Math.min(range, N - i - 2);
            for (int j = 1; j <= range; j++) {
                if (dp[i - j + 1][i + j] && nums[i - j] == nums[i + j]) {
                    dp[i - j][i + j + 1] = true;
                }
            }
        }
    }
}