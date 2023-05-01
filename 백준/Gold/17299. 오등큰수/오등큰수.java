import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] count = new int[1000002];
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());


        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            count[nums[i]]++;
        }

        StringBuilder sb = new StringBuilder();
        stack.push(0);
        for (int i = 1; i < N; i++) {
            while (true) {
                if(stack.isEmpty()){
                    break;
                }
                if(!(count[nums[stack.peek()]] < count[nums[i]])){
                    break;
                }
                nums[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        while (true) {
            if(stack.isEmpty()){
                break;
            }
            nums[stack.pop()] = -1;
        }
        for (int i = 0; i < N; i++) {
            sb.append(nums[i]).append(" ");
        }
        System.out.println(sb);
    }
}