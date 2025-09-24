package kakao;

import java.util.*;

public class PS_258712 {

    public int solution(String[] friends, String[] gifts) {

        int answer = 0;
        int size = friends.length;

        // 내가 더 많이 줘야 받을 수 있음
        // 선물 주고받은 수가 똑같다면 내 선물 지수가 더 커야 받을 수 있음
        int[][] gift = new int[size][size];
        Map<String, Integer> m = new HashMap<>();

        for(int i = 0; i < size; i++) {

            m.put(friends[i], i);
        }

        // fill array
        for(int i = 0; i < gifts.length; i++) {

            String[] parts = gifts[i].split(" ");

            String from = parts[0];
            String to = parts[1];

            gift[m.get(from)][m.get(to)] += 1;
        }

        // count giftScore
        int[] giftScore = new int[size];
        for(int i = 0; i < size; i++) {

            int give = 0;
            int get = 0;

            for(int j = 0; j < size; j++) {

                give += gift[i][j];
                get += gift[j][i];
            }
            giftScore[i] = (give - get);
        }

        // solve
        int[] getThisMonth = new int[size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {

                if (i == j)
                    continue;

                int iGive = gift[i][j];
                int iGet = gift[j][i];

                if (iGive > iGet) {
                    getThisMonth[i] += 1;
                } else if (iGive == iGet) {

                    if (giftScore[i] > giftScore[j])
                        getThisMonth[i] += 1;
                }
            }
        }

        // 정렬
        Arrays.sort(getThisMonth);
        answer = getThisMonth[size - 1];
        return answer;
    }
}
