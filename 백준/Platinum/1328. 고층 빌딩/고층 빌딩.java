import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long[][][] buildings = new long[N + 1][N + 1][N + 1];
        buildings[1][1][1] = 1;

        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for (int k = 1; k < N + 1; k++) {
                    buildings[i][j][k] = (buildings[i - 1][j][k] * (i - 2) + buildings[i - 1][j - 1][k] + buildings[i - 1][j][k - 1]) % 1000000007;
                }
            }
        }

        System.out.println(buildings[N][L][R]);
    }
}