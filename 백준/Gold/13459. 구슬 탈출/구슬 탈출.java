import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class balls {
        int[] red;
        int[] blue;

        public balls(int[] red, int[] blue) {
            this.red = red;
            this.blue = blue;
        }
    }

    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};
    static int N;
    static int M;
    static int[][] field;
    static final int B = 3;
    static final int R = 2;
    static final int wall = -1;
    static final int hole = -2;
    static final int empty = 0;
    static Queue<balls> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        int[] red = {};
        int[] blue = {};
        field = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '#') {
                    field[i][j] = wall;
                } else if (str.charAt(j) == '.') {
                    field[i][j] = empty;
                } else if (str.charAt(j) == 'R') {
                    field[i][j] = R;
                    red = new int[]{i, j};
                } else if (str.charAt(j) == 'B') {
                    field[i][j] = B;
                    blue = new int[]{i, j};
                } else {
                    field[i][j] = hole;
                }
            }
        }
        q.add(new balls(red, blue));
        BFS();
    }

    static void BFS() {
        int flag = 0;
        int time = 10;
        for (int t = 0; t < time; t++) {
            //System.out.println("============= " + t);
            int length = q.size();
            while (length-- > 0) {
                balls cur = q.poll();
                for (int i = 0; i < DR.length; i++) {
                    flag = 0;
                    int nextRedR = cur.red[0] + DR[i];
                    int nextRedC = cur.red[1] + DC[i];
                    while (nextRedR >= 0 && nextRedR < N && nextRedC >= 0 && nextRedC < M && field[nextRedR][nextRedC] != wall && !(cur.blue[0] == nextRedR && cur.blue[1] == nextRedC)) {
                        if (field[nextRedR][nextRedC] == hole) {
                            flag = 1;
                            nextRedR = -2;
                            nextRedC = -2;
                        }
                        nextRedR += DR[i];
                        nextRedC += DC[i];
                    }
                    nextRedR -= DR[i];
                    nextRedC -= DC[i];
                    int nextBlueR = cur.blue[0] + DR[i];
                    int nextBlueC = cur.blue[1] + DC[i];
                    while (nextBlueR >= 0 && nextBlueR < N && nextBlueC >= 0 && nextBlueC < M && field[nextBlueR][nextBlueC] != wall && !(nextBlueR == nextRedR && nextBlueC == nextRedC)) {
                        if (field[nextBlueR][nextBlueC] == hole) {
                            flag = -1;
                            nextBlueR = -2;
                            nextBlueC = -2;
                        }
                        nextBlueR += DR[i];
                        nextBlueC += DC[i];
                    }
                    nextBlueR -= DR[i];
                    nextBlueC -= DC[i];
                    nextRedR += DR[i];
                    nextRedC += DC[i];
                    while (nextRedR >= 0 && nextRedR < N && nextRedC >= 0 && nextRedC < M && field[nextRedR][nextRedC] != wall && !(nextBlueR == nextRedR && nextBlueC == nextRedC)) {
                        if (field[nextRedR][nextRedC] == hole && flag != -1) {
                            flag = 1;
                            nextRedR += DR[i];
                            nextRedC += DC[i];
                        }
                        nextRedR += DR[i];
                        nextRedC += DC[i];
                    }
                    nextRedR -= DR[i];
                    nextRedC -= DC[i];
                    if (flag == 1) {
                        System.out.println(1);
                        return;
                    }
                    if (flag != -1) {
                        q.add(new balls(new int[]{nextRedR, nextRedC}, new int[]{nextBlueR, nextBlueC}));
                        //ShowAll(i, nextRedR, nextRedC, nextBlueR, nextBlueC);

                    }
                }
            }
        }
        if (flag == -1) {
            System.out.println(0);
        } else {
            System.out.println(flag);
        }
    }

    static void ShowAll(int index, int nextRedR, int nextRedC, int nextBlueR, int nextBlueC) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == nextBlueR && j == nextBlueC) {
                    System.out.print("B ");
                } else if (i == nextRedR && j == nextRedC) {
                    System.out.print("R ");
                } else if (field[i][j] == wall) {
                    System.out.print("# ");
                }else if(field[i][j] == hole){
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("==============================" + index);
    }
}