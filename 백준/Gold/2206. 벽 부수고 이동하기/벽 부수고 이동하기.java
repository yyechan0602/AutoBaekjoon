import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class tile {
        int map;
        int notBrokenTime;
        int brokenTime;

        public tile(int map) {
            this.map = map;
            this.notBrokenTime = max;
            this.brokenTime = max;
        }
    }

    static final int max = 1000001;
    static tile[][] maze;
    static int N;
    static int M;
    static int[] DR = {-1, 0, 1, 0};
    static int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new tile[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') {
                    maze[i][j] = new tile(max);
                } else {
                    maze[i][j] = new tile(0);
                }
            }
        }
        //ShowAll();
        BFS();
    }

    static void BFS() {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{0, 0, 0});
        maze[0][0].brokenTime = 1;
        maze[0][0].notBrokenTime = 1;

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            for (int i = 0; i < DR.length; i++) {
                int nextR = cur[0] + DR[i];
                int nextC = cur[1] + DC[i];
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (maze[nextR][nextC].map == 0) {
                    if (cur[2] == 0) {
                        if (maze[nextR][nextC].notBrokenTime > maze[cur[0]][cur[1]].notBrokenTime + 1) {
                            maze[nextR][nextC].notBrokenTime = maze[cur[0]][cur[1]].notBrokenTime + 1;
                            q.add(new Integer[]{nextR, nextC, 0});
                        }
                    } else {
                        if (maze[nextR][nextC].brokenTime > maze[cur[0]][cur[1]].brokenTime + 1) {
                            maze[nextR][nextC].brokenTime = maze[cur[0]][cur[1]].brokenTime + 1;
                            q.add(new Integer[]{nextR, nextC, 1});
                        }
                    }
                } else {
                    if (cur[2] == 0 && maze[nextR][nextC].brokenTime > maze[cur[0]][cur[1]].notBrokenTime + 1) {
                        maze[nextR][nextC].brokenTime = maze[cur[0]][cur[1]].notBrokenTime + 1;
                        q.add(new Integer[]{nextR, nextC, 1});
                    }
                }
            }
            //ShowAll();
        }
        if(Math.min(maze[N - 1][M - 1].brokenTime, maze[N - 1][M - 1].notBrokenTime) == max){
            System.out.println(-1);
        }else{
            System.out.println(Math.min(maze[N - 1][M - 1].brokenTime, maze[N - 1][M - 1].notBrokenTime));
        }
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j].map == max) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
                if (maze[i][j].brokenTime == max) {
                    System.out.print(".");
                } else {
                    System.out.print(maze[i][j].brokenTime);
                }
            }
            System.out.println();
        }
        System.out.println("========================");
    }
}