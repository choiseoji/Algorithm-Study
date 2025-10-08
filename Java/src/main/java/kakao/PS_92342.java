package kakao;

import java.util.*;

public class PS_92342 {

    int[] apeach;
    int[] lion;
    int[] ans;
    int maxScore = 0;

    public void dfs(int idx, int size, int n) {

        if (idx == size) {

            // 화살 남으면 마지막 칸에 다 넣어주기
            if (n > 0) lion[size - 1] += n;

            int score1 = 0, score2 = 0;

            for (int i = 0; i < size; i++) {
                if (apeach[i] == 0 && lion[i] == 0) continue;
                if (apeach[i] >= lion[i]) score1 += (10 - i);
                else score2 += (10 - i);
            }

            // 정답 업데이트
            if (score2 > score1) {

                int diff = score2 - score1;
                if (diff > maxScore) {
                    maxScore = diff;
                    ans = lion.clone();
                }
                // 동점인 경우 낮은 점수를 더 많이 맞힌 경우로 업데이트
                else if (diff == maxScore) {

                    for (int i = size - 1; i >= 0; i--) {
                        if (lion[i] > ans[i]) {
                            ans = lion.clone();
                            break;
                        } else if (lion[i] < ans[i]) {
                            break;
                        }
                    }
                }
            }


            if (n > 0) lion[size - 1] -= n;
            return;
        }

        // 어피치보다 하나 더 많음
        int value = apeach[idx];
        if (n >= value + 1) {

            n -= (value + 1);
            lion[idx] = value + 1;
            dfs(idx + 1, size, n);
            n += (value + 1);
            lion[idx] = 0;
        }

        // 그냥 0
        dfs(idx + 1, size, n);
    }

    // 경우의 수 : 어피치보다 한발 더 쏘거나, 쏘지 않거나
    public int[] solution(int n, int[] info) {

        int[] answer;

        int size = info.length;
        apeach = new int[size];
        lion = new int[size];
        apeach = info;

        ans = new int[size];

        dfs(0, size, n);

        if (maxScore == 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        else
            answer = ans.clone();
        return answer;
    }
}
