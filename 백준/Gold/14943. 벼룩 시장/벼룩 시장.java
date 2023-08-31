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

        long remain = 0;
        long result = 0;
        for (int i = 0; i < N; i++) {
            result += Math.abs(remain);
            remain += Integer.parseInt(st.nextToken());
        }

        System.out.println(result);
    }
}