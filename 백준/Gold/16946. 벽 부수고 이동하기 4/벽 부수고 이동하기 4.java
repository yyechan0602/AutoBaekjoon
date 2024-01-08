import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] maze;
    static int[][][] prefixMaze;
    static boolean[][] visited;
    static Queue<Integer[]> q;
    static Queue<Integer[]> q2;

    static final int[] DR = {0, -1, 0, 1};
    static final int[] DC = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new boolean[N][M];
        prefixMaze = new int[N][M][2];
        visited = new boolean[N][M];
        int index = 1;
        int count;

        q = new LinkedList<>();
        q2 = new LinkedList<>();

        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) != '1';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!maze[i][j]) continue;
                if (prefixMaze[i][j][0] != 0) continue;

                q2.add(new Integer[]{i, j});
                q.add(new Integer[]{i, j});
                visited[i][j] = true;
                count = BFS();

                while (!q2.isEmpty()) {
                    Integer[] cur = q2.poll();

                    prefixMaze[cur[0]][cur[1]][0] = index;
                    prefixMaze[cur[0]][cur[1]][1] = count;
                }

                index++;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j]) {
                    sb.append(0);
                    continue;
                }

                hashSet.clear();
                count = 1;

                for (int k = 0; k < DR.length; k++) {
                    int nextR = i + DR[k];
                    int nextC = j + DC[k];

                    if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M)
                        continue;

                    if (hashSet.contains(prefixMaze[nextR][nextC][0]))
                        continue;

                    hashSet.add(prefixMaze[nextR][nextC][0]);

                    count += prefixMaze[nextR][nextC][1];
                }

                sb.append(count%10);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int BFS() {
        int count = 1;
        while (!q.isEmpty()) {
            Integer[] cur = q.poll();

            for (int i = 0; i < DR.length; i++) {
                int nextR = cur[0] + DR[i];
                int nextC = cur[1] + DC[i];

                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || visited[nextR][nextC] || !maze[nextR][nextC])
                    continue;

                visited[nextR][nextC] = true;
                count++;
                q.add(new Integer[]{nextR, nextC});
                q2.add(new Integer[]{nextR, nextC});
            }
        }

        return count;
    }
}