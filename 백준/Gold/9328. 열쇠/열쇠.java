import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int[] DR = {0, 1, 0, -1};
    static final int[] DC = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] building = new char[h + 2][w + 2];
            int documentCount = 0;

            // buidling 채우기
            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(building[i], '.');
            }

            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    building[i][j] = str.charAt(j - 1);
                }
            }

            // keys 채우기
            String str = br.readLine();
            boolean[] keys = new boolean[26];

            if (str.charAt(0) != '0') {
                for (int i = 0; i < str.length(); i++) {
                    keys[str.charAt(i) - 'a'] = true;
                }
            }

            // doors 채우기
            Queue<Integer[]>[] doors = new LinkedList[26];
            for (int i = 0; i < 26; i++) {
                doors[i] = new LinkedList<Integer[]>();
            }

            // 시작
            Queue<Integer[]> q = new LinkedList<>();
            q.add(new Integer[]{0, 0});

            while (true) {
                boolean flag = true;
                while (!q.isEmpty()) {
                    Integer[] cur = q.poll();

                    for (int i = 0; i < DR.length; i++) {
                        int nextR = cur[0] + DR[i];
                        int nextC = cur[1] + DC[i];

                        if (nextR < 0 || nextR >= h + 2 || nextC < 0 || nextC >= w + 2 || building[nextR][nextC] == '*')
                            continue;

                        if (building[nextR][nextC] >= 'A' && building[nextR][nextC] <= 'Z') {
                            doors[(building[nextR][nextC] - 'A')].add(new Integer[]{nextR, nextC});
                            building[nextR][nextC] = '*';
                            continue;
                        }

                        if (building[nextR][nextC] >= 'a' && building[nextR][nextC] <= 'z')
                            keys[building[nextR][nextC] - 'a'] = true;


                        if (building[nextR][nextC] == '$') documentCount++;

                        building[nextR][nextC] = '*';
                        q.add(new Integer[]{nextR, nextC});
                    }


                }


                for (int i = 0; i < doors.length; i++) {
                    while (!doors[i].isEmpty() && keys[i]) {
                        q.add(doors[i].poll());
                        flag = false;
                    }
                }

                if (flag) break;
            }

            System.out.println(documentCount);
        }
    }
}