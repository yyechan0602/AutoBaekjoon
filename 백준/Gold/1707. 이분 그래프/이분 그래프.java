import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] matrix;
    static final int EMPTY = 0;
    static final int RED = 1;
    static final int BLUE = 2;
    static int V;
    static int[] nums;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            result = true;
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            matrix = new ArrayList[V];
            nums = new int[V];

            for (int j = 0; j < V; j++) {
                matrix[j] = new ArrayList<>();
            }

            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                matrix[a - 1].add(b - 1);
                matrix[b - 1].add(a - 1);
            }

            for (int j = 0; j < V; j++) {
                if (nums[j] == EMPTY) {
                    nums[j] = RED;
                    DFS(j);
                }
            }

            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static void DFS(int num) {
        if (!result) return;
        int color = nums[num] == RED ? BLUE : RED;
        while (matrix[num].size() != 0) {
            int target = matrix[num].get(0);
            matrix[num].remove(0);
            if (nums[target] == EMPTY) {
                nums[target] = color;
                DFS(target);
            } else if (nums[target] != color) {
                result = false;
            }

        }
    }
}