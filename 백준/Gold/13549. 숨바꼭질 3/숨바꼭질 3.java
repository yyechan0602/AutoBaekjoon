import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] pos;
    static final int goal = 100005;
    static final int notVisited = 100004;
    static final int last = 100001;
    static final int start = 0;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pos = new int[100001];
        Arrays.fill(pos, notVisited);
        q = new LinkedList<>();
        int startPoint = Integer.parseInt(st.nextToken());
        pos[startPoint] = start;
        q.add(startPoint);
        pos[Integer.parseInt(st.nextToken())] = goal;
        if (pos[startPoint] == goal) {
            System.out.println(0);
            return;
        }
        System.out.println(FindSister());
        //ShowAll();
    }

    static int FindSister() {
        int min = last;
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            int curInt = cur.intValue();
            int curTime = pos[curInt];
            // *2이동
            if (2 * curInt <= last) {
                if (pos[2 * curInt] == goal) {
                    min = Math.min(curTime, min);
                } else if (pos[2 * curInt] > curTime) {
                    q.add(2 * curInt);
                    pos[2 * curInt] = curTime;
                }
            }
            // -1칸
            if (curInt - 1 >= 0) {
                if (pos[curInt - 1] == goal) {
                    min = Math.min(curTime + 1, min);
                } else if (pos[curInt - 1] > curTime + 1) {
                    q.add(curInt - 1);
                    pos[curInt - 1] = curTime + 1;
                }
            }
            // +1칸
            if (curInt + 1 < last) {
                if (pos[curInt + 1] == goal) {
                    min = Math.min(curTime + 1, min);
                } else if (pos[curInt + 1] > curTime + 1) {
                    q.add(curInt + 1);
                    pos[curInt + 1] = curTime + 1;
                }
            }
        }
        return min;
    }

    static void ShowAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < last; i++) {
            if (pos[i] == notVisited) {
                sb.append(". ");
            } else if (pos[i] == goal) {
                sb.append("* ");
            } else {
                sb.append(pos[i] + " ");
            }
        }
        System.out.println(sb);
    }
}