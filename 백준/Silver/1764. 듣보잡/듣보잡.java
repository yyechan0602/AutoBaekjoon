import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> before = new HashSet<>(N);
        LinkedList<String> result = new LinkedList<>();
        int length = 0;

        for (int i = 0; i < N; i++) {
            before.add(br.readLine());
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (before.contains(str)) {
                result.add(str);
                length++;
            }
        }

        Collections.sort(result);
        System.out.println(length);

        while(!result.isEmpty()){
            System.out.println(result.pop());
        }
    }
}