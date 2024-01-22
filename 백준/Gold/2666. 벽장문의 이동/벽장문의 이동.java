import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int minimum;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        minimum = Integer.MAX_VALUE;

        T = Integer.parseInt(br.readLine());
        nums = new int[T];

        for (int i = 0; i < T; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Move(0, 0, first, second);

        System.out.println(minimum);
    }

    static void Move(int depth, int count, int first, int second) {
        if (depth >= T) {
            minimum = Math.min(minimum, count);
            return;
        }

        Move(depth+1, count+Math.abs(nums[depth]-first), nums[depth], second);
        Move(depth+1, count+Math.abs(nums[depth]-second), first, nums[depth]);
    }
}