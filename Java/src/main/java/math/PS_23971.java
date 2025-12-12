package math;

import java.io.*;

public class PS_23971 {

    // H(행), W(열), N(세로), M(가로)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // input
        String input = br.readLine();
        String[] nums = input.split(" ");

        Integer H = Integer.parseInt(nums[0]);
        Integer W = Integer.parseInt(nums[1]);
        Integer N = Integer.parseInt(nums[2]);
        Integer M = Integer.parseInt(nums[3]);

        // solve
        int a = (H + N) / (N + 1);
        int b = (W + M) / (M + 1);

        bw.write(Integer.toString(a * b));
        bw.flush();
        bw.close();
    }
}
