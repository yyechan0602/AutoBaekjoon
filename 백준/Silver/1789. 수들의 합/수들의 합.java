import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long S = in.nextLong();
        long N = 0;

        while (S >= 0) {
            N++;
            S -= N;
        }

        System.out.println(N - 1);
    }
}