import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[N][N];
        int[][][] prefix = new int[3][N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            Arrays.fill(prefix[0][i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N - B + 1; i++) {
            for (int j = 0; j < N - B + 1; j++) {
                for (int k = 0; k < B; k++) {
                    for (int l = 0; l < B; l++) {
                        prefix[0][i][j] = Math.min(prefix[0][i][j], matrix[i + k][j + l]);
                        prefix[1][i][j] = Math.max(prefix[1][i][j], matrix[i + k][j + l]);
                    }
                }

                prefix[2][i][j] = prefix[1][i][j] - prefix[0][i][j];
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            sb.append(prefix[2][x][y]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}