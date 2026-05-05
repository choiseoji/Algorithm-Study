package simulation;

public class PS_389479 {

    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] arr = new int[1030];  // 현재 증설된 서버의 수

        for(int i = 0; i < players.length; i++) {

            int cur = players[i];
            if (cur < m) {
                continue;
            }

            int cnt = cur / m;   // 필요한 서버의 수

            int added = cnt - arr[i];  // 추가 증설 서버의 수
            if (added > 0) {
                answer += added;
                for(int j = 0; j < k; j++) {
                    arr[i + j] += added;
                }
            }
        }

        return answer;
    }
}
