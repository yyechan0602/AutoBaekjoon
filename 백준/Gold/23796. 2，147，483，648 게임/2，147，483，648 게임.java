import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int arraySize;
    static long[][][] board;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arraySize = 8;

        board = new long[arraySize][arraySize][2];

        for (int i = 0; i < arraySize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < arraySize; j++) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        MakeBoard(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                sb.append(board[i][j][1]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void MakeBoard(String key) {
        if (key.equals("U")) {
            Up(0);
        }
        if (key.equals("D")) {
            Down(0);
        }
        if (key.equals("L")) {
            Left(0);
        }
        if (key.equals("R")) {
            Right(0);
        }
    }

    static void Reset(int depth) {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                board[i][j][depth] = 0;
            }
        }
    }

    static void Left(int depth) {
        for (int i = 0; i < arraySize; i++) {
            long num = 0;
            int index = 0;
            for (int j = 0; j < arraySize; j++) {
                if (board[i][j][depth] != 0) {
                    if (num == 0) {
                        num = board[i][j][depth];
                    } else {
                        if (num == board[i][j][depth]) {
                            board[i][index++][depth + 1] = 2 * num;
                            num = 0;
                        } else {
                            board[i][index++][depth + 1] = num;
                            num = board[i][j][depth];
                        }
                    }
                }
                if (num != 0) {
                    board[i][index][depth + 1] = num;
                }
            }
        }
    }

    static void Right(int depth) {
        for (int i = 0; i < arraySize; i++) {
            long num = 0;
            int index = arraySize - 1;
            for (int j = arraySize - 1; j >= 0; j--) {
                if (board[i][j][depth] != 0) {
                    if (num == 0) {
                        num = board[i][j][depth];
                    } else {
                        if (num == board[i][j][depth]) {
                            board[i][index--][depth + 1] = 2 * num;
                            num = 0;
                        } else {
                            board[i][index--][depth + 1] = num;
                            num = board[i][j][depth];
                        }
                    }
                }
                if (num != 0) {
                    board[i][index][depth + 1] = num;
                }
            }
        }
    }

    static void Up(int depth) {
        for (int i = 0; i < arraySize; i++) {
            long num = 0;
            int index = 0;
            for (int j = 0; j < arraySize; j++) {
                if (board[j][i][depth] != 0) {
                    if (num == 0) {
                        num = board[j][i][depth];
                    } else {
                        if (num == board[j][i][depth]) {
                            board[index++][i][depth + 1] = 2 * num;
                            num = 0;
                        } else {
                            board[index++][i][depth + 1] = num;
                            num = board[j][i][depth];
                        }
                    }
                }
                if (num != 0) {
                    board[index][i][depth + 1] = num;
                }
            }
        }
    }

    static void Down(int depth) {
        for (int i = 0; i < arraySize; i++) {
            long num = 0;
            int index = arraySize - 1;
            for (int j = arraySize - 1; j >= 0; j--) {
                if (board[j][i][depth] != 0) {
                    if (num == 0) {
                        num = board[j][i][depth];
                    } else {
                        if (num == board[j][i][depth]) {
                            board[index--][i][depth + 1] = 2 * num;
                            num = 0;
                        } else {
                            board[index--][i][depth + 1] = num;
                            num = board[j][i][depth];
                        }
                    }
                }
                if (num != 0) {
                    board[index][i][depth + 1] = num;
                }
            }
        }
    }
}