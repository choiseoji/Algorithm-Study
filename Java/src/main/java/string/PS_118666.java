package string;

import java.util.*;

public class PS_118666 {

    public String solution(String[] survey, int[] choices) {

        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        Character[] type = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

        // init
        Map<Character, Integer> scoreBoard = new HashMap<>();
        scoreBoard.put('R', 0);
        scoreBoard.put('T', 0);
        scoreBoard.put('C', 0);
        scoreBoard.put('F', 0);
        scoreBoard.put('J', 0);
        scoreBoard.put('M', 0);
        scoreBoard.put('A', 0);
        scoreBoard.put('N', 0);

        // solve
        for(int i = 0; i < survey.length; i++) {

            Character first = survey[i].charAt(0);
            Character second = survey[i].charAt(1);
            int choice = choices[i];

            if (choice < 4) {
                int lastScore = scoreBoard.get(first);
                scoreBoard.put(first, lastScore + score[choice]);
            } else {
                int lastScore = scoreBoard.get(second);
                scoreBoard.put(second, lastScore + score[choice]);
            }
        }

        // get result
        String answer = "";
        for(int i = 0; i < 8; i = i + 2) {

            answer += (scoreBoard.get(type[i]) >= scoreBoard.get(type[i + 1])) ? type[i] : type[i + 1];
        }

        return answer;
    }
}
