import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        int Scount = 0;
        int Tcount = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'B') Scount++;
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == 'B') Tcount++;
        }

        boolean isEven = false;

        // 짝수
        if ((Tcount - Scount) % 2 == 0) {
            isEven = true;
        }

        for (int i = 0; i < (Tcount - Scount) / 2.0; i++) {
            if (isEven) {
                while (true) {
                    if (T.charAt(0) != 'A') {
                        T.deleteCharAt(0);
                        break;
                    }
                    T.deleteCharAt(0);
                }
            }
            isEven = true;

            while (true) {
                if (T.charAt(T.length() - 1) != 'A') {
                    T.deleteCharAt(T.length() - 1);
                    break;
                }
                T.deleteCharAt(T.length() - 1);
            }
        }

        if ((Tcount - Scount) % 2 != 0) {
            T.reverse();
        }

        //System.out.println(S);
        //System.out.println(T);

        // 남은 T'의 길이가 S보다 짧으면 만들 수 없음
        if (S.length() > T.length()) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != T.charAt(i)) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }
}