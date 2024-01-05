import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static long[] smallArr;
    static String[] bigArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        smallArr = new long[101];
        bigArr = new String[101];

        makeBig();
        makeSmall();

        for (int t = 0; t < T; t++) {
            int num = Integer.parseInt(br.readLine());

            System.out.println(smallArr[num] + " " + bigArr[num]);
        }

    }

    static void makeSmall() {

        Arrays.fill(smallArr, Long.MAX_VALUE);

        smallArr[2] = 1;
        smallArr[3] = 7;
        smallArr[4] = 4;
        smallArr[5] = 2;
        smallArr[6] = 6;
        smallArr[7] = 8;
        smallArr[8] = 10;
        smallArr[9] = 18;
        smallArr[10] = 22;
        smallArr[11] = 20;
        smallArr[12] = 28;
        smallArr[13] = 68;
        smallArr[14] = 88;

        for (int i = 15; i <= 100; i++) {
            for (int j = 2; j <= i - 2; j++) {
                smallArr[i] = Math.min(smallArr[i], Math.min(Long.parseLong(Long.toString(smallArr[i - j]) + smallArr[j]), Long.parseLong(Long.toString(smallArr[j]) + smallArr[i - j])));
            }
            if(i == 17){
                smallArr[17] = 200;
            }
        }
    }

    static void makeBig() {
        bigArr[2] = "1";
        bigArr[3] = "7";
        StringBuilder sb = new StringBuilder();

        for (int i = 4; i <= 100; i++) {
            sb.setLength(0);
            if (i % 2 == 0) {

                sb.append("1".repeat(i / 2));
                bigArr[i] = sb.toString();
            } else {
                sb.append("7");
                sb.append("1".repeat(i / 2 - 1));
                bigArr[i] = sb.toString();
            }
        }
    }
}