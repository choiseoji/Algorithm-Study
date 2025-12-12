package hash;

import java.io.*;
import java.util.*;

public class PS_25757 {

    // Y(2), F(3), O(4) -> 인원수 부족하면 게임 시작 X
    // 한번 플레이 했으면 같은 사람하고 다시 X
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N;
        int maxCnt = 0;
        int answer = 0;
        Map<String, Integer> visited = new HashMap<>();   // 이미 플레이한 사람 기록

        String input = br.readLine();
        String[] inputs = input.split(" ");
        N = Integer.parseInt(inputs[0]);
        if (inputs[1].equals("Y")) {
            maxCnt = 1;
        } else if (inputs[1].equals("F")) {
            maxCnt = 2;
        } else if (inputs[1].equals("O")){
            maxCnt = 3;
        }

        // solve
        int curCnt = 0;
        for(int i = 0; i < N; i++) {

            String name = br.readLine();
            if (visited.get(name) == null) {
                curCnt++;
                if (curCnt == maxCnt) {
                    answer++;
                    curCnt = 0;
                }
                visited.put(name, 1);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}
