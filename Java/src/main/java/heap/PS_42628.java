package heap;

import java.util.*;

public class PS_42628 {

    public int[] solution(String[] operations) {
        int[] answer = {0, 0};

        PriorityQueue<Integer> minHeap = new PriorityQueue();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {

            String[] words = operation.split(" ");
            int num = Integer.parseInt(words[1]);

            // insert
            if (words[0].equals("I")) {

                maxHeap.add(num);
                minHeap.add(num);
            }
            // delete max
            if (words[0].equals("D") && num == 1) {

                if (!maxHeap.isEmpty()) {
                    int n = maxHeap.poll();
                    minHeap.remove(n);
                }
            }
            // delete min
            if (words[0].equals("D") && num == -1) {

                if (!minHeap.isEmpty()) {
                    int n = minHeap.poll();
                    maxHeap.remove(n);
                }
            }
        }

        // 출력
        int maxNum = 0;
        int minNum = 0;
        if (!maxHeap.isEmpty())
            maxNum = maxHeap.poll();
        if (!minHeap.isEmpty())
            minNum = minHeap.poll();

        answer[0] = maxNum;
        answer[1] = minNum;

        return answer;
    }
}
