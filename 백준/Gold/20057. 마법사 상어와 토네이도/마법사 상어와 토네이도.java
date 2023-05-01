

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] field;
    static int OutSand = 0;
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {-1, 0, 1, 0};
    static final int[] SLR = {-2, -1, -1, -1, 0, 1, 1, 1, 2, 0};
    static final int[] SLC = {0, -1, 0, 1, -2, -1, 0, 1, 0, -1};
    static final int[] SLW = {2, 10, 7, 1, 5, 10, 7, 1, 2};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        field = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //ShowAll(new int[]{N / 2, N / 2});
        Tornado();
        System.out.println(OutSand);
    }

    static void Tornado() {
        int[] pos = new int[]{N / 2, N / 2};
        int dir = 0;
        OutSand = 0;
        int area = 1;
        boolean flag = true;
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < area; j++) {
                    pos = new int[]{pos[0] + DR[dir], pos[1] + DC[dir]};
                    if (pos[0] < 0 || pos[0] >= N || pos[1] < 0 || pos[1] >= N) {
                        return;
                    }
                    MoveSand(pos, dir);
                    //ShowAll(pos);
                }
                dir += 1;
                if (dir > 3) {
                    dir = 0;
                }
            }
            area += 1;
        }
    }

    static void MoveSand(int[] pos, int dir) {
        int adjx = 1;
        if (dir == 1 || dir == 2) {
            adjx = -1;
        }
        int sand = field[pos[0]][pos[1]];
        int remainSand = sand;
        field[pos[0]][pos[1]] = 0;
        for (int i = 0; i < SLW.length; i++) {
            if (dir % 2 == 0) {
                if (pos[0] + SLR[i] < 0 || pos[0] + SLR[i] >= N || pos[1] + SLC[i] * adjx < 0 || pos[1] + SLC[i] * adjx >= N) {
                    OutSand += sand * SLW[i] / 100;
                    remainSand -= sand * SLW[i] / 100;
                    continue;
                }
                field[pos[0] + SLR[i]][pos[1] + SLC[i] * adjx] += sand * SLW[i] / 100;
                remainSand -= sand * SLW[i] / 100;
            } else {
                if (pos[0] + SLC[i] * adjx < 0 || pos[0] + SLC[i] * adjx >= N || pos[1] + SLR[i] < 0 || pos[1] + SLR[i] >= N) {
                    OutSand += sand * SLW[i] / 100;
                    remainSand -= sand * SLW[i] / 100;
                    continue;
                }
                field[pos[0] + SLC[i] * adjx][pos[1] + SLR[i]] += sand * SLW[i] / 100;
                remainSand -= sand * SLW[i] / 100;
            }
        }
        if (dir % 2 == 0) {
            if (pos[0] + SLR[9] < 0 || pos[0] + SLR[9] >= N || pos[1] + SLC[9] * adjx < 0 || pos[1] + SLC[9] * adjx >= N) {
                OutSand += remainSand;
            } else {
                field[pos[0] + SLR[9]][pos[1] + SLC[9] * adjx] += remainSand;
            }
        } else {
            if (pos[0] + SLC[9] < 0 * adjx || pos[0] + SLC[9] * adjx >= N || pos[1] + SLR[9] < 0 || pos[1] + SLR[9] >= N) {
                OutSand += remainSand;
            } else {
                field[pos[0] + SLC[9] * adjx][pos[1] + SLR[9]] += remainSand;
            }
        }
    }

    static void ShowAll(int[] pos) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pos[0] == i && pos[1] == j) {
                    System.out.print(field[i][j] + ")");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("=====================================");
    }
}