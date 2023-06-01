import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    static int cctvCount;
    static cctv[] cctvArray;
    static int[][] office;
    static int[][] visited;

    static int result = 10000;

    static class cctv {
        int r;
        int c;
        int type;

        public cctv(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static final int EMPTY = 0;
    static final int WALL = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        office = new int[N][M];
        cctvArray = new cctv[8];
        visited = new int[N][M];
        cctvCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] >= 1 && office[i][j] < 6) {
                    cctvArray[cctvCount++] = new cctv(i, j, office[i][j]);
                }
            }
        }
        DFS(0);

        System.out.println(result);
    }

    static void DFS(int cctvNum) {
        if (cctvNum == cctvCount) {
            int curResult = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 0 && office[i][j] == 0) {
                        curResult++;
                    }
                }
            }
            result = Math.min(curResult, result);
            return;
        }


        int curR = cctvArray[cctvNum].r;
        int curC = cctvArray[cctvNum].c;

        if (cctvArray[cctvNum].type == 1) {
            ControlSee(curR, curC, cctvNum, true, false, false, false);
            ControlSee(curR, curC, cctvNum, false, true, false, false);
            ControlSee(curR, curC, cctvNum, false, false, true, false);
            ControlSee(curR, curC, cctvNum, false, false, false, true);
            /*LeftSee(curR, curC, 1);
            DFS(cctvNum + 1);
            LeftSee(curR, curC, -1);

            RightSee(curR, curC, 1);
            DFS(cctvNum + 1);
            RightSee(curR, curC, -1);

            UpSee(curR, curC, 1);
            DFS(cctvNum + 1);
            UpSee(curR, curC, -1);

            DownSee(curR, curC, 1);
            DFS(cctvNum + 1);
            DownSee(curR, curC, -1);*/

        } else if (cctvArray[cctvNum].type == 2) {
            ControlSee(curR, curC, cctvNum, false, false, true, true);
            ControlSee(curR, curC, cctvNum, true, true, false, false);

        } else if (cctvArray[cctvNum].type == 3) {
            ControlSee(curR, curC, cctvNum, false, true, true, false);
            ControlSee(curR, curC, cctvNum, false, true, false, true);
            ControlSee(curR, curC, cctvNum, true, false, false, true);
            ControlSee(curR, curC, cctvNum, true, false, true, false);

        } else if (cctvArray[cctvNum].type == 4) {
            ControlSee(curR, curC, cctvNum, false, true, true, true);
            ControlSee(curR, curC, cctvNum, true, false, true, true);
            ControlSee(curR, curC, cctvNum, true, true, false, true);
            ControlSee(curR, curC, cctvNum, true, true, true, false);

        } else if (cctvArray[cctvNum].type == 5) {
            ControlSee(curR, curC, cctvNum, true, true, true, true);
        }
    }

    static void ControlSee(int r, int c, int cctvNum, boolean left, boolean right, boolean up, boolean down){
        if(left) LeftSee(r, c, 1);
        if(right) RightSee(r, c, 1);
        if(up) UpSee(r, c, 1);
        if(down) DownSee(r, c, 1);

        DFS(cctvNum + 1);

        if(left) LeftSee(r, c, -1);
        if(right) RightSee(r, c, -1);
        if(up) UpSee(r, c, -1);
        if(down) DownSee(r, c, -1);
    }

    static void LeftSee(int r, int c, int value) {
        while (true) {
            if (c < 0 || office[r][c] == 6) {
                break;
            }
            visited[r][c--] += value;
        }
    }

    static void RightSee(int r, int c, int value) {
        while (true) {
            if (c >= M || office[r][c] == 6) {
                break;
            }
            visited[r][c++] += value;
        }
    }

    static void UpSee(int r, int c, int value) {
        while (true) {
            if (r < 0 || office[r][c] == 6) {
                break;
            }
            visited[r--][c] += value;
        }
    }

    static void DownSee(int r, int c, int value) {
        while (true) {
            if (r >= N || office[r][c] == 6) {
                break;
            }
            visited[r++][c] += value;
        }
    }
}