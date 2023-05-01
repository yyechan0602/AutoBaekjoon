import java.io.*;
import java.util.*;

public class Main {
    static class ice {
        int height;
        boolean melted;

        public ice(int height) {
            this.height = height;
            if (height == 0) {
                melted = true;
            } else {
                melted = false;
            }
        }

        public void TimePass() {
            if (this.height == 0) {
                this.melted = true;
            }
        }
    }

    static int time;
    static int N;
    static int M;
    static ice[][] sea;
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = 0;
        sea = new ice[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                sea[i][j] = new ice(Integer.parseInt(st.nextToken()));
            }
        }

        //ShowAll();
        while (CheckDivided()) {
            Melt();
            //ShowAll();
        }
        System.out.println(time);
    }

    static void Melt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sea[i][j].TimePass();
            }
        }
        time += 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < DR.length; k++) {
                    int nextR = i + DR[k];
                    int nextC = j + DC[k];
                    if (sea[i][j].height == 0 || nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || !sea[nextR][nextC].melted) {
                        continue;
                    }
                    sea[i][j].height -= 1;
                }
            }
        }
    }

    static boolean CheckDivided() {
        int flag = 0;
        isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(isVisited[i], false);
        }
        Queue<Integer[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!isVisited[i][j]) {
                    isVisited[i][j] = true;
                    if (sea[i][j].height > 0) {
                        q.add(new Integer[]{i, j});
                        flag += 1;
                    }

                    while (!q.isEmpty()) {
                        Integer[] cur = q.poll();
                        for (int k = 0; k < DR.length; k++) {
                            int nextR = cur[0] + DR[k];
                            int nextC = cur[1] + DC[k];

                            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                                continue;
                            }
                            if (!isVisited[nextR][nextC] && sea[nextR][nextC].height > 0) {
                                q.add(new Integer[]{nextR, nextC});
                                isVisited[nextR][nextC] = true;
                            }
                        }
                    }
                    //ShowCheck(isVisited);
                }
            }
        }

        if (flag != 1) {
            if (flag == 0) {
                time = 0;
            }
            return false;
        } else {
            return true;
        }
    }

    static void ShowCheck() {
        for (int i2 = 0; i2 < N; i2++) {
            for (int j2 = 0; j2 < M; j2++) {
                if (isVisited[i2][j2]) {
                    System.out.print(". ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (sea[i][j].melted) {
                    System.out.print("^ ");
                } else {
                    System.out.print(sea[i][j].height + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==============================");
    }
}