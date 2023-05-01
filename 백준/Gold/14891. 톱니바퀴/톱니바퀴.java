import java.io.*;
import java.util.*;

public class Main {
    public static boolean[][] gear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;
        gear = new boolean[4][8];
        for (int i = 0; i < 4; i++) {
            str = br.readLine();
            for (int j = 0; j < 8; j++) {
                if (str.charAt(j) == '0') {
                    gear[i][j] = false;
                } else {
                    gear[i][j] = true;
                }
            }
        }
        int K = Integer.parseInt(br.readLine());
        //ShowAll();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            Rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Print12();
    }

    static void Rotate(int num, int dir) {
        if (num == 1) {
            if (CompareGear(0, 1)) {
                if (CompareGear(1, 2)) {
                    if (CompareGear(2, 3)) {
                        GearCounter(3, -dir);
                    }
                    GearCounter(2, dir);
                }
                GearCounter(1, -dir);
            }
            GearCounter(0, dir);
        } else if (num == 2) {
            if (CompareGear(0, 1)) {
                GearCounter(0, -dir);
            }
            if (CompareGear(1, 2)) {
                if (CompareGear(2, 3)) {
                    GearCounter(3, dir);
                }
                GearCounter(2, -dir);
            }
            GearCounter(1, dir);
        } else if (num == 3) {
            if (CompareGear(1, 2)) {
                if (CompareGear(0, 1)) {
                    GearCounter(0, dir);
                }
                GearCounter(1, -dir);
            }
            if (CompareGear(2, 3)) {
                GearCounter(3, -dir);
            }
            GearCounter(2, dir);
        } else {
            if (CompareGear(2, 3)) {
                if (CompareGear(1, 2)) {
                    if (CompareGear(0, 1)) {
                        GearCounter(0, -dir);
                    }
                    GearCounter(1, dir);
                }
                GearCounter(2, -dir);
            }
            GearCounter(3, dir);
        }
        //ShowAll();
    }

    static void Print12() {
        int result = 0;
        if (gear[0][0]) {
            result += 1;
        }
        if (gear[1][0]) {
            result += 2;
        }
        if (gear[2][0]) {
            result += 4;
        }
        if (gear[3][0]) {
            result += 8;
        }
        System.out.println(result);
    }

    static void ShowAll() {
        System.out.println("    v       v");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if (!gear[i][j]) {
                    System.out.print("0 ");
                } else {
                    System.out.print("1 ");
                }
            }
            System.out.println();
        }
        System.out.println("===========================");
    }

    static boolean CompareGear(int a, int b) {
        if (gear[a][2] != gear[b][6]) {
            return true;
        } else {
            return false;
        }
    }

    static void GearCounter(int num, int dir) {
        if (dir == 1) {
            boolean save = gear[num][7];
            for (int i = 7; i > 0; i--) {
                gear[num][i] = gear[num][i - 1];
            }
            gear[num][0] = save;
        } else {
            boolean save = gear[num][0];
            for (int i = 0; i < 7; i++) {
                gear[num][i] = gear[num][i + 1];
            }
            gear[num][7] = save;
        }
    }
}