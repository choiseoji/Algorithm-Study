package bfs;

import java.util.*;

public class PS_43162 {

    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;

    public void visitNode(int cur, int prev) {


        for(int i = 0; i < graph.get(cur).size(); i++) {

            int next = graph.get(cur).get(i);
            if (next != prev && !visited[next]) {

                visited[next] = true;
                visitNode(next, cur);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        // init
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n];

        // make graph
        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if (i != j && computers[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        // solve
        for(int i = 0; i < n; i++) {

            if (visited[i] == false) {
                visited[i] = true;
                visitNode(i, -1);
                answer++;
            }
        }

        return answer;
    }
}
