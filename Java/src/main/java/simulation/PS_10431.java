package simulation;

import java.io.*;
import java.util.*;

public class PS_10431 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {

            String input = br.readLine();
            String[] inputSplit = input.split(" ");

            int totalCnt = 0;
            int T = Integer.parseInt(inputSplit[0]);

            List<Integer> list = new ArrayList<>();
            for(int j = 1; j <= 20; j++) {

                int n = Integer.parseInt(inputSplit[j]);

                if (j == 1) {
                    list.add(n);
                } else {

                    boolean flag = false;
                    for(int k = 0; k < list.size(); k++) {

                        // 나보다 큰 사람이 나오면 k 자리가 내 자리
                        if (n < list.get(k)) {

                            List<Integer> prev = list.subList(0, k);
                            List<Integer> post = list.subList(k, list.size());

                            List<Integer> newlist = new ArrayList<>(prev);
                            newlist.add(n);
                            newlist.addAll(post);

                            list = newlist;
                            totalCnt += post.size();
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        list.add(n);
                    }
                }
            }

            bw.write(T + " " + totalCnt + "\n");
            bw.flush();
        }
        bw.close();
    }
}
