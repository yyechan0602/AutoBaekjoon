import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int max = Math.max(a, b);
        StringBuilder sb = new StringBuilder();
        
        if(a + b - 1 > N){
            System.out.println(-1);
            return;
        }

        if (a != 1) {
            for (int i = 0; i < N - a - b + 1; i++) {
                sb.append(1).append(" ");
            }
        }


        for (int i = 1; i < a; i++) {
            sb.append(i).append(" ");
        }

        sb.append(max).append(" ");

        if (a == 1) {
            for (int j = 0; j < N - a - b + 1; j++) {
                sb.append(1).append(" ");
            }
        }

        for (int i = b - 1; i > 0; i--) {
            sb.append(i).append(" ");
        }

        System.out.print(sb);
    }
}