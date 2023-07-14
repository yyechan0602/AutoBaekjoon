import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[] bulb1;
    static boolean[] bulb2;
    static boolean[] bulb3;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bulb1 = new boolean[N];
        bulb2 = new boolean[N];
        bulb3 = new boolean[N];
        int result = 0;
        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            bulb1[i] = Character.getNumericValue(str.charAt(i)) == 0;
            bulb2[i] = Character.getNumericValue(str.charAt(i)) == 0;
        }
        str = br.readLine();
        for (int i = 0; i < N; i++) {
            bulb3[i] = Character.getNumericValue(str.charAt(i)) == 0;
        }

        for (int i = 1; i < N; i++) {
            result += changeBulb(bulb1, i);
        }

        if (bulb1[N - 1] == bulb3[N - 1]) {
            System.out.println(result);
            return;
        }

        result = 1;

        bulb2[0] = !bulb2[0];
        bulb2[1] = !bulb2[1];
        for (int i = 1; i < N; i++) {
            result += changeBulb(bulb2, i);
        }

        if (bulb2[N - 1] == bulb3[N - 1]) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static int changeBulb(boolean[] bulb, int index) {
        if (bulb[index - 1] != bulb3[index - 1]) {
            bulb[index - 1] = !bulb[index - 1];
            bulb[index] = !bulb[index];
            if (index < N-1) {
                bulb[index + 1] = !bulb[index + 1];
            }
            return 1;
        }
        return 0;
    }
}