package hash;

import java.util.*;

public class PS_42576 {

    // 참여자, 완주자
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> names = new HashMap<>();

        // map에 참여자 등록
        for(int i = 0; i < participant.length; i++) {

            String name = participant[i];

            Integer num = names.get(name);
            if (num == null) {

                names.put(name, 1);
            } else {
                names.put(name, num + 1);
            }
        }

        // 완주자 소거
        for(int i = 0; i < completion.length; i++) {

            String name = completion[i];
            Integer num = names.get(name);
            names.put(name, num - 1);
            if (num - 1 == 0) {
                names.remove(name);
            }
        }

        // map에 남아있는 name 반환
        for(Map.Entry<String, Integer> en : names.entrySet()) {
            answer = en.getKey();
        }

        return answer;
    }
}
