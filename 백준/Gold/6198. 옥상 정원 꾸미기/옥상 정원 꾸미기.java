import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();
        long result = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            while (!q.isEmpty() && q.peek() <= num) {
                q.poll();
            }
            result += q.size();
            q.add(num);
        }

        System.out.println(result);
    }
}