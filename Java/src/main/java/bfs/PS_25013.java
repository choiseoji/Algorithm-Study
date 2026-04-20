package bfs;

import java.util.*;

// 0: 빈 땅, 1: 석유가 있는 땅
// 영역에 넘버링 및 크기 계산
// 영역이 속해있는 열 구하기
public class PS_25013 {

    int n, m;
    int[][] mark;  // 영역 숫자로 구분
    Map<Integer, Integer> map = new HashMap<>();   // 영역 크기 저장
    Map<Integer, List<Integer>> ch = new HashMap<>();  // 각 열별로 어떤 영역이 포함되어 있는지 (key: 열, value: 포함된 영역 넘버 리스트)

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    static class Node {
        int x;
        int y;

        Node(int x, int y) {

            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] land) {

        int answer = 0;
        m = land.length;
        n = land[0].length;
        mark = new int[m][n];

        // 영역 넘버링
        int number = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                // 방문하지 않고, 석유가 있다면
                if (mark[i][j] == 0 && land[i][j] == 1) {
                    bfs(i, j, land, number);
                    number++;
                }
            }
        }

        // 최대 시추 계산
        for(int i = 0; i < n; i++) {

            int size = 0;
            List<Integer> num = ch.get(i);
            if (num == null) {
                continue;
            }

            for(int j = 0; j < num.size(); j++) {
                size += map.get(num.get(j));
            }
            answer = Math.max(answer, size);
        }

        return answer;
    }

    void bfs(int y, int x, int[][] land, int number) {

        Map<Integer, Integer> check = new HashMap<>();  // 열, ?

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        mark[y][x] = number;

        int size = 1;

        while(!q.isEmpty()) {

            Node cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            // 해당 영역이 포함되어 있는 열을 ch 맵에 저장
            if (check.get(cx) == null) {

                check.put(cx, 1);
                List<Integer> list = ch.getOrDefault(cx, new ArrayList<>());
                list.add(number);
                ch.put(cx, list);
            }

            for(int i = 0; i < 4; i++) {

                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if ((nx >= 0 && nx < n && ny >= 0 && ny < m)
                        && (mark[ny][nx] == 0 && land[ny][nx] == 1)) {

                    q.add(new Node(nx, ny));
                    mark[ny][nx] = number;
                    size++;
                }
            }
        }
        map.put(number, size);
    }
}
