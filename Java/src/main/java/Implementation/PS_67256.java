package Implementation;

import java.util.*;

public class PS_67256 {

    public class Info {

        int x;
        int y;

        Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public String solution(int[] numbers, String hand) {
        String answer = "";

        Map<Integer, Info> m = new HashMap<>();

        // map 변환
        int index = 1;
        m.put(0, new Info(1,3));
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {

                m.put(index, new Info(j, i));
                index++;
            }
        }

        // solve
        int curRight = 12;  // #
        int curLeft = 10; // *

        for(int i = 0; i < numbers.length; i++) {

            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer += 'L';
                curLeft = numbers[i];
            }
            else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
                answer += 'R';
                curRight = numbers[i];
            } else {

                Info curLoc = m.get(numbers[i]);
                Info curLeftLoc = m.get(curLeft);
                Info curRightLoc = m.get(curRight);

                int leftDist = Math.abs(curLeftLoc.x - curLoc.x) + Math.abs(curLeftLoc.y - curLoc.y);
                int rightDist = Math.abs(curRightLoc.x - curLoc.x) + Math.abs(curRightLoc.y - curLoc.y);
                if (leftDist == rightDist) {
                    if (hand.equals("left")) {
                        answer += 'L';
                        curLeft = numbers[i];
                    } else {
                        answer += 'R';
                        curRight = numbers[i];
                    }
                } else if (leftDist < rightDist) {
                    answer += 'L';
                    curLeft = numbers[i];
                } else {
                    answer += 'R';
                    curRight = numbers[i];
                }
            }
        }

        return answer;
    }
}
