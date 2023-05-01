import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] input;
    static int[] output;
    static final int notExist = 10001;

    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[N];
        output = new int[N + 1];
        Arrays.fill(output, notExist);
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        SortDict();
    }

    static void SortDict() {
        int index = notExist;
        int curindex = 0;
        if (N != 0) {
            output[curindex++] = input[0];
        }
        for (int i = 1; i < N; i++) {
            if (index == notExist) {
                if (output[curindex - 1] + 1 != input[i]) {
                    output[curindex] = input[i];
                    curindex++;
                } else {
                    index = curindex++;
                    output[curindex++] = input[i];
                }
            } else {
                if (output[index - 1] + 1 != input[i]) {
                    output[index] = input[i];
                    index = notExist;
                } else {
                    output[curindex++] = input[i];
                }
            }
        }
        if (index != notExist) {
            int flag = index - 1;
            while (flag >= 0 && output[flag] + 1 == output[N]) {
                flag--;
            }
            flag++;

            int count = index - flag;
            for (int i = index + 1; i < N + 1; i++) {
                output[flag++] = output[N];
            }
            for (int i = 0; i < count; i++) {
                output[flag++] = output[N] - 1;
            }
        }
        ShowAll();
    }

    static void ShowAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(output[i] + " ");
        }
        System.out.println(sb);
    }

    static void ShowTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N + 1; i++) {
            if (output[i] == notExist) {
                sb.append(". ");
            } else {
                sb.append(output[i] + " ");
            }
        }
        System.out.println(sb);
    }
}