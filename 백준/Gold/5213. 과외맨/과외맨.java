import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static int tileNum;
    private static int[][][] dp;

    private static final int[] DR = {0, 1, 0, -1};
    private static final int[] DC = {1, 0, -1, 0};
    private static final int start = -2333;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][2 * N];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < N; j++) {
                    st = new StringTokenizer(br.readLine());
                    for (int k = 0; k < 2; k++) {
                        board[i][2 * j + k] = Integer.parseInt(st.nextToken());
                    }
                }
            } else {
                for (int j = 1; j < N; j++) {
                    st = new StringTokenizer(br.readLine());
                    for (int k = 0; k < 2; k++) {
                        board[i][2 * j + k - 1] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }

        tileNum = 1;
        BFS();

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int r = 1;
        int c = 1;

        for (int i = N - 1; i >= 0; i--) {
            if (flag) break;
            for (int j = 2 * N - 1; j >= 0; j--) {
                if (visited[i][j] != Integer.MAX_VALUE) {
                    r = i;
                    c = j;
                    flag = true;
                    break;
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int lastNum = 0;

        while (true) {
            if (r == start && c == start) break;

            if (lastNum != (2 * N - 1) * (r / 2) + N * (r % 2) + ((c + r % 2) / 2) + 1 - r % 2) {
                stack.add((2 * N - 1) * (r / 2) + N * (r % 2) + ((c + r % 2) / 2) + 1 - r % 2);
                lastNum = (2 * N - 1) * (r / 2) + N * (r % 2) + ((c + r % 2) / 2) + 1 - r % 2;
            }
            int temptR = r;
            r = dp[temptR][c][0];
            c = dp[temptR][c][1];
        }

        System.out.println(stack.size());

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    static void BFS() {
        dp = new int[N][2 * N][2];
        visited = new int[N][2 * N];
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{0, 0});
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[0][0] = 1;
        dp[0][0][0] = start;
        dp[0][0][1] = start;

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            tileNum = Math.max((2 * N - 1) * (cur[0] / 2) + N * (cur[0] % 2) + ((cur[1] + cur[0] % 2) / 2) + 1 - cur[0] % 2, tileNum);

            for (int i = 0; i < DR.length; i++) {
                int nextR = cur[0] + DR[i];
                int nextC = cur[1] + DC[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= 2 * N)
                    continue;

                // 홀수 줄
                if (nextR % 2 == 0) {
                    // 서로 같은 블럭이면
                    if (nextR == cur[0] && cur[1] / 2 == nextC / 2) {
                        if (visited[nextR][nextC] <= visited[cur[0]][cur[1]]) continue;
                        visited[nextR][nextC] = visited[cur[0]][cur[1]];
                        q.add(new Integer[]{nextR, nextC});
                        dp[nextR][nextC][0] = cur[0];
                        dp[nextR][nextC][1] = cur[1];
                    }
                    // 아닐때
                    else if (board[nextR][nextC] == board[cur[0]][cur[1]]) {
                        if (visited[nextR][nextC] <= visited[cur[0]][cur[1]] + 1) continue;
                        visited[nextR][nextC] = visited[cur[0]][cur[1]] + 1;
                        q.add(new Integer[]{nextR, nextC});
                        dp[nextR][nextC][0] = cur[0];
                        dp[nextR][nextC][1] = cur[1];
                    }
                } else {
                    // 서로 같은 블럭이면
                    if (nextR == cur[0] && cur[1] / 2 != nextC / 2) {
                        if (visited[nextR][nextC] <= visited[cur[0]][cur[1]]) continue;
                        visited[nextR][nextC] = visited[cur[0]][cur[1]];
                        q.add(new Integer[]{nextR, nextC});
                        dp[nextR][nextC][0] = cur[0];
                        dp[nextR][nextC][1] = cur[1];
                    }
                    // 아닐때
                    else if (board[nextR][nextC] == board[cur[0]][cur[1]]) {
                        if (visited[nextR][nextC] <= visited[cur[0]][cur[1]] + 1) continue;
                        visited[nextR][nextC] = visited[cur[0]][cur[1]] + 1;
                        q.add(new Integer[]{nextR, nextC});
                        dp[nextR][nextC][0] = cur[0];
                        dp[nextR][nextC][1] = cur[1];
                    }
                }
            }
/*            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 2 * N; j++) {
                    if (visited[i][j] == Integer.MAX_VALUE) System.out.print(".\t");
                    else {
                        System.out.print(visited[i][j] + "\t");
                    }
                }
                System.out.println();
            }
            System.out.println("=================");*/
        }
    }
}