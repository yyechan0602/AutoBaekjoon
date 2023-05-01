import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int width;
    static int height;
    static int[][] cheese;
    static boolean[][] visited;
    static Queue<Integer[]> q;
    static final int[] DR = {-1, 0, 1, 0};
    static final int[] DC = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        cheese = new int[height + 2][width + 2];
        visited = new boolean[height + 2][width + 2];
        q = new LinkedList<>();
        for (int i = 0; i < width + 2; i++) {
            cheese[0][i] = 0;
            cheese[height + 1][i] = 0;
        }
        for (int i = 1; i < height + 1; i++) {
            cheese[i][0] = 0;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < width + 1; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
            cheese[i][width + 1] = 0;
        }
        //ShowAll();
        TotalMelt();
    }

    static void TotalMelt() {
        int time = 0;
        int result = 0;
        while (FindCheese() != 0) {
            time++;
            result = FindCheese();
            Melt(0, 0);
            //ShowAll();
        }
        System.out.println(time);
        System.out.println(result);
    }

    static void Melt(int r, int c) {
        for (int i = 0; i < height + 2; i++) {
            Arrays.fill(visited[i], false);
        }
        if (!visited[r][c]) {
            q.add(new Integer[]{r, c});
        }
        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            if (cheese[cur[0]][cur[1]] == 1) {
                continue;
            }
            for (int i = 0; i < DR.length; i++) {
                int nextR = cur[0] + DR[i];
                int nextC = cur[1] + DC[i];
                if (nextR < 0 || nextR >= height + 2 || nextC < 0 || nextC >= width + 2 || visited[nextR][nextC]) {
                    continue;
                }
                visited[nextR][nextC] = true;
                if (cheese[nextR][nextC] == 0) {
                    q.add(new Integer[]{nextR, nextC});
                } else {
                    cheese[nextR][nextC] = 0;
                }
            }

        }
    }

    static int FindCheese() {
        int result = 0;
        for (int i = 1; i < height + 2; i++) {
            for (int j = 1; j < width + 2; j++) {
                if (cheese[i][j] == 1) {
                    result += 1;
                }
            }
        }
        return result;
    }

    static void ShowAll() {
        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                if (cheese[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println("=================================");
    }
}