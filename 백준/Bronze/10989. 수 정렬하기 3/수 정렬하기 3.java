import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb =new StringBuilder();

        int[] nums = new int[10001];

        for (int i = 0; i < N; i++) {
            nums[Integer.parseInt(br.readLine())]++;
        }

        for (int i = 0; i < nums.length; i++) {
            while(nums[i]>0){
                nums[i]--;
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
}