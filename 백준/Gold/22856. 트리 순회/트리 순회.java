import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][2];
        count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            graph[node][0] = Integer.parseInt(st.nextToken());
            graph[node][1] = Integer.parseInt(st.nextToken());
        }

        Count(1, false);

        System.out.println(count);
    }

    static void Count(int num, boolean isDouble) {
        if (graph[num][1] != -1) {
            Count(graph[num][1], isDouble);
            if (isDouble) {
                count += 2;
            } else {
                count++;
            }
            isDouble = true;
        }

        if (graph[num][0] != -1) {
            Count(graph[num][0], true);
            count += 2;
        }
    }
}