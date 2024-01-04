import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[] rowExist = new boolean[N];
        boolean[] colExist = new boolean[M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == 'X') {
                    rowExist[i] = true;
                    colExist[j] = true;
                }
            }
        }

        int rowCount = 0;
        int colCount = 0;

        for (int i = 0; i < N; i++) {
            if (!rowExist[i]) rowCount++;
        }

        for (int i = 0; i < M; i++) {
            if (!colExist[i]) colCount++;
        }

        System.out.println(Math.max(rowCount, colCount));
    }
}