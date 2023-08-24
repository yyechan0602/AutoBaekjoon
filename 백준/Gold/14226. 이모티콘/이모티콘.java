import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        int[] dp = new int[3000];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        dp[1] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int tempt = 2;
            while (tempt * cur < 3000) {
                if (dp[tempt * cur] > dp[cur] + tempt) {
                    dp[tempt * cur] = dp[cur] + tempt;
                    q.add(tempt * cur);
                }
                tempt++;
            }

            if (cur - 1 < 1) continue;
            if (dp[cur - 1] > dp[cur] + 1) {
                dp[cur - 1] = dp[cur] + 1;
                q.add(cur - 1);
            }
        }

        System.out.println(dp[S]);
    }
}