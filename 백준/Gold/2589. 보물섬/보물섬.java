import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] map;
    static int[][] visited;
    static final int maxInt = 9999;

    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'L') {
                    map[i][j] = true;
                }
            }
        }

        Total();
    }

    static void Total() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    for (int k = 0; k < N; k++) {
                        Arrays.fill(visited[k], maxInt);
                    }
                    max = Math.max(max, BFS(i, j));
                }
            }
        }
        System.out.println(max);
    }

    static int BFS(int i, int j) {
        Queue<Integer[]> q = new LinkedList<>();
        q.add(new Integer[]{i, j});
        visited[i][j] = 0;

        while (!q.isEmpty()) {
            Integer[] cur = q.poll();

            for (int k = 0; k < DR.length; k++) {
                int nextR = cur[0] + DR[k];
                int nextC = cur[1] + DC[k];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || !map[nextR][nextC] ||visited[nextR][nextC] <= visited[cur[0]][cur[1]] + 1) {
                    continue;
                }

                visited[nextR][nextC] = visited[cur[0]][cur[1]] + 1;
                q.add(new Integer[]{nextR, nextC});
            }
        }
        int max = 0;
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < M; l++) {
                if (visited[k][l] != maxInt) {
                    max = Math.max(visited[k][l], max);
                }
            }
        }
        //showAll();
        return max;
    }
    static void showAll(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!map[i][j]){
                    System.out.print(". ");
                }else {
                    if (visited[i][j] != maxInt) {
                        System.out.print(visited[i][j] + " ");
                    } else {
                        System.out.print("* ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}