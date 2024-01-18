import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] cards = new long[N];

        for (int i = 0; i < N; i++) {
            cards[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(cards);

        long maxCount = 0;
        long curCount = 0;
        long maxNum = 0;
        long curNum = 0;

        for (int i = 0; i < cards.length; i++) {
            if(curNum!=cards[i]) curCount=0;

            curNum = cards[i];
            curCount++;

            if(maxCount<curCount){
                maxCount = curCount;
                maxNum = curNum;
            }
        }

        System.out.println(maxNum);
    }
}