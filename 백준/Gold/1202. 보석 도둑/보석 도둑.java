import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static private class jewel implements Comparable<jewel> {
        int weight;
        int value;

        public jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(jewel o) {
            if (this.weight == o.weight) {
                return o.value - this.value;
            }
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        jewel[] jewels = new jewel[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(jewels);

        int[] bags = new int[K];

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long result = 0;
        int index = 0;

        for (int i = 0; i < K; i++) {

            while(index<N&&jewels[index].weight<=bags[i]){
                pq.add(jewels[index++].value);
            }

            if(!pq.isEmpty()){
                result += pq.poll();
            }
        }

        System.out.println(result);
    }
}