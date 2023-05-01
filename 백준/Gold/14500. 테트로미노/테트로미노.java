import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    static int[][] paper;
    static boolean[][] isVisited;

    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        int max = 0;
        isVisited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++){
                max = Math.max(max, FindMax(i, j, 3));
            }
        }
        System.out.println(max);
    }

    static int FindMax(int r, int c, int count) { // DFS로 접근하였다.
        int max = 0;
        isVisited[r][c] = true; // 방문배열

        if (count > 0) {
            for (int i = 0; i < DR.length; i++) {
                int nextR = r + DR[i];
                int nextC = c + DC[i];

                // 다음 위치가 도달 가능한지/ 방문을 햇는지 체크
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || isVisited[nextR][nextC]) {
                    continue;
                }

                // 방문할 수 있는 모든 곳에서의 최대값을 골라낸다
                max = Math.max(max, FindMax(nextR, nextC, count - 1));
            }

            if (count == 2) {
                for (int i = 0; i < DR.length; i++) {
                    int nextR1 = r + DR[i];
                    int nextC1 = c + DC[i];

                    if (nextR1 < 0 || nextR1 >= N || nextC1 < 0 || nextC1 >= M || isVisited[nextR1][nextC1]) {
                        continue;
                    }

                    isVisited[nextR1][nextC1] = true;

                    for (int j = 0; j < DR.length; j++) {
                        int nextR2 = r + DR[j];
                        int nextC2 = c + DC[j];

                        if (nextR2 < 0 || nextR2 >= N || nextC2 < 0 || nextC2 >= M || isVisited[nextR2][nextC2]) {
                            continue;
                        }

                        max = Math.max(max, paper[nextR1][nextC1] + paper[nextR2][nextC2]);
                    }

                    isVisited[nextR1][nextC1] = false;
                }
            }
        }

        isVisited[r][c] = false;
        return max + paper[r][c];
    }
}