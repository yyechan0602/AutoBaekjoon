import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int[] under = {0, 1, 2, 3, 4, 5};
    static final int[] up = {5, 3, 4, 1, 2, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] dice = new int[N][6];
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 0; j < under.length; j++) {
            int diceMax = 0;
            int num = dice[0][up[j]];

            for (int i = 0; i < N; i++) {
                int sideMax = 0;
                int num2 = -1;

                for (int k = 0; k < under.length; k++) {
                    if (dice[i][k] == num) {
                        num2 = dice[i][up[k]];
                        break;
                    }
                }

                for (int k = 0; k < under.length; k++) {
                    if (dice[i][k] == num || dice[i][k] == num2) {
                        continue;
                    }
                    sideMax = Math.max(sideMax, dice[i][k]);
                }

                num = num2;
                diceMax += sideMax;
                //System.out.print(sideMax + " ");
            }

            max = Math.max(diceMax, max);
            //System.out.println(diceMax);
        }

        System.out.println(max);
    }
}