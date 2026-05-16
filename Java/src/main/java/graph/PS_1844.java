package graph;

import java.util.*;

public class PS_1844 {
    class Info {

        int x;
        int y;
        int dist;

        Info (int x, int y, int dist) {

            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(int[][] maps) {
        int answer = -1;

        int n = maps.length;  // 세로
        int m = maps[0].length;  // 가로
        boolean[][] visited = new boolean[n + 1][m + 1];

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(0, 0, 1));
        visited[0][0] = true;

        while (!q.isEmpty()) {

            Info cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;

            if (x == m - 1 && y == n - 1) {
                answer = dist;
                break ;
            }

            for(int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {

                    if (!visited[ny][nx] && maps[ny][nx] == 1) {
                        q.add(new Info(nx, ny, dist + 1));
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return answer;
    }
}
