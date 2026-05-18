package queue;

import java.util.*;

public class PS_42627_2 {
    class Info {

        int id;
        int requestT;
        int durationT;
        int startT;   // 작업 시작 시간

        Info (int id, int requestT, int durationT, int startT) {
            this.id = id;
            this.requestT = requestT;
            this.durationT = durationT;
            this.startT = startT;
        }
    }

    // 작업 번호, 요청 시각, 소요 시간
    // 소요 시간이 짧은 것 -> 요청 시각이 빠른 것 -> 작업 번호가 작은 것
    // 대기 큐, 작업 큐
    public int solution(int[][] jobs) {
        int answer = 0;
        List<Integer> ans = new ArrayList<>();

        // 대기큐
        PriorityQueue<Info> waitq = new PriorityQueue<>(
                (a,b) -> {
                    if ((a.durationT == b.durationT)
                            && (a.requestT == b.requestT)) {
                        return a.id - b.id;
                    }
                    if (a.durationT == b.durationT) {
                        return a.requestT - b.requestT;
                    }
                    return a.durationT - b.durationT;
                });

        // 작업큐
        PriorityQueue<Info> workq = new PriorityQueue<>(
                (a, b) -> {
                    return a.id - b.id;
                });

        // solve
        int index = 0;
        int time = 0;

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        while (ans.size() < jobs.length) {

            if (!workq.isEmpty()) {

                Info cur = workq.peek();

                // 작업 종료
                if (time - cur.startT == cur.durationT) {
                    workq.poll();
                    ans.add(time - cur.requestT);
                }
            }

            // 새로운 작업 넣기 (같은 시간 대에 작업이 요청 될 수도 있음)
            while (index < jobs.length && jobs[index][0] == time) {
                waitq.add(new Info(index, time, jobs[index][1], -1));
                index++;
            }

            // 새로우 작업 시작
            if (workq.isEmpty() && !waitq.isEmpty()) {

                Info newWork = waitq.poll();
                workq.add(new Info(newWork.id, newWork.requestT, newWork.durationT, time));
            }

            time++;
        }

        for(int i = 0; i < ans.size(); i++) {
            answer += ans.get(i);
        }
        answer /= ans.size();
        return answer;
    }
}
