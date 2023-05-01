import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int a;
    static int b;
    static int d;
    static int N;
    static Queue<Integer> baby;
    static Queue<Integer> adult;
    static Queue<Integer> old;
    static int adultNum;
    static int babyNum;
    static int oldNum;
    static int lastOld;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        oldNum = 0;
        adultNum = 0;
        babyNum = 1;
        lastOld = 0;
        baby = new LinkedList<>();
        adult = new LinkedList<>();
        old = new LinkedList<>();

        baby.add(new Integer(1));
        System.out.println(TimePass());
    }

    static long TimePass() {
        //ShowAll(0);
        for (int time = 1; time < N + 1; time++) {
            BabyLife(time);
            AdultLife(time);
            OldLife(time);
            //ShowAll(time);
        }
        return (adultNum + babyNum + oldNum) % 1000;
    }

    static void BabyLife(int time) {
        if (time >= a) {
            Integer newAdult = baby.poll().intValue() % 1000;
            adult.add(newAdult);
            babyNum -= newAdult;
            adultNum += newAdult.longValue();
        }

    }

    static void AdultLife(int time) {
        if (time >= b) {
            Integer newOld = adult.poll().intValue() % 1000;
            old.add(newOld);
            adultNum -= newOld.intValue();
            oldNum += newOld.intValue();
        }
        baby.add(adultNum);
        babyNum += adultNum;
        babyNum = (babyNum + 1000) % 1000;
        adultNum = (adultNum + 1000) % 1000;
    }

    static void OldLife(int time) {
        if (time >= d) {
            lastOld = old.poll().intValue() % 1000;
            oldNum -= lastOld;
        }
        oldNum = (oldNum + 1000) % 1000;
    }

    static void ShowAll(int time) {
        System.out.println(time + " -> " + oldNum + " " + adultNum + " " + babyNum);
    }
}