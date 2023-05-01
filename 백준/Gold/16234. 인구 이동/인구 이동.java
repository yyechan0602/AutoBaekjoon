import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int R;
    static int[][] ground;
    static Queue<Integer[]> que;
    static Queue<Integer[]> queUnion;
    static boolean[][] visited;
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        ground = new int[N][N];
        que = new LinkedList<>();
        queUnion = new LinkedList<>();
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        flag = true;
        int time = -1;
        //ShowAll();
        while (flag) {
            time++;
            BFS();
            //ShowAll();
        }
        System.out.println(time);
    }

    static void BFS() {
        flag = false;
        int num = 0;
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    int plus = 0;
                    visited[i][j] = true;
                    plus += ground[i][j];
                    que.add(new Integer[]{i, j});
                    queUnion.add(new Integer[]{i, j});

                    while (!que.isEmpty()) {
                        Integer[] cur = que.poll();
                        for (int k = 0; k < DR.length; k++) {
                            int nextR = cur[0] + DR[k];
                            int nextC = cur[1] + DC[k];

                            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || visited[nextR][nextC]) {
                                continue;
                            }
                            if (Math.abs(ground[nextR][nextC] - ground[cur[0]][cur[1]]) < L || Math.abs(ground[nextR][nextC] - ground[cur[0]][cur[1]]) > R) {
                                continue;
                            }
                            plus += ground[nextR][nextC];
                            visited[nextR][nextC] = true;
                            que.add(new Integer[]{nextR, nextC});
                            queUnion.add(new Integer[]{nextR, nextC});
                        }
                    }
                    if (queUnion.size() > 1) {
                        flag = true;
                    }
                    int value = plus / queUnion.size();
                    while (!queUnion.isEmpty()) {
                        Integer[] cur2 = queUnion.poll();
                        ground[cur2[0]][cur2[1]] = value;

                    }
                }
            }
        }
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(ground[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==============================");
    }
}