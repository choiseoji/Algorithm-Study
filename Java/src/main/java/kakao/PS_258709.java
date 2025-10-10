package kakao;

import java.util.*;

public class PS_258709 {

    int diceCnt;
    Integer[] aDice; // 인덱스 저장
    Integer[] bDice;
    int maxWin = 0;
    int win = 0;
    int[] ans;

    // uppderBound, lowerBound 구현 잘 알아두기!
    public int upperBound(List<Integer> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {

            int mid = (left + right) / 2;
            if (list.get(mid) <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return (left);
    }

    public void roll(int[][] dice, List<Integer> score, Integer[] diceIdx, int idx, int sum) {

        if (idx == diceIdx.length) {
            score.add(sum);
            return ;
        }

        for(int i = 0; i < 6; i++) {

            roll(dice, score, diceIdx, idx + 1, sum + dice[diceIdx[idx]][i]);
        }
    }

    public void getScore(int[][] dice) {

        // fill bDice
        int index = 0;
        List<Integer> aSet = Arrays.asList(aDice);
        for(int i = 0; i < diceCnt; i++) {

            if (!aSet.contains(i)) {

                bDice[index] = i;
                index++;
            }
        }

        // A, B가 얻을 수 있는 점수
        List<Integer> scoreA = new ArrayList<>();
        List<Integer> scoreB = new ArrayList<>();

        roll(dice, scoreA, aDice, 0, 0);
        roll(dice, scoreB, bDice, 0, 0);

        Collections.sort(scoreA);
        for(int i = 0; i < scoreB.size(); i++) {

            int idx = upperBound(scoreA, scoreB.get(i));
            win += (scoreA.size() - idx);
        }
    }

    public void getDice(int[][] dice, int idx, int n) {

        if (n == diceCnt/2) {

            win = 0;
            getScore(dice);
            if (win > maxWin) {

                maxWin = win;
                for(int i = 0; i < diceCnt / 2; i++) {

                    ans[i] = aDice[i] + 1;
                }

            }
            return ;
        }

        for(int i = idx; i < diceCnt; i++) {

            aDice[n] = i;
            getDice(dice, i + 1, n + 1);
            aDice[n] = null;
        }
    }

    public int[] solution(int[][] dice) {

        int[] answer;

        diceCnt = dice.length;
        aDice = new Integer[diceCnt / 2];
        bDice = new Integer[diceCnt / 2];
        ans = new int[diceCnt / 2];

        getDice(dice, 0, 0);

        answer = new int[diceCnt / 2];
        for(int i = 0; i < diceCnt / 2; i++) {
            answer[i] = ans[i];
        }
        return answer;
    }
}
