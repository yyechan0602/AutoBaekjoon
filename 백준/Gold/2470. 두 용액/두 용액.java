import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> ascend = new PriorityQueue<>();
        PriorityQueue<Integer> descend = new PriorityQueue<>(Collections.reverseOrder());
        st = new StringTokenizer(br.readLine());
        int num;

        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(st.nextToken());
            ascend.add(num);
            descend.add(num);
        }

        int max = descend.remove();
        int min = ascend.remove();
        int[] closeZero = {max, min};

        while (max != min) {
            if (max + min > 0) {
                max = descend.remove();
            } else {
                min = ascend.remove();
            }
            if (max == min) {
                break;
            }
            if (Math.abs(closeZero[0] + closeZero[1]) > Math.abs(max + min)) {
                closeZero = new int[]{max, min};
            }
        }
        System.out.println(closeZero[1] + " " + closeZero[0]);
    }
}