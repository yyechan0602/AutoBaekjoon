import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static HashSet<Integer> set;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        set = new HashSet<>();
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            Calculation(st.nextToken(), st);
        }

        System.out.println(sb);
    }

    static void Calculation(String str, StringTokenizer st) {

        if (str.equals("add")) {
            Add(Integer.parseInt(st.nextToken()));
        } else if (str.equals("remove")) {
            Remove(Integer.parseInt(st.nextToken()));
        } else if (str.equals("check")) {
            Check(Integer.parseInt(st.nextToken()));
        } else if (str.equals("toggle")) {
            Toggle(Integer.parseInt(st.nextToken()));
        } else if (str.equals("all")) {
            All();
        } else if (str.equals("empty")) {
            Empty();
        }
    }

    static void Add(int x) {
        set.add(x);
    }

    static void Remove(int x) {
        set.remove(x);
    }

    static void Check(int x) {
        if (set.contains(x)) {
            sb.append(1).append("\n");
        } else {
            sb.append(0).append("\n");
        }
    }

    static void Toggle(int x) {
        if (set.contains(x)) {
            set.remove(x);
        } else {
            set.add(x);
        }
    }

    static void All() {
        for (int i = 1; i <= 20; i++) {
            set.add(i);
        }
    }

    static void Empty() {
        set.clear();
    }
}