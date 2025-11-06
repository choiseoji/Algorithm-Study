package dfs;

import java.util.*;

public class PS_43165 {

    int ans = 0;
    List<Integer> num = new ArrayList<>();

    public void dfs(int sum, int target, int index) {

        if (index == num.size()) {

            if (sum == target)
                ans++;
            return ;
        }

        dfs(sum + num.get(index), target, index + 1);
        dfs(sum - num.get(index), target, index + 1);
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;

        // init
        for(int i = 0; i < numbers.length; i++) {
            num.add(numbers[i]);
        }

        dfs(0, target, 0);

        answer = ans;
        return answer;
    }
}
