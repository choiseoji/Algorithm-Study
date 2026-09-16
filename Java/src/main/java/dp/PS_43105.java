package dp;

public class PS_43105 {

    public int solution(int[][] triangle) {
        int answer = 0;
        int depth = triangle.length;
        int width = triangle[depth - 1].length;

        int[][] dp = new int[depth][width];
        dp[0][0] = triangle[0][0];

        for(int i = 1; i < depth; i++) {

            for(int j = 0; j < triangle[i].length; j++) {

                if (j == 0) {

                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == triangle[i].length - 1) {

                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {

                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }

                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer;
    }
}
