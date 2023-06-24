import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] party;
    static boolean[] people;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 처음이 사람숫자, 두번째가 사건숫자
        party = new boolean[N + 1][M + 1];
        people = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= num; i++) {
            people[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int tempt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tempt; j++) {
                party[Integer.parseInt(st.nextToken())][i] = true;
            }
        }

        for (int i = 1; i <= M; i++) {
            TellTruth(i);
        }

        int result = 0;
        for (int i = 1; i <= M; i++) {
            int value = 1;
            for (int j = 1; j <= N; j++) {
                if (party[j][i] && people[j]) {
                    value = 0;
                    break;
                }
            }
            result += value;
        }
        System.out.println(result);
    }

    static void TellTruth(int num) {
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (party[i][num] && people[i]) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = 1; i <= N; i++) {
                if (party[i][num] && !people[i]) {
                    people[i] = true;
                    for (int j = 1; j <= M; j++) {
                        TellTruth(j);
                    }
                }
            }
        }
    }
}