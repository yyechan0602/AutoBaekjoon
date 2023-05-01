

import java.io.*;
import java.util.*;

public class Main {
    static int[][] box;
    static Queue<Integer[]> q;
    static int M;
    static int N;
    static int[] DR = new int[]{-1, 0, 1, 0};
    static int[] DC = new int[]{0, 1, 0, -1};
    static final int empty = 1000002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if (box[i][j] == 1) {
                    q.add(new Integer[]{i, j});
                } else if (box[i][j] == 0) {
                    box[i][j] = empty;
                }

            }
        }
        BFS();
        FindTomato();
    }

    static void FindTomato() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, box[i][j]);
            }
        }
        if (max == empty) {
            System.out.println(-1);
        } else {
            System.out.println(max - 1);
        }
    }

    static void BFS() {
        Integer[] cur;
        int nextRow;
        int nextCol;
        int curtime;
        while (!q.isEmpty()) {
            cur = q.poll();
            curtime = box[cur[0]][cur[1]] + 1;
            for (int i = 0; i < DC.length; i++) {
                nextRow = cur[0] + DR[i];
                nextCol = cur[1] + DC[i];
                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || box[nextRow][nextCol] != empty) {
                    continue;
                }
                q.add(new Integer[]{nextRow, nextCol});
                box[nextRow][nextCol] = Math.min(box[nextRow][nextCol], curtime);
            }
        }
    }
}
