import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] board;
    static boolean[][] visited;
    static int[][] time;
    static boolean flag;
    static int max;
    static final int HOLE = -10;

    static int[] DR = {-1, 0, 1, 0};
    static int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        time = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'H') {
                    board[i][j] = HOLE;
                } else {
                    board[i][j] = Character.getNumericValue(str.charAt(j));
                }
            }
        }
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
            Arrays.fill(time[i], 0);
        }
        flag = false;
        max = 0;
        DFS(0, 0);
        if (!flag) {
            System.out.println(max);
        }
    }

    static void DFS(int r, int c) {
        if (flag) {
            return;
        }
        if (visited[r][c]) {
            System.out.println(-1);
            flag = true;
            return;
        }
        visited[r][c] = true;
        for (int i = 0; i < DR.length; i++) {
            int nextR = DR[i] * board[r][c] + r;
            int nextC = DC[i] * board[r][c] + c;
            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                max = Math.max(time[r][c] + 1, max);
                continue;
            }
            if (board[nextR][nextC] == HOLE) {
                max = Math.max(time[r][c] + 1, max);
                continue;
            }
            if (time[r][c] + 1 > time[nextR][nextC]) {
                time[nextR][nextC] = time[r][c] + 1;
                DFS(nextR, nextC);
            }
        }
        visited[r][c] = false;
    }
}