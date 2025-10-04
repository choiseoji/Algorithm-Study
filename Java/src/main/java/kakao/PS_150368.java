package kakao;

import java.util.*;

public class PS_150368 {

    int[][] userInfo;
    int[] emoticonInfo;
    double[] price = new double[101]; // 할인률 기록
    double[] p = {0.1, 0.2, 0.3, 0.4};

    int ans1 = 0;
    int ans2 = 0;

    void solve(int depth, int idx) {

        if (depth == emoticonInfo.length) {

            int emoticonPlusCnt = 0;
            int totalPrice = 0;

            for(int i = 0; i < userInfo.length; i++) {

                double priceSum = 0;

                // int / int는 정수 나눗셈이라서 소수점 버려짐 -> 하나를 double로 바꿔주기
                double percent = userInfo[i][0] / 100.0;
                int pr = userInfo[i][1];

                for(int j = 0; j < emoticonInfo.length; j++) {

                    if (price[j] >= percent) {
                        priceSum += emoticonInfo[j] * (1 - price[j]);
                    }
                }

                if (priceSum >= pr)
                    emoticonPlusCnt++;
                else
                    totalPrice += priceSum;
            }

            if (ans1 < emoticonPlusCnt) {

                ans1 = emoticonPlusCnt;
                ans2 = totalPrice;
            } else if (ans1 == emoticonPlusCnt) {

                if (ans2 < totalPrice)
                    ans2 = totalPrice;
            }

            return ;
        }

        for(int i = 0; i < p.length; i++) {

            price[idx] = p[i];
            solve(depth + 1, idx + 1);
        }
    }

    // 임티 플러스 가입자 늘리기 -> 1순위
    // 판매액 늘리기
    // 0.1, 0.2, 0.3, 0.4
    public int[] solution(int[][] users, int[] emoticons) {

        userInfo = users;
        emoticonInfo = emoticons;

        solve(0, 0);

        int[] answer = new int[2];
        answer[0] = ans1;
        answer[1] = ans2;

        return answer;
    }
}
