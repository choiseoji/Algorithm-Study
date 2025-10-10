package kakao;

public class PS_92344 {

    public int solution(int[][] board, int[][] skill) {

        int answer = 0;
        // 누적합은 배열 크기 1씩 늘려줘야 함
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        // solve
        for(int i = 0; i < skill.length; i++) {

            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];

            // 적
            // -n 0 0 n
            // 0 0 0 0
            // n 0 0 -n
            if (type == 1) {
                sum[r1][c1] -= degree;
                sum[r1][c2 + 1] += degree;
                sum[r2 + 1][c1] += degree;
                sum[r2 + 1][c2 + 1] -= degree;
            }

            // 아군
            // n 0 0 -n
            // 0 0 0 0
            // -n 0 0 n
            if (type == 2) {
                sum[r1][c1] += degree;
                sum[r1][c2 + 1] -= degree;
                sum[r2 + 1][c1] -= degree;
                sum[r2 + 1][c2 + 1] += degree;
            }
        }

        for(int i = 0; i < sum.length; i++) {
            for(int j = 1; j < sum[i].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }
        for(int i = 1; i < sum.length; i++) {
            for(int j = 0; j < sum[i].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        // check
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {

                if (board[i][j] + sum[i][j] > 0)
                    answer++;
            }
        }

        return answer;
    }
}
