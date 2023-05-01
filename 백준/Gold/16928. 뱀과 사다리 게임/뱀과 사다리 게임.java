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
    static int[] field;
    static int[] count;
    static final int MAX = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        field = new int[100];
        count = new int[100];
        Arrays.fill(count, MAX);
        count[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            field[Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            field[Integer.parseInt(st.nextToken()) - 1] = Integer.parseInt(st.nextToken()) - 1;
        }

        BFS();
        System.out.println();
    }

    static void BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= 6; i++) {
                int nextPos = cur + i;
                if (nextPos >= 100) {
                    continue;
                }
                if (field[nextPos] == 0) {
                    if (count[nextPos] > count[cur] + 1) {
                        count[nextPos] = count[cur] + 1;
                        q.add(nextPos);
                    }
                } else {
                    if (count[field[nextPos]] > count[cur] + 1) {
                        count[field[nextPos]] = count[cur] + 1;
                        q.add(field[nextPos]);
                    }
                }
            }
            if (count[99] != MAX) {
                System.out.println(count[99]);
                break;
            }
        }
    }
}