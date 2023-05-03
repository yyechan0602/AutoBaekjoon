import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] cases;
    static final int CASERANGE = 31;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        MakeCases();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println(cases[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]);
        }

        //ShowAll();
    }

    private static void MakeCases() {
        cases = new int[CASERANGE][CASERANGE];

        for (int i = 1; i < CASERANGE; i++) {
            cases[i][i] = 1;
            cases[i][1] = i;
            cases[1][i] = i;
        }

        for (int i = 1; i < CASERANGE; i++) {
            for (int j = i + 1; j < CASERANGE; j++) {
                int decrement = 0;
                while (i <= j + decrement) {
                    cases[i][j] += cases[i - 1][j - 1 + decrement];
                    decrement--;
                }
            }
        }
    }

    private static void ShowAll(){
        for (int i = 0; i < CASERANGE; i++) {
            for (int j = 0; j < CASERANGE; j++) {
                System.out.printf("%9d", cases[i][j]);
            }
            System.out.println();
        }
    }
}