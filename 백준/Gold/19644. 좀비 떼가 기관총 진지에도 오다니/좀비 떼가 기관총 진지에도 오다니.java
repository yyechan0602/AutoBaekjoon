import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int ML = Integer.parseInt(st.nextToken());
        int MK = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        boolean[] shot = new boolean[L];
        int damage = 0;
        int length = 0;

        for (int i = 0; i < L; i++) {
            q.add(Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < L; i++) {
            if (length != ML) {
                length++;
            } else {
                if (shot[i - ML]) damage -= MK;
            }

            Integer cur = q.poll();

            if (cur <= damage + MK) {
                damage += MK;
                shot[i] = true;
            } else C--;
        }

        if (C >= 0) System.out.println("YES");
        else System.out.println("NO");
    }
}