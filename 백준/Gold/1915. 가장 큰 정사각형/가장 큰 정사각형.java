import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        int length;
        int max = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            length = 0;
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') {
                    length++;
                    field[i][j] = length;
                } else {
                    length = 0;
                    field[i][j] = length;
                }
            }
        }
        //showAll();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int num = findPasture(i, j);
                max = Math.max(max, num);
                //System.out.print(num + " ");
            }
            //System.out.println();
        }

        System.out.println(max * max);
    }

    static int findPasture(int r, int c) {
        int maxLength = field[r][c];

        for (int i = 0; i < maxLength; i++) {
            if (r + i >= N) {
                maxLength = i;
                return maxLength;
            }

            if (field[r + i][c] < maxLength) {
                maxLength = field[r + i][c];
            }
        }

        return maxLength;
    }

    static void showAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}