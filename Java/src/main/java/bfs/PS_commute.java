package bfs;

import java.util.*;

public class PS_commute {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Integer>[] graph = new ArrayList[n + 1];
        List<Integer>[] reverseGraph = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            graph[x].add(y);
            reverseGraph[y].add(x);
        }
        int S = sc.nextInt();
        int T = sc.nextInt();

        // solve
        int[] fromS = bfs(S, T, n, graph);
        int[] toT = bfs(T, -1, n, reverseGraph);
        int[] fromT = bfs(T, S, n, graph);
        int[] toS = bfs(S, -1, n, reverseGraph);

        int ans = 0;
        for(int i = 0; i <= n; i++) {

            if (i == S || i == T) {
                continue;
            }

            if (fromS[i] + toT[i] + fromT[i] + toS[i] == 4) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static int[] bfs(int st, int de, int n, List<Integer>[] gr) {

        int[] visited = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        visited[st] = 1;
        q.offer(st);

        while (!q.isEmpty()) {

            int cur = q.poll();
            if (cur == de) {
                continue;
            }

            for(int next : gr[cur]) {

                if (visited[next] != 1) {
                    visited[next] = 1;
                    q.offer(next);
                }
            }
        }

        return (visited);
    }
}
