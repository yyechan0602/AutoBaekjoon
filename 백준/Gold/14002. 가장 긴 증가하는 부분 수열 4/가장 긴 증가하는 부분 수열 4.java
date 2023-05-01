import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        int[][] dp = new int[2][N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[0] 은 이전 주소값
        // dp[1] 은 지금까지의 개수
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[1][i] <= (dp[1][j] + 1)) {
                    if (dp[1][i] == (dp[1][j] + 1)) {
                        if (arr[dp[0][i]] <= arr[dp[0][j]]) {
                            continue;
                        }
                    }
                    dp[1][i] = dp[1][j] + 1;
                    dp[0][i] = j;
                }
            }
        }
/*        System.out.print("\n순서:\t");
        for (int i = 1; i <= N; i++) {
            System.out.print(i + "\t");
        }
        System.out.print("\ndp[0]\t");
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[0][i] + "\t");
        }
        System.out.print("\ndp[1]\t");
        for (int i = 1; i <= N; i++) {
            System.out.print(dp[1][i] + "\t");
        }*/

        int max = 0;
        int maxIndex = 0;

        for (int i = 1; i <= N; i++) {
            if(max < dp[1][i]){
                max = dp[1][i];
                maxIndex = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        while(maxIndex != 0) {
            sb.insert(0, arr[maxIndex] + " ");
            maxIndex = dp[0][maxIndex];
        }
        System.out.println(max);
        System.out.println(sb);
    }
}