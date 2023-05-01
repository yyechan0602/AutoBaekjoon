import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        N = 2 * N + 1;
        String str = br.readLine();
        int count = 0;
        int length = 0;
        boolean isO = true;

        for (int i = 0; i < M; i++) {
            if (isO) {
                if (str.charAt(i) == 'I') {
                    length++;
                    isO = false;
                } else {
                    length = 0;
                    isO = true;
                }

                if (length == N) {
                    count++;
                    length -= 2;
                }
            } else {
                if (str.charAt(i) == 'O') {
                    length++;
                    isO = true;
                } else {
                    length = 1;
                    isO = false;
                }

                if (length == N) {
                    count++;
                    length -= 2;
                }
            }
        }

        System.out.println(count);
    }
}