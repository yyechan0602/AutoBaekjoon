import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] lectures = new int[n][2];
        int max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken());
            lectures[i][1] = Integer.parseInt(st.nextToken());
            max = Math.max(max, lectures[i][1]);
        }

        Arrays.sort(lectures, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) return -1;
                else if(o1[1] == o2[1]) return 0;
                return 1;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int result = 0;
        int index = 0;

        for (int i = max; i > 0; i--) {
            while (index < n && lectures[index][1] >= i) {
                queue.add(lectures[index++][0]);
            }
            if (!queue.isEmpty()) {
                result += queue.poll();
            }
        }

        System.out.println(result);
    }
}