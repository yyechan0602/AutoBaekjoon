import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            stack.push(Integer.parseInt(br.readLine()));
        }

        int max = 0;
        int count = 0;

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            if(max<cur) {
                max = cur;
                count++;
            }
        }

        System.out.println(count);
    }
}