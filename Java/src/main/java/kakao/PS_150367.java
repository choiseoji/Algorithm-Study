package kakao;

import java.util.*;

public class PS_150367 {

    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];
        String[] binary = new String[numbers.length];

        // 이진수로 바꾸기
        for(int i = 0; i < numbers.length; i++) {

            binary[i] = Long.toBinaryString(numbers[i]);

            // 포화 이진트리로 바꿔주기
            int size = binary[i].length();
            int st = 1;
            int n = 1;
            while (true) {

                if (n >= size) {

                    int addNum = n - size;
                    String s = "";
                    for(int j = 0; j < addNum; j++) {

                        s += "0";
                    }
                    binary[i] = s + binary[i];
                    break;
                }
                st = st * 2;
                n = n + st;

            }

            answer[i] = solve(binary[i], 0, binary[i].length() - 1, 1);
        }

        return answer;
    }

    public int solve(String str, int start, int end, int flag) {

        if (start > end)
            return (1);

        int root = (start + end) / 2;

        if (flag == 0) {

            if (str.charAt(root) == '1')
                return (0);
        }

        if (str.charAt(root) == '0')
            flag = 0;

        if (solve(str, start, root - 1, flag) == 0)
            return (0);
        if (solve(str, root + 1, end, flag) == 0)
            return (0);
        return (1);
    }

}
