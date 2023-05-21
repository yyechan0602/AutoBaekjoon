import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] solution = new int[N];
        int forth = 0;
        int back = N - 1;

        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        int forthNum = solution[0];
        int backNum = solution[N - 1];

        while (forth != back) {
            if (Math.abs(forthNum + backNum) > Math.abs(solution[forth] + solution[back])) {
                forthNum = solution[forth];
                backNum = solution[back];
            }


            if (solution[forth] + solution[back] < 0) {
                forth++;
            } else if (solution[forth] + solution[back] > 0) {
                back--;
            } else {
                break;
            }
        }

        System.out.println(forthNum + " " + backNum);
    }
}