import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] pool = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pool[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(pool, new Comparator<int[]>() {
            public int compare(int[] p1, int[] p2) {
                if (p1[0] < p2[0]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        int lastPos = 0;
        int count = 0;

        for (int i = 0; i < N; i++) {
            if (lastPos < pool[i][0]) {
                lastPos = pool[i][0] + L - 1;
                count++;
            }

            while (lastPos < pool[i][1]-1) {
                lastPos += L;
                count++;
            }
        }

        System.out.println(count);
    }
}