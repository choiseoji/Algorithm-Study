package queue;

import java.util.*;

public class PS_121689 {

    // k초마다 손님 방문
    public int solution(int[] menu, int[] order, int k) {

        int answer = 0;

        Queue<Integer> waiting = new LinkedList<>();
        int customer = 0;
        int curTime = 0;
        int endTime = 0;


        while (customer < order.length) {

            // 대기 끝나는 사람 계산
            while (!waiting.isEmpty() && endTime <= curTime) {

                waiting.poll();

                // 다음 사람 조리 시작
                if (!waiting.isEmpty()) {

                    int nextCustomer = waiting.peek();
                    endTime += menu[order[nextCustomer]];
                }
            }

            // 새로 들어온 사람
            if (endTime <= curTime) {
                endTime = curTime + menu[order[customer]];
            }
            waiting.add(customer);
            customer++;

            answer = Math.max(answer, waiting.size());
            curTime += k;
        }

        return answer;
    }
}
