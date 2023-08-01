import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> buildings = new Stack<>();
        int count = 0;

        for (int i = 0; i <= n; i++) {
            int y;
            if(i != n) {
                st = new StringTokenizer(br.readLine());
                Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
            }else{
                y = 0;
            }
            boolean flag = true;

            while (!buildings.isEmpty()) {
                if (buildings.peek() > y) {
                    buildings.pop();
                    count++;
                } else if (buildings.peek() == y) {
                    flag = false;
                    break;
                } else {
                    break;
                }
            }

            if(flag) {
                buildings.add(y);
            }
        }

        System.out.println(count);
    }
}