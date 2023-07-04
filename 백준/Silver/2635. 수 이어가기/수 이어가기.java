

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());

        int result = 0;
        int max = 0;
        for (int i = 0; i < num + 1; i++) {
            if ( result < NumContinue(num, i)){
                result  = NumContinue(num, i);
                max = i;
            }
        }
        System.out.println(result);
        Show(num, max);
    }

    public static int NumContinue(int firstNum, int secondNum) {
        int count = 1;
        int thirdNum;
        do {
            thirdNum = firstNum - secondNum;
            firstNum = secondNum;
            secondNum = thirdNum;
            count += 1;
        } while (thirdNum >= 0);
        return count;
    }

    public static void Show(int firstNum, int secondNum) {
        System.out.print(firstNum);
        int thirdNum;
        do {
            thirdNum = firstNum - secondNum;
            firstNum = secondNum;
            secondNum = thirdNum;
            System.out.print(" " + firstNum);
        } while (thirdNum >= 0);

    }

}
