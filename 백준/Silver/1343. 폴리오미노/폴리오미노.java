import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] str = sc.next().toCharArray();
        int length = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'X') {
                length++;
                if (length == 4) {
                    for (int j = 0; j < 4; j++) {
                        str[i - j] = 'A';
                        length = 0;
                    }
                }
            } else if (str[i] == '.') {
                if (length == 0) {
                    continue;
                } else if (length == 3 || length == 1) {
                    System.out.println(-1);
                    return;
                } else {
                    str[i - 1] = 'B';
                    str[i - 2] = 'B';
                    length = 0;
                }
            }
        }

        if (length == 3 || length == 1) {
            System.out.println(-1);
            return;
        } else if (length == 2) {
            str[str.length - 1] = 'B';
            str[str.length - 2] = 'B';
        }

        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i]);
        }
    }
}