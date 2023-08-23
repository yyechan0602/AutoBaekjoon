import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static private class tile {
        int r, c, time, keys;

        public tile(int r, int c, int time, int keys) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.keys = keys;
        }
    }

    static private final char EMPTY = '.';
    static private final char WALL = '#';
    static private final char start = '0';
    static private final char end = '1';

    static private char[][] maze;

    static private int startR;
    static private int startC;

    static private int N;
    static private int M;

    static private final int[] DR = {0, 1, 0, -1};
    static private final int[] DC = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j);
                if (maze[i][j] == start) {
                    startR = i;
                    startC = j;
                }
            }
        }

        System.out.println(BFS());
    }

    static private int BFS() {
        // 6자리 bit를 사용하여 visited check
        boolean[][][] visited = new boolean[N][M][64];
        // curR, curC, time, key
        Queue<tile> q = new LinkedList<>();
        q.add(new tile(startR, startC, 0, 0));
        visited[startR][startC][0] = true;

        while (!q.isEmpty()) {
            tile cur = q.poll();

            if (maze[cur.r][cur.c] == end) return cur.time;

            for (int i = 0; i < DR.length; i++) {
                int nextR = cur.r + DR[i];
                int nextC = cur.c + DC[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;
                if (visited[nextR][nextC][cur.keys] || maze[nextR][nextC] == WALL) continue;

                if (maze[nextR][nextC] >= 'a' && maze[nextR][nextC] <= 'f') {
                    int nextkeys = 1 << (maze[nextR][nextC] - 'a');
                    nextkeys = nextkeys | cur.keys;
                    visited[nextR][nextC][nextkeys] = true;
                    q.add(new tile(nextR, nextC, cur.time + 1, nextkeys));
                } else if (maze[nextR][nextC] >= 'A' && maze[nextR][nextC] <= 'F') {
                    if ((cur.keys & (1 << (maze[nextR][nextC] - 'A'))) == 0) continue;
                    visited[nextR][nextC][cur.keys] = true;
                    q.add(new tile(nextR, nextC, cur.time + 1, cur.keys));
                } else {
                    visited[nextR][nextC][cur.keys] = true;
                    q.add(new tile(nextR, nextC, cur.time + 1, cur.keys));
                }
            }
        }

        return -1;
    }
}