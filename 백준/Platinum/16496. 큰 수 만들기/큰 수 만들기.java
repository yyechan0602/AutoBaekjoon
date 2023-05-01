import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class num implements Comparable<num> {
        String number;

        public num(String num) {
            this.number = num;
        }

        @Override
        public int compareTo(num o) {
            BigInteger bi1 = new BigInteger(this.number + o.number);
            BigInteger bi2 = new BigInteger(o.number + this.number);
            if (bi1.compareTo(bi2) == -1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        num[] nums = new num[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = new num(st.nextToken());
        }

        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(nums[i].number);
        }

        if (nums[0].number.equals("0")) {
            System.out.println(0);
        } else {
            System.out.println(sb);
        }
    }
}