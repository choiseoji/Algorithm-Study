package kakao;

import java.util.*;

public class PS_92343 {

    int ans = 0;
    int[] infos;
    List<List<Integer>> graph = new ArrayList<>();

    public void dfs(int sheep, int wolf, List<Integer> available, boolean[] visited) {

        ans = Math.max(ans, sheep);
        if (sheep <= wolf)
            return ;

        for(int i = 0; i < available.size(); i++) {

            int nextNode = available.get(i);
            List<Integer> nextAvailable = new ArrayList<>(available);

            // List에서 특정 값 삭제하기
            nextAvailable.remove(Integer.valueOf(nextNode));

            for(int j = 0; j < graph.get(nextNode).size(); j++) {

                // 여기가 포인트!!
                if (!visited[graph.get(nextNode).get(j)]) {

                    nextAvailable.add(graph.get(nextNode).get(j));
                }
            }

            visited[nextNode] = true;
            if (infos[nextNode] == 0)
                dfs(sheep + 1, wolf, nextAvailable, visited);
            else
                dfs(sheep, wolf + 1, nextAvailable, visited);

            visited[nextNode] = false;
        }
    }

    public int solution(int[] info, int[][] edges) {

        int answer = 0;
        infos = info;

        // make graph
        for(int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++) {

            Integer u = edges[i][0];
            Integer v = edges[i][1];

            graph.get(u).add(v);
        }

        // solve
        List<Integer> available = new ArrayList<>();
        boolean[] visited = new boolean[info.length];
        for(int i = 0; i < graph.get(0).size(); i++) {

            available.add(graph.get(0).get(i));
        }
        visited[0] = true;
        if (info[0] == 0)
            dfs(1, 0, available, visited);
        else
            dfs(0, 1, available, visited);
        answer = ans;
        return answer;
    }
}
