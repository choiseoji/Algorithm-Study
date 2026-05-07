package bfs;

import java.util.*;

public class PS_388353 {

    public class Node {
        int x;
        int y;

        Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        Character[][] st;

        int n = storage.length;
        int m = storage[0].length();
        st = new Character[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                st[i][j] = storage[i].charAt(j);
            }
        }

        // solve
        for(int k = 0; k < requests.length; k++) {

            Character c = requests[k].charAt(0);
            int size = requests[k].length();

            if (size == 1) {  // 접근 가능 다 삭제

                outside(st, c, n, m);
            } else {   // 다 삭제

                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {

                        if (st[i][j] == c) {
                            st[i][j] = null;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if (st[i][j] != null)
                    answer++;
            }
        }
        return answer;
    }

    private void outside(Character[][] st, Character c, int n, int m) {

        boolean[][] visited = new boolean[n][m];
        Character[][] mem = new Character[n][m];

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                mem[i][j] = st[i][j];
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if (!(i == 0 || i == n - 1 || j == 0 || j == m - 1))
                    continue;

                // 내 위치가 c
                if (st[i][j] == c) {
                    mem[i][j] = null;
                    continue;
                }

                // 시작점은 null
                if (st[i][j] == null) {

                    Queue<Node> q = new LinkedList<>();
                    Node cur = new Node(i, j);
                    q.add(cur);
                    visited[i][j] = true;

                    while(!q.isEmpty()) {

                        Node node = q.poll();
                        int x = node.x;
                        int y = node.y;

                        for(int d = 0; d < 4; d++) {

                            int nx = x + dx[d];
                            int ny = y + dy[d];

                            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {

                                if (visited[ny][nx])
                                    continue;

                                if (st[ny][nx] == null) {
                                    visited[ny][nx] = true;
                                    q.add(new Node(ny, nx));
                                } else if (st[ny][nx] == c) {
                                    visited[ny][nx] = true;
                                    mem[ny][nx] = null;
                                }
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                st[i][j] = mem[i][j];
            }
        }
    }
}
