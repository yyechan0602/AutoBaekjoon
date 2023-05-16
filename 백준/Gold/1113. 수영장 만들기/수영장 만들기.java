import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] pool;
    static int N;
    static int M;
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};

    // for문을 두번 돌리면서 안에서부터 물을 채우려니까 메모리 초과로 인한 문제로 인해 너무 복잡해져서 발생의 전환을 했다.
    // 바로 전체 넓이에서 바깥부터 물을 못채우는 공간을 구한후, 현재 못채우는 높이보다 높은 땅을 빼주면 답이 나온다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pool = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                pool[i][j] = Character.getNumericValue(str.charAt(j - 1));
            }
        }

        int result = 0;

        for (int i = 2; i < 10; i++) {
            result += BFS(i);
        }

        System.out.println(result);
    }

    // 0, 0 부터 물을 못채우는 곳을 찾는 BFS
    static int BFS(int height) {
        boolean[][] visited = new boolean[N + 2][M + 2];
        Queue<Integer[]> q = new LinkedList<Integer[]>();
        q.add(new Integer[]{0, 0});
        visited[0][0] = true;
        int nonFill = 1;

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();

            for (int k = 0; k < DR.length; k++) {
                int nextR = cur[0] + DR[k];
                int nextC = cur[1] + DC[k];

                if (nextR < 0 || nextR >= N + 2 || nextC < 0 || nextC >= M + 2 || visited[nextR][nextC]) {
                    continue;
                }

                if (pool[nextR][nextC] < height) {
                    q.add(new Integer[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                    nonFill++;
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                if (pool[i][j] >= height) {
                    nonFill++;
                }
            }
        }

        return (N + 2) * (M + 2) - nonFill;
    }
}