package kakao;

import java.util.*;

public class PS_150369 {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;

        int d = 0;
        int p = 0;

        for(int i = n - 1; i >= 0; i--) {

            d -= deliveries[i];
            p -= pickups[i];

            // 현재까지 배달 or 수거할 수 있을 정도로 cap 채워주기
            while (d < 0 || p < 0) {

                d += cap;
                p += cap;

                answer += (i + 1) * 2;
            }
        }

        return answer;
    }
}
