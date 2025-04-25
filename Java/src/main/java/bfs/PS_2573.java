package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

import static java.lang.Math.max;

public class PS_2573 {

    static int year;
    static int N, M;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] board;
    static int[][] tmpBoard;
    static boolean[][] visited;

    private static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        tmpBoard = new int[N][M];
        for(int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                 board[i][j] = Integer.parseInt(line.nextToken());
                 tmpBoard[i][j] = board[i][j];
            }
        }
    }

    private static boolean check() {

        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {

                if (tmpBoard[i][j] != 0 && !visited[i][j]) {

                    if (cnt > 0)
                        return (false);

                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int y = cur[0];
                        int x = cur[1];

                        for(int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && tmpBoard[ny][nx] != 0) {

                                visited[ny][nx] = true;
                                q.add(new int[]{ny, nx});
                            }
                        }
                    }
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            year = 0;
            return (false);
        }

        // init
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                board[i][j] = tmpBoard[i][j];
            }
        }
        return (true);
    }

    private static void solve() {

        while (check()) {
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < M; x++) {
                    int count = 0;

                    for(int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx >= 0 && nx < M && ny >= 0 && ny < N && board[ny][nx] == 0) {
                            count++;
                        }
                    }
                    tmpBoard[y][x] = max(board[y][x] - count, 0);
                }
            }
            year++;
        }
        System.out.println(year);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
