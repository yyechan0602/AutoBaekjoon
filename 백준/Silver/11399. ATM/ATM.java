import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int people;
    public static int[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        people = Integer.parseInt(st.nextToken());
        times = new int[people];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < people; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);
        int result = 0;
        for (int i = 0; i < people; i++) {

        }
        for (int i = 0; i < people; i++) {
            for (int j = 0; j < i +1; j++) {
                result += times[j];
            }
        }

        System.out.println(result);
    }
}