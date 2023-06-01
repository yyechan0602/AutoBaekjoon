import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][][] board;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        board = new int[N][N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0);

        System.out.println(maxBlock);
    }

    static void DFS(int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxBlock = Math.max(maxBlock, board[i][j][5]);
                }
            }
            return;
        }

        Left(depth);
        DFS(depth + 1);
        Reset(depth + 1);

        Right(depth);
        DFS(depth + 1);
        Reset(depth + 1);

        Up(depth);
        DFS(depth + 1);
        Reset(depth + 1);

        Down(depth);
        DFS(depth + 1);
        Reset(depth + 1);
    }

    static void Reset(int depth) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j][depth] = 0;
            }
        }
    }

    static void Left(int depth) {
        for (int i = 0; i < N; i++) {
            int num = 0;
            int index = 0;
            for (int j = 0; j < N; j++) {
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
        for (int i = 0; i < N; i++) {
            int num = 0;
            int index = N - 1;
            for (int j = N - 1; j >= 0; j--) {
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
        for (int i = 0; i < N; i++) {
            int num = 0;
            int index = 0;
            for (int j = 0; j < N; j++) {
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
        for (int i = 0; i < N; i++) {
            int num = 0;
            int index = N - 1;
            for (int j = N - 1; j >= 0; j--) {
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