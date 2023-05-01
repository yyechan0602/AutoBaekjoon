
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] sets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sets = new int[Integer.parseInt(st.nextToken())];
        st = new StringTokenizer(br.readLine());
        int index = 0;
        while (st.hasMoreTokens()) {
            sets[index++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sets);

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        Section(num);
    }

    public static void Section(int num) {
        int min = 0;
        int max = 0;

        int index = 0;
        while (num > sets[index]) {
            min = sets[index++];
        }
        max = sets[index];
        if (max == num) {
            System.out.println(0);
        }
        else{
            System.out.println(Counts(num, min) * Counts(max, num) - 1);
        }
    }

    public static int Counts(int max, int min){
        return max - min;
    }
}
