import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static private class room {
        int money;
        int maxMoney;

        public room(int money) {
            this.money = money;
            this.maxMoney = -1;
        }

        public int pass(int money) {
            if (this.money < 0) {
                return money + this.money;
            } else {
                return Math.max(money, this.money);
            }
        }
    }

    static private BufferedReader br;
    static private StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        while (n != 0) {
            findMaze(n);
            n = Integer.parseInt(br.readLine());
        }
    }

    static void findMaze(int n) throws IOException {
        List<ArrayList<Integer>> maze = new ArrayList<>();
        // 0번지는 방 정보, 1번지는 최대 돈
        room[] rooms = new room[n];

        // 미로생성
        for (int i = 0; i < n; i++) {
            maze.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());

            int money;
            if (st.nextToken().equals("T")) {
                money = Integer.parseInt(st.nextToken()) * -1;
            } else {
                money = Integer.parseInt(st.nextToken());
            }

            rooms[i] = new room(money);

            while (true) {
                int nextPos = Integer.parseInt(st.nextToken());
                if (nextPos == 0) break;
                maze.get(i).add(nextPos - 1);
            }
        }

        // 미로 해결
        LinkedList<Integer> q = new LinkedList<>();
        q.add(0);
        rooms[0].maxMoney = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == n - 1) {
                System.out.println("Yes");
                return;
            }

            //System.out.println(cur);

            for (int i = 0; i < maze.get(cur).size(); i++) {
                //System.out.println(rooms[maze.get(cur).get(i)].pass(rooms[cur].maxMoney) + " " + rooms[maze.get(cur).get(i)].maxMoney + " " + maze.get(cur).get(i));

                if (rooms[maze.get(cur).get(i)].pass(rooms[cur].maxMoney) > rooms[maze.get(cur).get(i)].maxMoney) {
                    rooms[maze.get(cur).get(i)].maxMoney = rooms[maze.get(cur).get(i)].pass(rooms[cur].maxMoney);
                    q.add(maze.get(cur).get(i));
                }
            }
        }
        System.out.println("No");
    }
}