import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] field;

    private static boolean[][] visited;
    private static int R;
    private static int C;
    private static int K;

    private static final int[] DR = {0, 1, 0, -1};
    private static final int[] DC = {1, 0, -1, 0};
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new boolean[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                field[i][j] = str.charAt(j) == 'T';
            }
        }

        result = 0;

        visited[R - 1][0] = true;
        DFS(R - 1, 0, 1);

        System.out.println(result);
    }

    private static void DFS(int r, int c, int depth) {
        if (depth == K) {
            if (r != 0 || c != C - 1) return;

            result++;
        }

        for (int i = 0; i < DR.length; i++) {
            int nextR = r + DR[i];
            int nextC = c + DC[i];

            if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || visited[nextR][nextC] || field[nextR][nextC]) continue;

            visited[nextR][nextC] = true;
            DFS(nextR, nextC, depth + 1);
            visited[nextR][nextC] = false;
        }
    }
}