

import java.io.*;
import java.util.*;

public class Main {
    static class pos {
        int m;
        int s;
        int d;
        boolean isExplode;
        int count;
        int isSame;

        public pos(int m, int s, int d, int count, int isSame, boolean isExplode) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.count = count;
            this.isSame = isSame;
            this.isExplode = isExplode;
        }

        public boolean BigBang(int m, int s, int d) {
            if (this.m != 0) {
                this.isExplode = true;
                this.count += 1;
                if (this.isSame == notSame) {
                    this.isSame = notSame;
                } else if ((this.isSame == empty) || (this.isSame == d % 2)) {
                    this.isSame = d % 2;
                } else {
                    this.isSame = notSame;
                }
            } else {
                this.isSame = d % 2;
                this.isExplode = false;
                this.count = 1;
            }
            this.m += m;
            this.s += s;
            this.d += d;
            this.d %= 8;
            return isExplode;
        }

        public void reset() {
            this.m = 0;
            this.s = 0;
            this.d = 0;
            this.count = 0;
            this.isSame = empty;
            this.isExplode = false;
        }
    }


    static pos[][] field;
    static pos[][] curfield;
    static Queue<Integer[]> fire;
    static int N;
    static int M;
    static int K;
    static int empty = -1;
    static int notSame = -2;
    static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] EDR = {-1, 0, 1, 0};
    static final int[] EDC = {0, 1, 0, -1};
    static final int[] ODR = {-1, 1, 1, -1};
    static final int[] ODC = {1, 1, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        curfield = new pos[N][N];
        field = new pos[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                field[i][j] = new pos(0, 0, 0, 1, empty, false);
                curfield[i][j] = new pos(0, 0, 0, 1, empty, false);
            }
        }
        fire = new LinkedList<>();
        int r;
        int c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            field[r][c] = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, empty, false);
            fire.add(new Integer[]{r, c});
        }
        TotalTime(K);
    }

    static void TotalTime(int time) {
        //ShowAll();
        for (int i = 0; i < time; i++) {
            MoveFire();
            //ShowAll();
        }
        Count();
    }

    static void MoveFire() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                curfield[i][j] = new pos(field[i][j].m, field[i][j].s, field[i][j].d, field[i][j].count, field[i][j].isSame, field[i][j].isExplode);
                field[i][j].reset();
            }
        }
        int count = fire.size();
        int nextR;
        int nextC;
        int curm;
        int curs;
        int curd;
        Integer[] cur;
        for (int i = 0; i < count; i++) {
            cur = fire.poll();
            curm = curfield[cur[0]][cur[1]].m;
            curs = curfield[cur[0]][cur[1]].s;
            curd = curfield[cur[0]][cur[1]].d;
            if (curm <= 0) {
                continue;
            }
            if (!curfield[cur[0]][cur[1]].isExplode) {
                nextR = (cur[0] + ((DR[curd] * curs) % N + N)) % N;
                nextC = (cur[1] + ((DC[curd] * curs) % N + N)) % N;
                if (!field[nextR][nextC].BigBang(curm, curs, curd)) {
                    fire.add(new Integer[]{nextR, nextC});
                }
            } else {
                curm /= 5;
                curs /= curfield[cur[0]][cur[1]].count;
                if (curm <= 0) {
                    continue;
                }
                if (curfield[cur[0]][cur[1]].isSame == notSame) {
                    for (int j = 0; j < ODC.length; j++) {
                        nextR = (cur[0] + ((ODR[j] * curs) % N + N)) % N;
                        nextC = (cur[1] + ((ODC[j] * curs) % N + N)) % N;
                        if (!field[nextR][nextC].BigBang(curm, curs, 2 * j + 1)) {
                            fire.add(new Integer[]{nextR, nextC});
                        }
                    }
                } else {
                    for (int j = 0; j < EDC.length; j++) {
                        nextR = (cur[0] + ((EDR[j] * curs) % N + N)) % N;
                        nextC = (cur[1] + ((EDC[j] * curs) % N + N)) % N;
                        if (!field[nextR][nextC].BigBang(curm, curs, 2 * j)) {
                            fire.add(new Integer[]{nextR, nextC});
                        }
                    }
                }
            }
        }
    }

    static void Count() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j].isExplode) {
                    result += (field[i][j].m / 5) * 4;
                } else {
                    result += field[i][j].m;
                }
            }
        }
        System.out.println(result);
    }


    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (field[i][j].m == 0) {
                    System.out.print(". ");
                } else if (field[i][j].isExplode) {
                    System.out.print(field[i][j].m + ")");
                } else {
                    System.out.print(field[i][j].m + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==========================");
    }
}
