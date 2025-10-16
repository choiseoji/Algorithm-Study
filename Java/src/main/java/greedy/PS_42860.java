package greedy;

import java.util.*;

public class PS_42860 {

    public int solution(String name) {

        int answer = 0;
        Map<Character, Integer> m = new HashMap<>();

        // init
        for(int i = 0; i < 26; i++) {

            m.put((char)('A' + i), Math.min(i, 26 - i));
        }

        // solve
        // 위,아래
        for(int i = 0; i < name.length(); i++) {

            answer += m.get(name.charAt(i));
        }

        // 좌,우
        // 건너 뛸 A 구간 찾기
        int minMove = name.length() - 1;
        for(int i = 0; i < name.length(); i++) {

            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }

            // 체크
            int goRightFirst = i * 2 + name.length() - next;
            int goLeftFirst =  (name.length() - next) * 2 + i;
            minMove = Math.min(minMove, Math.min(goRightFirst, goLeftFirst));
        }
        answer += minMove;
        return answer;
    }
}
