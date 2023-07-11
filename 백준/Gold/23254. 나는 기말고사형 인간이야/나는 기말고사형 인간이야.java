import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class test implements Comparable<test> {
        int score;
        int efficiency;

        public test(int score, int efficiency) {
            this.score = score;
            this.efficiency = efficiency;
            if (this.score + this.efficiency >= 100) {
                this.efficiency = 100 - this.score;
            }
        }

        public void study() {
            score += efficiency;

            if (this.score + this.efficiency >= 100) {
                this.efficiency = 100 - this.score;
            }
        }


        @Override
        public int compareTo(test o) {
            if (this.efficiency > o.efficiency) return -1;
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<test> pq = new PriorityQueue<>();
        int result = 0;
        int[] score = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            result += score[i];
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            pq.add(new test(score[i], Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < 24 * N; i++) {
            test subject = pq.poll();
            result += subject.efficiency;
            subject.study();
            pq.add(subject);
        }

        System.out.println(result);
    }
}