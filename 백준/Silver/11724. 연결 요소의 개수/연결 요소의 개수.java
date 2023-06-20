import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isvisited;
    static LinkedList<Integer>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int result = 0;

        arr = new LinkedList[N+1];

        isvisited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            arr[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if(isvisited[i]) continue;
            fun(i);
            result++;
        }

        System.out.println(result);
    }

    static void fun(int i){
        while(!arr[i].isEmpty()){
            int num = arr[i].poll();
            isvisited[num] = true;
            fun(num);
        }
    }
}