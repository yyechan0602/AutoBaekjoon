import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] grow = new int[2 * M - 1];
        Arrays.fill(grow, 1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int index = 0;
            for (int j = 0; j < 3; j++) {
                int tempt = Integer.parseInt(st.nextToken());
                while (tempt != 0) {
                    grow[index++] += j;
                    tempt--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            sb.append(grow[M - i - 1]).append(" ");
            for (int j = 1; j < M; j++) {
                sb.append(grow[M + j-1]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}