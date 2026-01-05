package SlidingWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PS_2531 {

    // K개를 연속해서 먹으면 할인
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String[] inputs = input.split(" ");

        Integer N = Integer.parseInt(inputs[0]);
        Integer d = Integer.parseInt(inputs[1]);
        Integer k = Integer.parseInt(inputs[2]);
        Integer c = Integer.parseInt(inputs[3]);

        List<Integer> nums = new ArrayList<>();
        for(int i = 0; i < N; i++) {

            nums.add(Integer.parseInt(br.readLine()));
        }

        // solve
        // 연속해서 K 개 먹을 수 있는 조합 찾고, 그 안에 c가 없으면 K + 1이 정답
        for(int i = 0; i < k - 1; i++) {
            nums.add(nums.get(i));
        }

        int[] cnt = new int[d + 1];
        int type = 0;

        // 초기 윈도우
        for(int i = 0; i < k; i++) {
            int x = nums.get(i);
            if (cnt[x] == 0) type++;
            cnt[x]++;
        }
        int maxCount = type + (cnt[c] == 0 ? 1 : 0);

        // 슬라이딩
        for(int i = 1; i < N; i++) {

            int remove = nums.get(i - 1);
            cnt[remove]--;
            if (cnt[remove] == 0) {
                type--;
            }

            int add = nums.get((i + k - 1));
            if (cnt[add] == 0) {
                type++;
            }
            cnt[add]++;

            int cur = type + (cnt[c] == 0 ? 1 : 0);
            maxCount = Math.max(maxCount, cur);
        }

        bw.write(String.valueOf(maxCount));
        bw.flush();
        bw.close();
    }
}
