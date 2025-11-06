package hash;

import java.util.*;

public class PS_42578 {

    public int solution(String[][] clothes) {
        int answer = 1;
        List<String> keys = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // parsing
        for(int i = 0; i < clothes.length; i++) {

            String value = clothes[i][0];
            String key = clothes[i][1];

            if (map.get(key) == null) {
                map.put(key, 1);
                keys.add(key);
            }
            else {
                Integer prev = map.get(key);
                map.put(key, prev + 1);
            }
        }

        for(int i = 0; i < keys.size(); i++) {

            answer *= (map.get(keys.get(i)) + 1);
        }
        answer--;
        return answer;
    }
}
