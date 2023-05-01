import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][][] box;
    static Queue<Integer[]> q;
    static int M;
    static int N;
    static int H;
    static int[] DR = new int[]{-1, 0, 1, 0, 0, 0};
    static int[] DC = new int[]{0, 1, 0, -1, 0, 0};
    static int[] DH = new int[]{0, 0, 0, 0, 1, -1};
    static final int empty = 1000002;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        q = new LinkedList<>();
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[k][i][j] = Integer.parseInt(st.nextToken());
                    if (box[k][i][j] == 1) {
                        q.add(new Integer[]{k, i, j});
                    } else if (box[k][i][j] == 0) {
                        box[k][i][j] = empty;
                    }
                }
            }
        }
        BFS();
        FindTomato();
    }

    static void FindTomato() {
        int max = 0;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    max = Math.max(max, box[k][i][j]);
                }
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
        int nextHeight;
        int curtime;
        while (!q.isEmpty()) {
            cur = q.poll();
            curtime = box[cur[0]][cur[1]][cur[2]] + 1;
            for (int i = 0; i < DC.length; i++) {
                nextRow = cur[1] + DR[i];
                nextCol = cur[2] + DC[i];
                nextHeight = cur[0] + DH[i];
                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || nextHeight < 0 || nextHeight >= H || box[nextHeight][nextRow][nextCol] != empty) {
                    continue;
                }
                q.add(new Integer[]{nextHeight, nextRow, nextCol});
                box[nextHeight][nextRow][nextCol] = Math.min(box[nextHeight][nextRow][nextCol], curtime);
            }
        }
    }
}