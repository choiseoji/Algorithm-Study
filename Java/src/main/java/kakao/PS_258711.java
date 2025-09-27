package kakao;

import java.util.*;

public class PS_258711 {

    List<List<Integer>> graph = new ArrayList<>();
    int[] input;
    int[] output;
    int[] answer = new int[4];

    // 생성 : input = 0, output > 1
    // 도넛 : 노드 수 = 간선 수, input = output
    // 막대 : 노드 수 = 간선 수 + 1,
    // 8자 : 노드 수 = 간선 수 - 1, input = output

    // 1. 생성 정점을 먼저 찾자 (생성 정점은 input은 없고, 각 그래프로 뻗어 나가기만 함)
    // -> 따라서 생성 정점의 Output의 수가 그래프의 수 (여기를 시작으로 그래프 탐색하기)
    // 2. 막대 : 따라가다보면 언젠가 output = 0
    // 3. 8자 : 중간에 교차점 존재 output == 2
    // 4. 도넛 : 따라가다보면 언젠가 st 다시 만남
    public int[] solution(int[][] edges) {

        // 노드 개수 세기
        int max = 0;
        for(int[] edge : edges) {

            max = Math.max(max, Math.max(edge[0], edge[1]));
        }

        // input, output 개수 세기
        input = new int[max + 1];
        output = new int[max + 1];

        for(int[] edge : edges) {

            output[edge[0]]++;
            input[edge[1]]++;
        }

        // 생성 정점 찾기
        for(int i = 1; i < input.length; i++) {

            if (input[i] == 0 && output[i] > 1) {
                answer[0] = i;
                break ;
            }
        }

        // 그래프 만들기
        for(int i = 0; i <= max; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {

            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
        }

        // solve
        for(int i = 0; i < graph.get(answer[0]).size(); i++) {

            int st = graph.get(answer[0]).get(i);
            dfs(st, st);
        }

        return answer;
    }

    public void dfs(int st, int cur) {

        if (output[cur] == 0) {

            // 막대
            answer[2]++;
            return ;
        } else if (output[cur] == 2) {

            // 8자
            answer[3]++;
            return ;
        }

        for(int i = 0; i < graph.get(cur).size(); i++) {

            int next = graph.get(cur).get(i);

            if (next == st) {

                // 도넛
                answer[1]++;
                return ;
            }
            else {
                dfs(st, next);
            }
        }
    }
}
