package SlidingWindow;

public class PS_178870 {

    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int sum = sequence[0];
        int windowSize = 1000001;
        int left = 0;
        int right = 1;

        while (right < sequence.length) {

            if (sum == k) {

                if (right - left - 1 < windowSize) {
                    answer[0] = left;
                    answer[1] = right - 1;
                    windowSize = right - left - 1;
                }
                sum -= sequence[left];
                left++;
            }

            if (sum > k) {
                sum -= sequence[left];
                left++;
            }
            else if (sum < k) {
                sum += sequence[right];
                right++;
            }
        }

        if (sum == k && (right - left - 1) < windowSize) {
            answer[0] = left;
            answer[1] = right - 1;
            windowSize = right - left - 1;
        }

        // 마지막까지
        while (left < right) {

            sum -= sequence[left];
            left++;
            if (sum == k && (right - left - 1) < windowSize) {
                answer[0] = left;
                answer[1] = right - 1;
                windowSize = right - left - 1;
            }
        }

        return answer;
    }
}
