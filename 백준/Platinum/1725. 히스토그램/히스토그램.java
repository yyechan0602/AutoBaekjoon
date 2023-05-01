import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer[]> st = new Stack<>();
        int max = 0;
        st.add(new Integer[]{Integer.parseInt(br.readLine()), 0});
        for (int i = 1; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > st.peek()[0]) {
                st.add(new Integer[]{num, i});
            } else if (num == st.peek()[0]) {
                //아무것도 안한다.
            } else {
                Integer[] arr = st.pop();
                max = Math.max(arr[0] * (i - arr[1]), max);
                while (!st.isEmpty() && num < st.peek()[0]) {
                    arr = st.pop();
                    max = Math.max(arr[0] * (i - arr[1]), max);
                }
                st.add(new Integer[]{num, arr[1]});
            }
        }
        while (!st.isEmpty()) {
            Integer[] arr = st.pop();
            max = Math.max(arr[0] * (N - arr[1]), max);
        }
        System.out.println(max);
    }
}