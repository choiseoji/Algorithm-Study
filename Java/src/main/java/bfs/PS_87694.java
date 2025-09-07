package bfs;

import java.util.*;

public class PS_87694 {

    static class Point {
        int x, y, dist;

        Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        int[][] tmp = new int[110][110];
        int[][] board = new int[110][110];
        boolean[][] visited = new boolean[110][110];

        // 1. 맵 2배 확장
        for(int i = 0; i < rectangle.length; i++) {

            int fx = rectangle[i][0] * 2;
            int fy = rectangle[i][1] * 2;
            int ex = rectangle[i][2] * 2;
            int ey = rectangle[i][3] * 2;

            for(int x = fx; x <= ex; x++) {
                for(int y = fy; y <= ey; y++) {
                    tmp[y][x] = 1;
                }
            }
        }

        // 2. 벽 판단
        int[] dx2 = {1, 0, -1, 0, 1, 1, -1, -1};
        int[] dy2 = {0, 1, 0, -1, 1, -1, 1, -1};

        for(int i = 1; i <= 100; i++) {    // y
            for(int j = 1; j <= 100; j++) {  // x

                if (tmp[i][j] == 1) {

                    for(int k = 0; k < 8; k++) {

                        int nx = j + dx2[k];
                        int ny = i + dy2[k];

                        if (tmp[ny][nx] == 0) {
                            board[i][j] = 1;
                            break ;
                        }
                    }
                }
            }
        }

        // 3. 아이템 찾기

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(characterX * 2, characterY * 2, 0));
        visited[characterY * 2][characterX * 2] = true;

        while (!q.isEmpty()) {

            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            int dist = p.dist;

            if (x == (itemX * 2) && y == (itemY * 2)) {

                answer = (dist / 2);
                break ;
            }

            for(int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 1 && nx <= 100 && ny >= 1 && ny <= 100 && !visited[ny][nx] && board[ny][nx] == 1) {
                    q.add(new Point(nx, ny, dist + 1));
                    visited[ny][nx] = true;
                }
            }
        }

        return answer;
    }
}
