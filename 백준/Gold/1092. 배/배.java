import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.max;

public class Main {
    static class crane implements Comparable<crane> {
        int weight;
        int count;

        public crane(int weight) {
            this.weight = weight;
            this.count = 0;
        }

        @Override
        public int compareTo(crane o) {
            if (this.weight > o.weight) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    static int N;
    static int M;
    static crane[] cranes;
    static int[] box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        cranes = new crane[N];
        for (int i = 0; i < N; i++) {
            cranes[i] = new crane(Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cranes);
        M = Integer.parseInt(br.readLine());
        box = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(box);
        if (box[M - 1] > cranes[0].weight) {
            System.out.println(-1);
            return;
        }
        System.out.println(MoveBox());
        System.out.println();
    }

    static int MoveBox() {
        int index = M - 1;
        int max = 0;
        while (index >= 0) {
            for (int j = 1; j < N; j++) {
                if (cranes[j].weight >= box[index]) {
                    cranes[j].count++;
                    index--;
                    while (index >= 0 && cranes[j].count < max && cranes[j].weight >= box[index]) {
                        cranes[j].count++;
                        index--;
                    }
                    max = max(cranes[j].count, max);
                }
                if (index < 0) {
                    break;
                }
            }
            if (index >= 0 && cranes[0].weight >= box[index]) {
                cranes[0].count++;
                index--;
                max = max(cranes[0].count, max);
            }
        }
        if (max == 0) {
            return -1;
        }
        return max;
    }
}