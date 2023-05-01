import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int count = Integer.parseInt(br.readLine());
        long[][] nums = new long[count][21];
        st = new StringTokenizer(br.readLine());
        nums[1][Integer.parseInt(st.nextToken())] = 1;

        for (int i = 2; i <= count-1; i++) {
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 21; j++) {
                if (j - num >= 0) {
                    nums[i][j - num] += nums[i - 1][j];
                }
                if (j + num <= 20) {
                    nums[i][j + num] += nums[i - 1][j];
                }
            }
        }

        System.out.println(nums[count-1][Integer.parseInt(st.nextToken())]);
    }
}