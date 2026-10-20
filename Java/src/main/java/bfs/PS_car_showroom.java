package bfs;

import java.util.*;

public class PS_car_showroom {

    static class Node {

        int node;
        int st;

        Node(int node, int st) {
            this.node = node;
            this.st = st;
        }
    }

    // 가장 멀리 사는 사람이 가장 빨리 도착하도록 전시장 위치 정하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        List<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {

            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[v].add(w);
        }

        int[] startPoints = new int[k];
        boolean[][] visited = new boolean[n + 1][k];
        int[] visitedCount = new int[n + 1];
        Queue<Node> q = new ArrayDeque<>();

        for(int i = 0; i < n + 1; i++) {
            visitedCount[i] = 0;
        }

        for (int i = 0; i < k; i++) {
            startPoints[i] = sc.nextInt();
            visited[startPoints[i]][i] = true;
            visitedCount[startPoints[i]]++;
            q.offer(new Node(startPoints[i], i));
        }

        // solve
        int time = 0;
        boolean isAnswer = false;

        while (!q.isEmpty()) {

            time++;
            int size = q.size();
            while (size > 0) {

                Node cur = q.poll();

                for(int next : graph[cur.node]) {

                    if (visited[next][cur.st] == false) {

                        visited[next][cur.st] = true;
                        q.offer(new Node(next, cur.st));

                        visitedCount[next]++;
                        if (visitedCount[next] == k) {

                            isAnswer = true;
                            break ;
                        }
                    }
                }
                if (isAnswer == true) {
                    break ;
                }
                size--;
            }

            if (isAnswer == true) {
                break ;
            }
        }

        if (isAnswer == false) {
            time = -1;
        }

        System.out.println(time);
    }
}
