import java.io.*;
import java.util.*;

public class Main {
    static class pos {
        int r;
        int c;
        boolean isVertical;

        public pos(int r, int c, boolean isVertical) {
            this.r = r;
            this.c = c;
            this.isVertical = isVertical;
        }
    }

    static class tile {
        int Vertical;
        int Horizontal;

        public tile(int state) {
            this.Vertical = state;
            this.Horizontal = state;
        }
    }

    static Queue<pos> q;
    static tile[][] field;
    static final int startPoint = 0;
    static final int notvisited = 1000002;
    static final int tree = -3;

    static final int[] VLDR = {-1, 0, 1};
    static final int[] VLDC = {-1, -1, -1};
    static final int[] VUDR = {-2};
    static final int[] VUDC = {0};

    static final int[] HLDR = {0};
    static final int[] HLDC = {-2};
    static final int[] HUDR = {-1, -1, -1};
    static final int[] HUDC = {-1, 0, 1};

    static final int[] ALLDR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] ALLDC = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;

    static int flag;
    static final int CantGoGoal = 1000003;
    static boolean goalPoint;
    static int[] goalPointcoor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        field = new tile[N][N];
        q = new LinkedList<>();
        boolean isVertical;
        int[][] start = new int[3][2];
        int[][] end = new int[3][2];
        int flag1 = 0;
        int flag2 = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                if (str.charAt(j) == '0') {
                    field[i][j] = new tile(notvisited);
                } else if (str.charAt(j) == '1') {
                    field[i][j] = new tile(tree);
                } else if (str.charAt(j) == 'B') {
                    start[flag1] = new int[]{i, j};
                    field[i][j] = new tile(notvisited);
                    flag1 += 1;
                } else {
                    end[flag2] = new int[]{i, j};
                    field[i][j] = new tile(notvisited);
                    flag2 += 1;
                }

            }
        }
        field[start[1][0]][start[1][1]] = new tile(startPoint);
        if (start[0][0] != start[2][0]) {
            q.add(new pos(start[1][0], start[1][1], true));
        } else {
            q.add(new pos(start[1][0], start[1][1], false));
        }
        goalPointcoor = new int[]{end[1][0], end[1][1]};
        if (end[0][0] != end[2][0]) {
            goalPoint = true;
        } else {
            goalPoint = false;
        }
        BFS();
    }

    static void BFS() {
        //ShowAll();
        flag = CantGoGoal;
        while (!q.isEmpty()) {
            pos cur = q.poll();
            if (cur.isVertical) {
                for (int i = 0; i < 5; i++) {
                    VerticalLog(cur, i);
                }
            } else {
                for (int i = 0; i < 5; i++) {
                    HorizonLog(cur, i);
                }
            }
            //ShowAll();
        }
        if (flag == CantGoGoal) {
            System.out.println(0);
        } else {
            System.out.println(flag);
        }
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == goalPointcoor[0] && j == goalPointcoor[1]) {
                    System.out.print(">");
                } else {
                    System.out.print("|");
                }

                if (field[i][j].Horizontal == notvisited) {
                    System.out.printf("%2s.", "");
                } else {
                    System.out.printf("%3d", field[i][j].Horizontal);
                }

                if (field[i][j].Vertical == notvisited) {
                    System.out.printf("%2s.", "");
                } else {
                    System.out.printf("%3d", field[i][j].Vertical);
                }
            }
            System.out.println();
        }
        System.out.print("======================");
        System.out.println(flag);
    }

    static boolean VerticalLog(pos cur, int index) {
        switch (index) {
            case 0:
                // Vertical 좌로 이동
                for (int i = 0; i < VLDR.length; i++) {
                    int nextR = cur.r + VLDR[i];
                    int nextC = cur.c + VLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Vertical == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c - 1 == goalPointcoor[1] && goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Vertical + 1);
                }
                if (field[cur.r][cur.c - 1].Vertical > field[cur.r][cur.c].Vertical + 1) {
                    field[cur.r][cur.c - 1].Vertical = field[cur.r][cur.c].Vertical + 1;
                    q.add(new pos(cur.r, cur.c - 1, true));
                }
                break;
            case 1:
                // Vertical 우로 이동
                for (int i = 0; i < VLDR.length; i++) {
                    int nextR = cur.r + VLDR[i];
                    int nextC = cur.c - VLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Vertical == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c + 1 == goalPointcoor[1] && goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Vertical + 1);
                }
                if (field[cur.r][cur.c + 1].Vertical > field[cur.r][cur.c].Vertical + 1) {
                    field[cur.r][cur.c + 1].Vertical = field[cur.r][cur.c].Vertical + 1;
                    q.add(new pos(cur.r, cur.c + 1, true));
                }
                break;
            case 2:
                // Vertical 아래로 이동
                for (int i = 0; i < VUDR.length; i++) {
                    int nextR = cur.r - VUDR[i];
                    int nextC = cur.c + VUDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Vertical == tree) {
                        return false;
                    }
                }
                if (cur.r + 1 == goalPointcoor[0] && cur.c == goalPointcoor[1] && goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Vertical + 1);
                }
                if (field[cur.r + 1][cur.c].Vertical > field[cur.r][cur.c].Vertical + 1) {
                    field[cur.r + 1][cur.c].Vertical = field[cur.r][cur.c].Vertical + 1;
                    q.add(new pos(cur.r + 1, cur.c, true));
                }
                break;
            case 3:
                // 위로 이동
                for (int i = 0; i < VUDR.length; i++) {
                    int nextR = cur.r + VUDR[i];
                    int nextC = cur.c + VUDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Vertical == tree) {
                        return false;
                    }
                }
                if (cur.r - 1 == goalPointcoor[0] && cur.c == goalPointcoor[1] && goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Vertical + 1);
                }
                if (field[cur.r - 1][cur.c].Vertical > field[cur.r][cur.c].Vertical + 1) {
                    field[cur.r - 1][cur.c].Vertical = field[cur.r][cur.c].Vertical + 1;
                    q.add(new pos(cur.r - 1, cur.c, true));
                }
                break;
            case 4:
                // 회전
                for (int i = 0; i < ALLDC.length; i++) {
                    int nextR = cur.r + ALLDR[i];
                    int nextC = cur.c + ALLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Vertical == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c == goalPointcoor[1] && !goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Vertical + 1);
                }
                if (field[cur.r][cur.c].Horizontal > field[cur.r][cur.c].Vertical + 1) {
                    field[cur.r][cur.c].Horizontal = field[cur.r][cur.c].Vertical + 1;
                    q.add(new pos(cur.r, cur.c, false));
                }
        }
        return true;
    }

    static boolean HorizonLog(pos cur, int index) {
        switch (index) {
            case 0:
                // Vertical 좌로 이동
                for (int i = 0; i < HLDR.length; i++) {
                    int nextR = cur.r + HLDR[i];
                    int nextC = cur.c + HLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Horizontal == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c - 1 == goalPointcoor[1] && !goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Horizontal + 1);
                }
                if (field[cur.r][cur.c - 1].Horizontal > field[cur.r][cur.c].Horizontal + 1) {
                    field[cur.r][cur.c - 1].Horizontal = field[cur.r][cur.c].Horizontal + 1;
                    q.add(new pos(cur.r, cur.c - 1, false));
                }
                break;
            case 1:
                // Vertical 우로 이동
                for (int i = 0; i < HLDR.length; i++) {
                    int nextR = cur.r + HLDR[i];
                    int nextC = cur.c - HLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Horizontal == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c + 1 == goalPointcoor[1] && !goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Horizontal + 1);
                }
                if (field[cur.r][cur.c + 1].Horizontal > field[cur.r][cur.c].Horizontal + 1) {
                    field[cur.r][cur.c + 1].Horizontal = field[cur.r][cur.c].Horizontal + 1;
                    q.add(new pos(cur.r, cur.c + 1, false));
                }
                break;
            case 2:
                // Vertical 아래로 이동
                for (int i = 0; i < HUDR.length; i++) {
                    int nextR = cur.r - HUDR[i];
                    int nextC = cur.c + HUDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Horizontal == tree) {
                        return false;
                    }
                }
                if (cur.r + 1 == goalPointcoor[0] && cur.c == goalPointcoor[1] && !goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Horizontal + 1);
                }
                if (field[cur.r + 1][cur.c].Horizontal > field[cur.r][cur.c].Horizontal + 1) {
                    field[cur.r + 1][cur.c].Horizontal = field[cur.r][cur.c].Horizontal + 1;
                    q.add(new pos(cur.r + 1, cur.c, false));
                }
                break;
            case 3:
                // 위로 이동
                for (int i = 0; i < HUDR.length; i++) {
                    int nextR = cur.r + HUDR[i];
                    int nextC = cur.c + HUDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Horizontal == tree) {
                        return false;
                    }
                }
                if (cur.r - 1 == goalPointcoor[0] && cur.c == goalPointcoor[1] && !goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Horizontal + 1);
                }
                if (field[cur.r - 1][cur.c].Horizontal > field[cur.r][cur.c].Horizontal + 1) {
                    field[cur.r - 1][cur.c].Horizontal = field[cur.r][cur.c].Horizontal + 1;
                    q.add(new pos(cur.r - 1, cur.c, false));
                }
                break;
            case 4:
                // 회전
                for (int i = 0; i < ALLDC.length; i++) {
                    int nextR = cur.r + ALLDR[i];
                    int nextC = cur.c + ALLDC[i];
                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || field[nextR][nextC].Horizontal == tree) {
                        return false;
                    }
                }
                if (cur.r == goalPointcoor[0] && cur.c == goalPointcoor[1] && goalPoint) {
                    flag = Math.min(flag, field[cur.r][cur.c].Horizontal + 1);
                }
                if (field[cur.r][cur.c].Vertical > field[cur.r][cur.c].Horizontal + 1) {
                    field[cur.r][cur.c].Vertical = field[cur.r][cur.c].Horizontal + 1;
                    q.add(new pos(cur.r, cur.c, true));
                }
        }
        return true;
    }
}