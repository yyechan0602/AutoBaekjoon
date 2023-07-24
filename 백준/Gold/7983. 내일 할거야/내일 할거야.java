import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class homework implements Comparable<homework> {
        int d;
        int t;

        public homework(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(homework o) {
            if (this.t < o.t) return 1;
            else return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<homework> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new homework(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        homework tempt = pq.poll();
        int time = tempt.t - tempt.d;

        while (!pq.isEmpty()) {
            tempt = pq.poll();

            time = Math.min(time, tempt.t);
            time -= tempt.d;
        }

        System.out.println(time);
    }
}