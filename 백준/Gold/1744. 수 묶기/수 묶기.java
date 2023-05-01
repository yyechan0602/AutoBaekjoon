import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        while (!positive.isEmpty()) {
            int num = positive.poll();

            if (!positive.isEmpty() && (num * positive.peek() > num + positive.peek())) {
                sum += ((long) num * positive.poll());
            } else {
                sum += num;
            }
        }
        while (!negative.isEmpty()) {
            int num = negative.poll();

            if (!negative.isEmpty() && (num * negative.peek() > num + negative.peek())) {
                sum += ((long) num * negative.poll());
            } else {
                sum += num;
            }
        }

        System.out.println(sum);
    }
}