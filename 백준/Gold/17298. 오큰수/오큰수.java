import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        nums = new int[N];
        dp = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        FindNGE(N);
        ShowAll(N);
    }

    static void FindNGE(int N) {
        dp[N - 1] = -1;
        for (int i = N - 2; i >= 0; i--) {
            // 주소값
            dp[i] = i + 1;
            // 주소값이 -1이 아니고, 더 크면
            while (nums[i] >= nums[dp[i]]) {
                if (dp[dp[i]] != -1) {
                    dp[i] = dp[dp[i]];
                } else{
                    dp[i] = -1;
                    break;
                }
            }
        }
    }

    static void ShowAll(int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (dp[i] != -1) {
                sb.append(nums[dp[i]] + " ");
            } else {
                sb.append(-1 +" ");
            }
        }
        System.out.println(sb);
    }
}