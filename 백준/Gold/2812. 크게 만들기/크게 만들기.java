import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        Stack<Integer> stack = new Stack<>();
        stack.add(Character.getNumericValue(str.charAt(0)));

        for (int i = 1; i < N; i++) {
            while (!stack.isEmpty() && stack.peek() < Character.getNumericValue(str.charAt(i)) && K > 0) {
                stack.pop();
                K--;
            }
            stack.add(Character.getNumericValue(str.charAt(i)));
        }

        while(K-->0){
            stack.pop();
        }

        StringBuilder sb =new StringBuilder();

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        sb.reverse();
        System.out.println(sb);
    }
}