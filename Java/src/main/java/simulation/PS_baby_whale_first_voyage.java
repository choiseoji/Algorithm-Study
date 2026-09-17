package simulation;

import java.util.*;
import java.io.*;

public class PS_baby_whale_first_voyage {

    static int N, r, c, d, K = 0;
    static int[][] board;
    static boolean[][] visited;

    static int[] dx = {-100, 0, -1, 1, 0, 0, 1, -1, 0, -1, 0, 0, 1, 1, 0, 0, -1};
    static int[] dy = {-100, -1, 0, 0, 1, 1, 0, 0, -1, 0, 1, -1, 0, 0, -1, 1, 0};
    static int[] dir = {-100, 1, 3, 4, 2, 2, 4, 3, 1, 3, 2, 1, 4, 4, 1, 2, 3};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input(br);

        // solve
        bw.write((r + 1) + " " + (c + 1) + "\n");
        visited[r][c] = true;
        K--;

        while (K > 0) {

            // step 1
            boolean visit = false;
            for (int i = 1; i <= 4; i++) {
                visit = canVisitLevelOne(r, c, d, i);
                if (visit == true) {
                    break ;
                }
            }

            // step 2
            if (visit == false) {
                canVisitLevelTwo(r, c, d);
            }

            K--;
            bw.write((r + 1) + " " + (c + 1) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void input(BufferedReader br) throws Exception {

        String firstLine = br.readLine();
        String[] nums = firstLine.split(" ");

        N = Integer.parseInt(nums[0]);
        r = Integer.parseInt(nums[1]) - 1;
        c = Integer.parseInt(nums[2]) - 1;
        d = Integer.parseInt(nums[3]);
        K = 0;

        board = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for(int i = 0; i < N; i++) {

            String line = br.readLine();
            String[] n = line.split(" ");
            for(int j = 0; j < N; j++) {

                board[i][j] = Integer.parseInt(n[j]);
                if (board[i][j] == 0) {
                    K++;
                }
            }
        }
    }

    // 현재 y, 현재 x, 현재 방향, 단계
    public static boolean canVisitLevelOne(int curR, int curC, int curD, int level) {

        int index = 4 * (curD - 1) + level;
        int ny = curR + dy[index];
        int nx = curC + dx[index];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            return (false);
        }

        if (visited[ny][nx] == true || board[ny][nx] == 1) {
            return (false);
        }

        // 실제 이동
        r = ny;
        c = nx;
        d = dir[index];
        visited[r][c] = true;
        return (true);
    }

    public static void canVisitLevelTwo(int curR, int curC, int curD) {

        // 모든 칸의 거리 구하기
        int[][] cnt = new int[N + 1][N + 1];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                cnt[i][j] = -1;
            }
        }
        int[][] dirMem = new int[N + 1][N + 1];

        int[] dirx = {-1, 0, 1, 0};
        int[] diry = {0, 1, 0, -1};
        int[] dirIndex = {3, 2, 4, 1};

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{curC, curR, 0});
        cnt[curR][curC] = -2; // 시작점 표시

        while (!q.isEmpty()) {

            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];

            for(int i = 0; i < 4; i++) {

                int nx = x + dirx[i];
                int ny = y + diry[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N
                        && cnt[ny][nx] == -1 && board[ny][nx] == 0) {

                    cnt[ny][nx] = c + 1;
                    dirMem[ny][nx] = dirIndex[i];
                    q.offer(new int[]{nx, ny, c + 1});
                }
            }
        }

        // 가장 가까운 위치 고르기
        int destR = -1;
        int destC = -1;
        int minCnt = 5000;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                if (visited[i][j] == false
                        && cnt[i][j] > 0 && cnt[i][j] < minCnt) {

                    destR = i;
                    destC = j;
                    minCnt = cnt[i][j];
                }
            }
        }

        r = destR;
        c = destC;
        d = dirMem[r][c];
        visited[r][c] = true;
    }
}
