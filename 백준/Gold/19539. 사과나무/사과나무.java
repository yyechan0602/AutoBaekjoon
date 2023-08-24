import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] tree = new int[N];
        int sum = 0;
        int odd = 0;
        int even = 0;

        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            sum += tree[i];
            even += tree[i] / 2;
            odd += tree[i] % 2;
        }

        if (sum % 3 != 0) {
            System.out.println("NO");
            return;
        }

        for (int i = 0; i <= even; i++) {
            if ((even - i) == (odd + 2 * i)){
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}