import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[2][n * n];
        long result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());
            int num4 = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++) {
                arr[0][n * i + j] += num1;
                arr[0][n * j + i] += num2;
                arr[1][n * i + j] += num3;
                arr[1][n * j + i] += num4;
            }
        }

        for (int i = 0; i < 2; i++) {
            Arrays.sort(arr[i]);
        }

        for (int i = 0; i < n * n; i++) {
            result += BinarySearch(-1 * arr[0][i]);
        }

        System.out.println(result);
    }

    static int BinarySearch(int value) {
        return UpperBound(value) - LowerBound(value);
    }

    static int LowerBound(int value) {
        int mid;
        int start = 0;
        int end = n * n - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[1][mid] >= value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    static int UpperBound(int value) {
        int mid;
        int start = 0;
        int end = n * n - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[1][mid] > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
}