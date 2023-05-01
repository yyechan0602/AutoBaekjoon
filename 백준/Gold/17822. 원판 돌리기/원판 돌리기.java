import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.chrono.Era;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int T;
    static int[][] roundPlate;

    static int[] DR = {-1, 0, 1, 0};
    static int[] DC = {0, 1, 0, -1};

    static final int empty = -5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        roundPlate = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                roundPlate[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //System.out.println();
        //ShowAll();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            TotalRotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        CountNum();
    }

    static void TotalRotate(int x, int d, int k) {
        for (int i = 0; i < k; i++) {
            Rotate(x, d);
        }
        //System.out.println("지우기전 :");
        //ShowAll();
        EraseNum();
        //ShowAll();
    }

    static void EraseNum() {
        Queue<Integer[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 동서남북 체크
                for (int k = 0; k < DR.length; k++) {
                    int nextR = DR[k] + i;
                    int nextC = DC[k] + j;

                    // 예외처리
                    if (nextR < 0 || nextR >= N) {
                        continue;
                    }
                    if (nextC < 0) {
                        nextC = M - 1;
                    } else if (nextC >= M) {
                        nextC = 0;
                    }
                    if (roundPlate[i][j] == empty || roundPlate[nextR][nextC] == empty) {
                        continue;
                    }


                    if (roundPlate[i][j] == roundPlate[nextR][nextC]) {
                        q.add(new Integer[]{i, j});
                        q.add(new Integer[]{nextR, nextC});
                    }
                }
            }
        }


        if (q.isEmpty()) {
            int sum = 0;
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (roundPlate[i][j] != empty) {
                        sum += roundPlate[i][j];
                        count++;
                    }
                }
            }
            double avg = (sum * 1.0) / count;
            //System.out.println(avg);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (roundPlate[i][j] != empty) {
                        if (roundPlate[i][j] > avg) {
                            roundPlate[i][j]--;
                        } else if (roundPlate[i][j] < avg) {
                            roundPlate[i][j]++;
                        }
                    }
                }
            }
        } else {
            while (!q.isEmpty()) {
                Integer[] remove = q.poll();
                roundPlate[remove[0]][remove[1]] = empty;
            }
        }
    }

    static void Rotate(int x, int d) {
        for (int i = 1; i * x -1 < N; i++) {
            if (d == 0) {  // 시계방향
                int num = roundPlate[x * i - 1][M - 1];
                for (int j = M - 1; j > 0; j--) {
                    roundPlate[x * i - 1][j] = roundPlate[x * i - 1][j - 1];
                }
                roundPlate[x * i - 1][0] = num;
            } else {  // 반시계방향
                int num = roundPlate[x * i - 1][0];
                for (int j = 1; j < M; j++) {
                    roundPlate[x * i - 1][j - 1] = roundPlate[x * i - 1][j];
                }
                roundPlate[x * i - 1][M - 1] = num;
            }
        }
    }

    static void CountNum() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (roundPlate[i][j] != empty) {
                    count += roundPlate[i][j];
                }
            }
        }
        System.out.println(count);
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(roundPlate[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");
    }
}