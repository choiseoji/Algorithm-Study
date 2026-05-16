package stack;

import java.util.*;

public class PS_76502 {

    public int solution(String s) {

        int answer = 0;

        // x만큼 이동
        int size = s.length();
        for(int i = 0; i < size; i++) {

            if (check(s)) {
                answer++;
            }
            Character ch = s.charAt(0);
            String newStr = s.substring(1);
            s = newStr + ch;

        }

        return answer;
    }

    boolean check(String s) {

        Stack<Character> st = new Stack<>();

        // stack에 넣기
        for(int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(') {
                st.add(s.charAt(i));
            } else {

                if (st.isEmpty()) {
                    return false;
                } else {

                    Character ch = st.pop();   // stack은 조회 + 삭제 : pop

                    if (!( (s.charAt(i) == ']' && ch == '[') || (s.charAt(i) == '}' && ch == '{')
                            || (s.charAt(i) == ')' && ch == '('))) {
                        return false;
                    }
                }
            }
        }
        if (!st.isEmpty()) {
            return false;
        }
        return true;
    }
}
