import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int coachCount = Integer.parseInt(br.readLine());
        int[] coach = new int[coachCount + 1];
        int[][] dp = new int[3][coachCount + 1];
        coach[0] = 0;
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= coachCount; i++) {
            coach[i] = coach[i - 1] + Integer.parseInt(st.nextToken());
        }

        int canTrans = Integer.parseInt(br.readLine());

        for (int i = 1; i <= coachCount - 2 * canTrans; i++) {
            int lastTrans = i - canTrans;
            if (lastTrans < 0) lastTrans = 0;
            dp[0][i] = Math.max(dp[0][i - 1], coach[i] - coach[lastTrans]);
        }

        for (int i = 1; i <= coachCount - canTrans; i++) {
            int lastTrans = i - canTrans;
            if (lastTrans < 0) lastTrans = 0;
            dp[1][i] = Math.max(dp[1][i - 1], coach[i] - coach[lastTrans] + dp[0][lastTrans]);
        }

        for (int i = 1; i <= coachCount; i++) {
            int lastTrans = i - canTrans;
            if (lastTrans < 0) lastTrans = 0;
            dp[2][i] = Math.max(dp[2][i - 1], coach[i] - coach[lastTrans] + dp[1][lastTrans]);
        }

        int max = 0;
        //ShowAll(coachCount, dp);

        for (int i = 0; i <= coachCount; i++) {
            max = Math.max(max, dp[2][i]);
        }

        System.out.println(max);
    }

    static void ShowAll(int coachCount, int[][] dp) {
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i <= coachCount; i++) {
                System.out.print(dp[j][i] + " ");
            }
            System.out.println();
        }
    }
}