import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static final int sushiCount = 200000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        LinkedList<Integer>[] sushi = new LinkedList[sushiCount];
        int[] people = new int[N+1];

        for (int i = 0; i < sushiCount; i++) {
            sushi[i] = new LinkedList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tempt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tempt; j++) {
                sushi[Integer.parseInt(st.nextToken())-1].add(i);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int tempt = Integer.parseInt(st.nextToken())-1;
            if(!sushi[tempt].isEmpty()) {
                people[sushi[tempt].poll()]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(people[i]).append(" ");
        }

        System.out.println(sb);
    }
}