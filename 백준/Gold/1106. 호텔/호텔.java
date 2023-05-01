import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class city {
        int money;
        int customer;

        public city(int money, int customer) {
            this.money = money;
            this.customer = customer;
        }
    }

    static city[] cities;

    static int MAXMONEY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        cities = new city[N];
        MAXMONEY = C * 100 + 1;
        int[] dp = new int[MAXMONEY]; // 모든 비용의 경우의 수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cities[i] = new city(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < MAXMONEY; i++) {
            for (int j = 0; j < cities.length; j++) {
                if (cities[j].money + i >= MAXMONEY) {
                    continue;
                }
                dp[cities[j].money + i] = Math.max(cities[j].customer + dp[i], dp[cities[j].money + i]);
            }
        }

        for (int i = 0; i < MAXMONEY; i++) {
            //System.out.println(i + ": " + dp[i]);
            if (dp[i] >= C) {
                System.out.println(i);
                break;
            }
        }
    }
}