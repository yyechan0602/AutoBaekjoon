import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        boolean isL = false;
        int result = N+1;
        int count = 0;

        for (int i = 0; i < N; i++) {
            if(str.charAt(i)=='L')
                count++;
        }


        System.out.println(Math.min(result-count/2, N));
    }
}