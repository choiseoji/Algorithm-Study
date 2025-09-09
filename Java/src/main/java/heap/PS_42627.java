package heap;

import java.util.*;

public class PS_42627 {

    static class Info implements Comparable<Info> {

        int duration;
        int reqTime;
        int num;

        Info(int duration, int reqTime, int num) {

            this.duration = duration;
            this.reqTime = reqTime;
            this.num = num;
        }

        @Override
        public int compareTo(Info o) {

            if (this.duration != o.duration) return this.duration - o.duration;
            if (this.reqTime != o.reqTime) return this.reqTime - o.reqTime;
            return this.num - o.num;
        }
    }

    // 작업 번호, 작업 요청 시각, 작업 소요 시간
    // 소요시간 짧은 것, 요청 시각이 빠른 것, 작업 번호 작은 것
    public int solution(int[][] jobs) {

        int answer = 0;

        // jobs 요청 시간 순으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int idx = 0;
        int jobCnt = 0;
        int jobEndTime = 0;
        PriorityQueue<Info> pq = new PriorityQueue();

        while (jobCnt < jobs.length) {

            // 현재까지 들어온 작업 모두 pq에 넣어주기
            while (idx < jobs.length && jobs[idx][0] <= jobEndTime) {

                pq.add(new Info(jobs[idx][1], jobs[idx][0], idx));
                idx++;
            }

            // pq가 비어있으면 다음 작업으로 건너뛰기
            if (pq.isEmpty()) {
                jobEndTime = jobs[idx][0];
            } else {

                // 현재 처리 가능한 작업하기
                Info curJob = pq.poll();
                jobEndTime += curJob.duration;
                answer += (jobEndTime - curJob.reqTime);
                jobCnt++;
            }
        }
        answer /= jobs.length;
        return answer;
    }
}
