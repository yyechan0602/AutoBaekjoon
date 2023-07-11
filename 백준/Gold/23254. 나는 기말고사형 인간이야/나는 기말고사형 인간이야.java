import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

        int result = 0;
        int[] score = new int[M];
        List<LinkedList<Integer>> scores = new ArrayList<>();

        for (int i = 0; i < 101; i++) {
            scores.add(new LinkedList<>());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int tempt = Integer.parseInt(st.nextToken());
            int efficiency = tempt + score[i] <= 100 ? tempt : 100 - score[i];
            scores.get(efficiency).add(score[i]);
        }

        int pos = 100;

        for (int i = 0; i < 24 * N; i++) {
            while (scores.get(pos).isEmpty()) {
                pos--;
            }

            int tempt = scores.get(pos).pop() + pos;
            int curPos = tempt + pos <= 100 ? pos : 100 - tempt;

            scores.get(curPos).add(tempt);
        }

        for (int i = 0; i < 101; i++) {
            while(!scores.get(i).isEmpty()){
                result += scores.get(i).pop();
            }
        }

        System.out.println(result);
    }
}