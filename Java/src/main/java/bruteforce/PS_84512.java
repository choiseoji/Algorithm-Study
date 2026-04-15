package bruteforce;

import java.util.*;

public class PS_84512 {

    Integer index = 1;
    Map<String, Integer> map = new HashMap<>();
    Character[] ch = {'A', 'E', 'I', 'O', 'U'};

    public int solution(String word) {

        int answer = 0;

        // 사전 만들기
        makeDictionary("");

        // 순서 찾기
        answer = map.get(word);

        return answer;
    }

    public void makeDictionary(String num) {

        if (num.length() == 5) {
            return ;
        }

        for(int i = 0; i < 5; i++) {

            map.put(num + ch[i], index++);
            makeDictionary(num + ch[i]);
        }
    }
}
