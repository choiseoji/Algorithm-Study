package SlidingWindow;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PS_20437 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T > 0) {

            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());
            ArrayList<Integer>[] idxList = new ArrayList[26];

            if (K == 1) {
                bw.write("1 1\n");
                T--;
                continue;
            }

            // 각 알파벳별 등장 위치 저장
            for (int i = 0; i < 26; i++) {
                idxList[i] = new ArrayList<>();
            }

            for (int i = 0; i < W.length(); i++) {
                idxList[W.charAt(i) - 'a'].add(i);
            }

            // solve
            int minLength = 0x3f3f3f3f;
            int maxLength = 0;
            for (int i = 0; i < 26; i++) {

                if (idxList[i].size() < K)
                    continue;

                for (int j = 0; j <= idxList[i].size() - K; j++) {
                    int start = idxList[i].get(j);
                    int end = idxList[i].get(j + K - 1);
                    minLength = min(minLength, end - start + 1);
                    maxLength = max(maxLength, end - start + 1);
                }
            }

            if (minLength == 0x3f3f3f3f && maxLength == 0)
                bw.write("-1\n");
            else
                bw.write(minLength + " " + maxLength + "\n");
            T--;
        }

        bw.flush();
        bw.close();
    }
}
