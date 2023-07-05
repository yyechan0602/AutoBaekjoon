import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static Stack<Integer> stack;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        stack = new Stack<>();
        flag = false;
        stack.push(0);

        for (int i = str.length(); i > 0; i--) {
            Total(str.charAt(i - 1));
        }

        System.out.println(stack.pop());
    }

    static void Total(char message) {
        if (flag) {
            flag = false;
            stack.push(stack.pop() * Character.getNumericValue(message)+stack.pop());
            return;
        }

        if (message == ')') {
            stack.push(0);
        } else if (message == '(') {
            flag = true;
        } else {
                stack.push(1 + stack.pop());
        }
    }
}