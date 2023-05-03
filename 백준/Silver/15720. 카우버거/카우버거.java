import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        Integer[] burger = new Integer[B];
        Integer[] side = new Integer[C];
        Integer[] beverages = new Integer[D];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            burger[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            side[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < D; i++) {
            beverages[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(burger, Comparator.reverseOrder());
        Arrays.sort(side, Comparator.reverseOrder());
        Arrays.sort(beverages, Comparator.reverseOrder());
        int min = Math.min(Math.min(B, C), D);
        int result = 0;
        int nosaleresult = 0;

        for (int i = 0; i < min; i++) {
            result += (burger[i] + side[i] + beverages[i]) * 9 / 10;
            nosaleresult += burger[i] + side[i] + beverages[i];
        }

        for (int i = min; i < B; i++) {
            result += burger[i];
            nosaleresult += burger[i];
        }
        for (int i = min; i < C; i++) {
            result += side[i];
            nosaleresult += side[i];
        }
        for (int i = min; i < D; i++) {
            result += beverages[i];
            nosaleresult += beverages[i];
        }

        System.out.println(nosaleresult);
        System.out.println(result);
    }
}