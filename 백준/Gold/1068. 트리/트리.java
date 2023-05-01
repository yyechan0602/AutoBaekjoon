import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] tree;
    static final int ERASE = -20;
    static int root;
    static boolean[] isVisited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            if (tree[i] == -1) {
                root = i;
            }
        }
        RemoveNode(Integer.parseInt(br.readLine()));

        isVisited = new boolean[N];
        count = 0;
        if (tree[root] != ERASE) {
            FindLeafTree(root);
        }

        System.out.println(count);
    }

    static void RemoveNode(int index) {
        tree[index] = ERASE;
        for (int i = 0; i < N; i++) {
            if (tree[i] == index) {
                RemoveNode(i);
            }
        }
    }

    static void FindLeafTree(int index) {
        isVisited[index] = true;
        boolean isLeaf = true;

        for (int i = 0; i < N; i++) {
            if (isVisited[i] || tree[i] == ERASE) {
                continue;
            }

            if (tree[i] == index) {
                FindLeafTree(i);
                isLeaf = false;
            }
        }
        if (isLeaf) {
            count++;
        }

        isVisited[index] = false;
    }
}