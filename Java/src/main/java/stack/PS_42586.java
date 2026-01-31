package stack;

import java.util.*;

public class PS_42586 {

    public int[] solution(int[] progresses, int[] speeds) {

        Map<Integer, Integer> m = new HashMap<>();
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < progresses.length; i++) {

            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            int add = remain % speeds[i];
            if (add > 0) {
                day += 1;
            }

            if (st.isEmpty()) {
                st.push(day);
                m.put(day, 1);
            } else {

                int num = st.peek();
                if (num < day) {
                    st.push(day);
                    m.put(day, 1);
                } else {
                    m.put(num, m.get(num) + 1);
                }
            }
        }

        // answer
        List<Integer> list = new ArrayList<>();
        while (!st.isEmpty()) {

            Integer key = st.pop();
            list.add(m.get(key));
        }

        int[] answer = new int[list.size()];
        int index = 0;
        for(int i = list.size() - 1; i >= 0; i--) {

            answer[index] = list.get(i);
            index++;
        }
        return answer;
    }
}
