import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

public class Main {
    static private class board {
        int[] nums;

        public board() {
            nums = new int[10];
        }

        // b1 b2
        // b3 curBoard
        public void compare(board b1, board b2, board b3, int num) {
            for (int i = 0; i < 10; i++) {
                this.nums[i] = b2.nums[i] + b3.nums[i] - b1.nums[i];
            }

            nums[num - 1]++;
        }

        // b1 b2
        // b3 curBoard
        public int compareQuery(int x1, int y1, int x2, int y2) {
            int count = 0;

            for (int i = 0; i < 10; i++) {
                if (matrix[x2][y2].nums[i] - matrix[x1 - 1][y2].nums[i] - matrix[x2][y1 - 1].nums[i] + matrix[x1 - 1][y1 - 1].nums[i] > 0)
                    count++;
            }

            return count;
        }
    }

    static board[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        matrix = new board[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                matrix[i][j] = new board();
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                matrix[i][j].compare(matrix[i - 1][j - 1], matrix[i - 1][j], matrix[i][j - 1], Integer.parseInt(st.nextToken()));
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < Q; j++) {
            st = new StringTokenizer(br.readLine());

            int count = 0;
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 10; i++) {
                if (matrix[x2][y2].nums[i] - matrix[x1 - 1][y2].nums[i] - matrix[x2][y1 - 1].nums[i] + matrix[x1 - 1][y1 - 1].nums[i] > 0)
                    count++;
            }

            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}