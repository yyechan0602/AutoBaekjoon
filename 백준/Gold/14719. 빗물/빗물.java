import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Main {
    public static boolean[][] block;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        block = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(block[i], false);
        }

        st = new StringTokenizer(br.readLine());
        int result = 0;

        FillBlock(height, width, st);
        for (int i = 0; i < height; i++) {
            result += IsFlow(i, width);
        }
        System.out.println(result);
    }

    public static void FillBlock(int height, int width, StringTokenizer st) {
        int flag = 0;
        for (int w = 0; w < width; w++) {
            int num = Integer.parseInt(st.nextToken());
            for (int h = height - 1; h > height - num - 1; h--) {
                block[h][w] = true;
            }
        }
    }

    public static int IsFlow(int height, int width) {
        boolean isblock = false;
        int count = 0;
        int finalCount=0;
        for (int i = 0; i < width; i++) {
            if (block[height][i]) {
                isblock = true;
                finalCount += count;
                count = 0;
            } else{
                if (isblock) {
                    count += 1;
                }
            }
        }
        return finalCount;
    }
}
