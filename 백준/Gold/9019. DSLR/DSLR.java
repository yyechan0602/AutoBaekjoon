import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class num {
        int num;
        String str;

        public num(int num, String str) {
            this.num = num;
            this.str = str;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println(BFS(st));
        }
    }

    static String BFS(StringTokenizer st) {
        boolean[] visited = new boolean[10000];

        int before = Integer.parseInt(st.nextToken());
        int after = Integer.parseInt(st.nextToken());

        Queue<num> q = new LinkedList<>();
        q.add(new num(before, ""));

        while (true) {
            num cur = q.poll();
            if (cur.num == after) {
                return cur.str;
            }

            int num = D(cur.num);
            if (!visited[num]) {
                visited[num] = true;
                if (num != after) {
                    q.add(new num(num, cur.str + "D"));
                } else {
                    return cur.str + "D";
                }
            }

            num = S(cur.num);
            if (!visited[num]) {
                visited[num] = true;
                if (num != after) {
                    q.add(new num(num, cur.str + "S"));
                } else {
                    return cur.str + "S";
                }
            }
            num = L(cur.num);
            if (!visited[num]) {
                visited[num] = true;
                if (num != after) {
                    q.add(new num(num, cur.str + "L"));
                } else {
                    return cur.str + "L";
                }
            }

            num = R(cur.num);
            if (!visited[num]) {
                visited[num] = true;
                if (num != after) {
                    q.add(new num(num, cur.str + "R"));
                } else {
                    return cur.str + "R";
                }
            }
        }
    }

    static int D(int after) {

        return (after * 2) % 10000;
    }

    static int S(int after) {
        after--;

        if (after == -1) {
            return 9999;
        }
        return after;
    }

    static int L(int after) {
        int num = after / 1000;

        after = after * 10 % 10000 + num;
        return after;
    }

    static int R(int after) {
        int num = after % 10;

        after = after / 10  + num * 1000;
        return after;
    }
}