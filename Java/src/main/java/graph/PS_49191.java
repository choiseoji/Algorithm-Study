package graph;

import java.util.*;

public class PS_49191 {

    public int solution(int n, int[][] results) {

        int answer = 0;
        int[][] arr = new int[101][101];

        // init
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {

                if (i == j) {
                    arr[i][j] = 0;
                }
            }
        }

        // 첫번째 update
        for(int i = 0; i < results.length; i++) {
            int winNode = results[i][0];
            int loseNode = results[i][1];

            arr[winNode][loseNode] = 1;
            arr[loseNode][winNode] = -1;
        }

        // 두번째 update
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                        arr[j][i] = -1;
                    }
                }
            }
        }

        // 답 찾기
        for(int i = 1; i <= n; i++) {

            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if (arr[i][j] == 1 || arr[i][j] == -1)
                    cnt++;
            }
            if (cnt == n - 1)
                answer ++;
        }

        return answer;
    }
}
