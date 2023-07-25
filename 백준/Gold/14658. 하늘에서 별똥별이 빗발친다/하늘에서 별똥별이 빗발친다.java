import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] shootingStars = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            shootingStars[i][0] = Integer.parseInt(st.nextToken());
            shootingStars[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j <= L; j++) {
                int curR = shootingStars[i][0];
                int curC = shootingStars[i][1] + j;
                int curMax = 0;

                //System.out.print(curR + " " + curC + " ");

                if (curC > M) {
                    break;
                }

                for (int k = 0; k < K; k++) {
                    if (curR <= shootingStars[k][0] && shootingStars[k][0] <= curR + L && curC-L <= shootingStars[k][1] && shootingStars[k][1] <= curC)
                        curMax++;
                }

                //System.out.println(curMax);

                max = Math.max(curMax, max);
            }
        }

        System.out.println(K - max);
    }
}