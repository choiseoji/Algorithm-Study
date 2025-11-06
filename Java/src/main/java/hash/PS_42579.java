package hash;

import java.util.*;

public class PS_42579 {

    // 오름차순 (this - o)
    public class Info implements Comparable<Info> {
        Integer total;
        Integer cnt;
        Integer index;

        String genre;

        Info(Integer total, Integer cnt, Integer index, String genre) {
            this.total = total;
            this.cnt = cnt;
            this.index = index;
            this.genre = genre;
        }

        @Override
        public int compareTo(Info o) {
            if (this.total != o.total)
                return o.total - this.total;
            else if (this.cnt != o.cnt)
                return o.cnt - this.cnt;
            else
                return this.index - o.index;
        }
    }

    public int[] solution(String[] genres, int[] plays) {

        int[] answer;
        Map<String, Integer> total = new HashMap<>();

        for(int i = 0; i < genres.length; i++) {
            String g = genres[i];
            int p = plays[i];

            if (total.get(g) != null) {
                total.put(g, total.get(g) + p);
            } else {
                total.put(g, p);
            }
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        for(int i = 0; i < genres.length; i++) {

            String g = genres[i];
            int p = plays[i];

            pq.add(new Info(total.get(g), p, i, g));
        }


        int idx = 0;
        Map<String, Integer> visited = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {

            Info top = pq.poll();

            String g = top.genre;
            if (visited.get(g) != null && visited.get(g) == 2)
                continue;

            // visited 업데이트
            if (visited.get(g) == null)
                visited.put(g, 1);
            else
                visited.put(g, visited.get(g) + 1);

            ans.add(top.index);
            idx++;
        }
        answer = ans.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}
