package string;

import java.util.*;

public class PS_60057 {

    // 최대 사이즈는 s.length() / 2
    public int solution(String s) {
        int answer = 1001;

        int size = s.length() / 2 + 1;
        while (size > 0) {

            int ans = 0;

            // 문자열 나누기
            List<String> list = new ArrayList<>();

            for(int start = 0; start < s.length(); start += size) {

                int end = Math.min(start + size, s.length());
                list.add(s.substring(start, end));
            }

            // 압축
            int count = 1;
            for(int i = 1; i < list.size(); i++) {

                if (list.get(i).equals(list.get(i - 1))) {
                    count ++;
                } else {

                    if (count == 1)
                        ans += list.get(i - 1).length();
                    else {

                        String num = String.valueOf(count);
                        ans += (list.get(i - 1).length() + num.length());
                    }
                    count = 1;
                }
            }

            // 마지막
            if (count == 1) {
                ans += list.get(list.size() - 1).length();
            } else {

                String num = String.valueOf(count);
                ans += (list.get(list.size() - 1).length() + num.length());
            }

            answer = Math.min(ans, answer);
            size--;
        }

        return answer;
    }
}
