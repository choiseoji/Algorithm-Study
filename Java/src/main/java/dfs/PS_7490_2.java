package dfs;

import java.io.*;

public class PS_7490_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {

            int N = Integer.parseInt(br.readLine());
            bt(1, 1, 2, N, "1", bw);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bt(
            int sum, int prev, int cur, int N, String output, BufferedWriter bw) throws IOException {

        if (cur == N + 1) {
            if (sum == 0) {
                bw.write(output + "\n");
            }
            return;
        }

        // 공백
        int tmpPrev = 0;
        if (prev < 0) {
            tmpPrev = prev * (-1);
            bt(sum - prev - (tmpPrev * 10 + cur),
                    - (tmpPrev * 10 + cur),
                    cur + 1,
                    N,
                    output + " " + cur,
                    bw);
        } else {
            bt(sum - prev + (prev * 10 + cur),
                    (prev * 10 + cur),
                    cur + 1,
                    N,
                    output + " " + cur,
                    bw);
        }

        // +
        bt(sum + cur,
                cur,
                cur + 1,
                N,
                output + "+" + cur,
                bw);

        // -
        bt(sum - cur,
                cur * (-1),
                cur + 1,
                N,
                output + "-" + cur,
                bw);
    }
}
