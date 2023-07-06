import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            String str = br.readLine();
            int num = 1;
            int result = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'O') {
                    result += num++;
                } else {
                    num = 1;
                }
            }
            System.out.println(result);
        }
    }
}