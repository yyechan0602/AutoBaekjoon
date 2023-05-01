import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class tile {
        int value;
        int dp;

        public tile(int value) {
            this.value = value;
            this.dp = -2100000000;
        }

        void MakeDP(tile dp) {
            this.dp = Math.max(this.dp, dp.dp + this.value * dp.value);
        }
    }

    static int N;
    static int M;
    static int D;
    static tile[][] field;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        field = new tile[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = new tile(Integer.parseInt(st.nextToken()));
            }
        }
        FillField();
        int max = -2100000000;
        for (int i = 0; i < M; i++) {
            max = Math.max(field[N - 1][i].dp, max);
        }
        //ShowAll();
        System.out.println(max);
    }

    static void FillField() {
        for (int i = 0; i < M; i++) {
            field[0][i].dp = 0;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 1; k <= D; k++) {
                    for (int l = -D + k; l <= D - k; l++) {
                        if (i + k >= N || j + l >= M || j + l < 0) {
                            continue;
                        }
                        field[i + k][j + l].MakeDP(field[i][j]);
                    }
                }
            }
        }
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(field[i][j].dp + " ");
            }
            System.out.println();
        }
    }
}