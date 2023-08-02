import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] code;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        code = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            code[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            code[i] -= Integer.parseInt(st.nextToken());
        }

        int last = 0;

        for (int i = 0; i < N; i++) {
            last = Edit(i, last);
        }

        System.out.println(count);
    }

    static private int Edit(int index, int last) {
        if (isSign(code[index], last)) {
            if (Math.abs(last) >= Math.abs(code[index])) {
                return code[index];
            } else {
                count += Math.abs(code[index]) - Math.abs(last);
                return code[index];
            }
        } else {
            count+= Math.abs(code[index]);
            return code[index];
        }
    }

    static private boolean isSign(int a, int b) {
        if (Math.abs(a + b) == Math.abs(a) + Math.abs(b)) {
            return true;
        }
        return false;
    }
}