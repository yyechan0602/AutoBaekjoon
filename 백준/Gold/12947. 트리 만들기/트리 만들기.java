import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] cnt = new int[N+1];
        st = new StringTokenizer(br.readLine());
        cnt[0] = 1;
        int length = 0;
        int max = 0;

        for (int i = 1; i <= N; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 0; i--) {
            if (cnt[i] == 1) {
                max = Math.max(max, N - i + length);
                length = 0;
            } else {
                length++;
            }
        }

        System.out.println(max);
    }
}