import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int firstN = N / 2;
        int secondN = N / 2 + N % 2;

        int[] firstNums = new int[firstN];
        int[] secondNums = new int[secondN];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < firstN; i++) {
            firstNums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < secondN; i++) {
            secondNums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(firstNums);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < firstN; i++) {
            int QueueSize = q.size();

            while (QueueSize > 0) {
                Integer num = q.poll();
                q.add(num + firstNums[i]);
                q.add(num);
                QueueSize--;
            }

            q.add(firstNums[i]);
        }

        q.add(0);

        int size = q.size();
        nums = new int[size];

        for (int i = 0; i < size; i++) {
            nums[i] = q.poll();
        }

        Arrays.sort(nums);
        q.clear();

        for (int i = 0; i < secondN; i++) {
            int QueueSize = q.size();

            while (QueueSize > 0) {
                Integer num = q.poll();
                q.add(num + secondNums[i]);
                q.add(num);
                QueueSize--;
            }

            q.add(secondNums[i]);
        }

        q.add(0);

        size = q.size();
        int[] nums2 = new int[size];

        for (int i = 0; i < size; i++) {
            nums2[i] = q.poll();
        }

        long count = 0;

        for (int i = 0; i < nums2.length; i++) {
            count += upperBound(S - nums2[i]) - lowerBound(S - nums2[i]);
        }

        int num = 0;
        if(S == 0) num = 1;

        System.out.println(count - num);
    }

    static int lowerBound(int num) {
        int low = 0;
        int high = nums.length;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (nums[mid] >= num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    static int upperBound(int num) {
        int low = 0;
        int high = nums.length;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;

            if (nums[mid] > num) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}