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

        int[] nums = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] ascend = new int[N];
        int[] descend = new int[N];

        for (int i = 0; i < N; i++) {
            ascend[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    ascend[i] = Math.max(ascend[i], ascend[j] + 1);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            descend[i] = 1;
            for (int j = N - 1; j >= 0; j--) {
                if (nums[i] > nums[j])
                    descend[i] = Math.max(descend[i], descend[j] + 1);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, ascend[i] + descend[i] - 1);
        }

        System.out.println(result);
    }
}