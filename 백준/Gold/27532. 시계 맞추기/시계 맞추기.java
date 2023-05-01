

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int M;
    static int[] times;
    static boolean[] clock;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());
        String str;
        times = new int[M];
        clock = new boolean[720];

        for (int i = 0; i < M; i++) {
            str = br.readLine();
            times[i] = Integer.parseInt(str.substring(0, 2)) * 60 + Integer.parseInt(str.substring(3, 5));
        }
        int min =  730;
        for (int i = 1; i <= 720; i++) {
            min = Math.min(FindTime(i), min);
            Arrays.fill(clock, false);
        }
        System.out.println(min);
    }

    static int FindTime(int time) {
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (!clock[((times[i] - i * time) % 720 + 720) % 720]) {
                clock[((times[i] - i * time) % 720 + 720) % 720] = true;
                count++;
            }
        }
        if (count >= 720) {
            return 720;
        }
        return count;
    }
}
