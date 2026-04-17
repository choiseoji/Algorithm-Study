package BinarySearch;

import java.util.*;

public class PS_340212 {

    // 난이도 <= 숙련도 : 정답 (현재 시간만큼 소요)
    // 난이도 > 숙련도 : 차이만큼 틀림 (현재 시간 + 이전 시간 소요)
    // 숙련도 최솟값 구하기
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        Integer left = 1;
        Integer right = 1000000;

        while (left <= right) {

            Integer mid = (left + right) / 2;

            long time = solve(mid, diffs, times, limit);
            if (time > limit) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }

    long solve(int level, int[] diffs, int[] times, long limit) {

        long time = 0;

        for(int i = 0; i < diffs.length; i++) {

            int time_cur = times[i];
            int time_prev = (i == 0) ? 0 : times[i - 1];

            if (diffs[i] <= level) {
                time += time_cur;
            } else {
                int retry = diffs[i] - level;
                time += (time_cur + time_prev) * retry + time_cur;
            }
        }
        return time;
    }
}
