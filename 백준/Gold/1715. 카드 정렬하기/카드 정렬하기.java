import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priQue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            priQue.add(Integer.parseInt(br.readLine()));
        }
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            int num1 = priQue.poll();
            int num2 = priQue.poll();
            result += num1 + num2;
            priQue.add(num1 + num2);
        }
        System.out.println(result);
    }
}