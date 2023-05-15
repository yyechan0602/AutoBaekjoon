import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};
    static final int MAX = 1000000;
    static final int EMPTY = MAX;
    static final int ROCK = -2;
    static final int CAVE = -3;
    static final int HEDGEHOG = 0;
    static final int WATER = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] forest = new int[R][C];

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('D', CAVE);
        map.put('S', HEDGEHOG);
        map.put('X', ROCK);
        map.put('.', EMPTY);
        map.put('*', WATER);

        Queue<Integer[]> hedQ = new LinkedList<>();
        Queue<Integer[]> waterQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                forest[i][j] = map.get(str.charAt(j));
                if (forest[i][j] == HEDGEHOG) {
                    hedQ.add(new Integer[]{i, j});
                } else if (forest[i][j] == WATER) {
                    waterQ.add(new Integer[]{i, j});
                }
            }
        }


        while (!hedQ.isEmpty()) {
            int size = hedQ.size();
            while (size-- > 0) {
                Integer[] cur = hedQ.poll();
                if(forest[cur[0]][cur[1]] == WATER){
                    continue;
                }
                for (int i = 0; i < DR.length; i++) {
                    int nextR = cur[0] + DR[i];
                    int nextC = cur[1] + DC[i];

                    if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C) {
                        continue;
                    }

                    if (forest[nextR][nextC] == CAVE) {
                        System.out.println(forest[cur[0]][cur[1]] + 1);
                        return;
                    }

                    if (forest[nextR][nextC] <= forest[cur[0]][cur[1]] + 1) {
                        continue;
                    }

                    forest[nextR][nextC] = forest[cur[0]][cur[1]] + 1;
                    hedQ.add(new Integer[]{nextR, nextC});
                }
            }

            size = waterQ.size();
            while (size-- > 0) {
                Integer[] cur = waterQ.poll();
                for (int i = 0; i < DR.length; i++) {
                    int nextR = cur[0] + DR[i];
                    int nextC = cur[1] + DC[i];

                    if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || forest[nextR][nextC] <= WATER) {
                        continue;
                    }

                    forest[nextR][nextC] = WATER;
                    waterQ.add(new Integer[]{nextR, nextC});
                }
            }

/*            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(forest[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("======================");*/
        }

        System.out.println("KAKTUS");
    }
}