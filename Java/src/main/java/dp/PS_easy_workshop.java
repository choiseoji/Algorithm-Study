package dp;

import java.util.*;

public class PS_easy_workshop {

    public static void main(String[] args) {

        final int INF = Integer.MAX_VALUE;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[][] grid = new int[n][n];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[][][] mem = new int[n][n][k+1];
        List<int[]> cells = new ArrayList<>();  // 높이, i, j

        // input
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
                cells.add(new int[]{grid[i][j], i, j});

                for (int l = 1; l <= k; l++) mem[i][j][l] = INF;
                mem[i][j][1] = 0;
            }
        }
        cells.sort((a, b) -> a[0] - b[0]);

        // solve
        for(int idx = 0; idx < cells.size(); idx++) {

            int h = cells.get(idx)[0];
            int y = cells.get(idx)[1];
            int x = cells.get(idx)[2];

            // 인접한 칸 확인
            for(int dir = 0; dir < 4; dir++) {

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || h >= grid[ny][nx]) {
                    continue;
                }

                int dist = grid[ny][nx] - grid[y][x];

                // 인접한 칸 내용 갱신
                for(int length = 1; length <= k; length++) {

                    int next = Math.min(length + 1, k); // k 이상은 k에 모이도록
                    int cand = Math.max(dist, mem[y][x][length]);
                    mem[ny][nx][next] = Math.min(cand, mem[ny][nx][next]);
                }
            }
        }

        int ans = INF;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.min(ans, mem[i][j][k]);
            }
        }
        if (ans == INF) ans = -1;

        System.out.println(ans);
    }
}
