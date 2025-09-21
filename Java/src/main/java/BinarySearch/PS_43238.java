package BinarySearch;

import java.util.*;

public class PS_43238 {

    public long solution(int n, int[] times) {

        long answer = 0;
        int max = Arrays.stream(times).max().getAsInt();

        long left = 1;
        long right = (long) max * n;

        while (left <= right) {

            long mid = (left + right) / 2;
            long total = 0;

            for (int i = 0; i < times.length; i++) {
                total += (mid / times[i]);
            }

            if (total >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
