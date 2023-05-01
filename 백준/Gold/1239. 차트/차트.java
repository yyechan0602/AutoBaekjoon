import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dogs;
    static boolean[] isUsed;
    static int[] graph;
    static int maxLine;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dogs = new int[N];
        graph = new int[N];
        isUsed = new boolean[N];
        maxLine = 0;

        for (int i = 0; i < N; i++) {
            dogs[i] = Integer.parseInt(st.nextToken());
        }

        Total();
    }

    static void Total() {
        graph[0] = dogs[0];
        isUsed[0] = true;
        MakeGraph(1);
        System.out.println(maxLine);
    }

    static void MakeGraph(int count) {  // DFS를 응용한 순열 만들기 코드
        if (count < N) {
            for (int i = 1; i < N; i++) {
                if (isUsed[i]) {
                    continue;
                }

                isUsed[i] = true;
                graph[count] = dogs[i] + graph[count - 1];
                MakeGraph(count + 1);
                isUsed[i] = false;
            }
        } else { // 개수 세는 코드
            int line = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    if (graph[i] + 50 == graph[j]) {
                        line++;
                    }
                }
            }

            maxLine = Math.max(maxLine, line);
            //ShowAll();
            //System.out.println(maxLine);
        }
    }

    static void ShowAll() {
        for (int i = 0; i < N; i++) {
            System.out.print(graph[i] + " ");
        }
    }
}