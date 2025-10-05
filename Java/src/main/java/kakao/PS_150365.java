package kakao;

import java.util.*;

public class PS_150365 {

    int dx[] = {0, -1, 1, 0};
    int dy[] = {1, 0, 0, -1};

    int N, M, sx, sy, ex, ey, K;

    // 아래, 왼, 오, 위
    public String dfs(int x, int y, String path) {

        // 남은 거리 확인
        int dist = Math.abs(x - ex) + Math.abs(y - ey);
        int remainDist = K - path.length();
        if (dist > remainDist)
            return ("impossible");

        // remainDist - dist가 짝수여야 함
        // 목적지에 도착했는데 remainDist가 남아있으면 왔다 갔다 (2번) 해야하기 때문에
        if ((remainDist - dist) % 2 != 0)
            return ("impossible");

        if (path.length() == K) {

            if (x == ex && y == ey)
                return (path);
            return ("impossible");
        }

        for(int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];
            String dir = "";

            if (nx >= 1 && nx <= M && ny >= 1 && ny <= N) {

                if (i == 0)
                    dir = "d";
                else if (i == 1)
                    dir = "l";
                else if (i == 2)
                    dir = "r";
                else if (i == 3)
                    dir = "u";

                String p = dfs(nx, ny, path + dir);
                if (p != "impossible")
                    return (p);
            }
        }
        return ("impossible");
    }


    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        String answer = "";

        N = n;
        M = m;
        sx = y;
        sy = x;
        ex = c;
        ey = r;
        K = k;

        answer = dfs(sx, sy, "");

        return answer;
    }
}
