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
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        field = new int[M][N];
        int length;
        int max = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            length = 0;
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("0")) {
                    length++;
                    field[i][j] = length;
                } else {
                    length = 0;
                    field[i][j] = length;
                }
            }
        }
        //showAll();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int num = findPasture(i, j);
                max = Math.max(max, num);
                //System.out.print(num + " ");
            }
            //System.out.println();
        }

        System.out.println(max);
    }

    static int findPasture(int r, int c) {
        int maxLength = field[r][c];

        for (int i = 0; i < maxLength; i++) {
            if (r + i >= M) {
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
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}