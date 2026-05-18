package hash;

import java.util.*;

public class PS_42579_2 {

    public class Info implements Comparable<Info> {

        int id;
        int cnt;

        Info (int id, int cnt) {
            this.id = id;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Info info) {

            if (info.cnt == this.cnt) {
                return this.id - info.id;  // id 오름차순
            }
            return info.cnt - this.cnt;  // cnt 내림차순
        }
    }

    // 장르별로 2개씩
    public int[] solution(String[] genres, int[] plays) {
        int[] answer;

        Map<String, Integer> genre = new HashMap<>();
        Map<String, List<Info>> m = new HashMap<>();

        // 어떤 장르를 우선으로 할지
        // 장르 내에서 어떤 고유번호를 우선으로 할지 (재생수 -> id순)
        for (int i = 0; i < genres.length; i++) {

            Integer count = genre.getOrDefault(genres[i], 0);
            genre.put(genres[i], count + plays[i]);

            // list에 저장
            List<Info> list = m.getOrDefault(genres[i], new ArrayList<>());
            list.add(new Info(i, plays[i]));
            m.put(genres[i], list);
        }

        // 장르 정렬
        List<String> keys = new ArrayList<>(genre.keySet());
        keys.sort((a, b) -> genre.get(b) - genre.get(a));

        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < keys.size(); i++) {

            String key = keys.get(i);
            List<Info> list = m.get(key);

            Collections.sort(list);
            for(int j = 0; j < Math.min(2, list.size()); j++) {

                ans.add(list.get(j).id);
            }
        }

        answer = new int[ans.size()];
        for(int i = 0; i < answer.length; i++) {

            answer[i] = ans.get(i);
        }
        return answer;
    }
}
