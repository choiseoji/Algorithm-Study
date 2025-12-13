package dfs;

import java.io.*;
import java.util.*;

public class PS_7490_1 {

    public static List<String> operand = new ArrayList<>();

    // +. -. 공백
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        operand.add(" ");
        operand.add("+");
        operand.add("-");

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {

            int N = Integer.parseInt(br.readLine());
            bt(1, N, new ArrayList<>(), bw);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bt(int cnt, int N, List<String> operands, BufferedWriter bw) throws IOException {

        if (cnt == N) {

            // cal
            List<Integer> nums = new ArrayList<>();
            List<String> operandExceptBlank = new ArrayList<>();
            nums.add(1);

            for (int i = 0; i < N - 1; i++) {

                if (operands.get(i).equals(" ")) {
                    int num = nums.get(nums.size() - 1);
                    num = num * 10 + (i + 2);
                    nums.remove(nums.size() - 1);
                    nums.add(num);
                } else {
                    nums.add(i + 2);
                    operandExceptBlank.add(operands.get(i));
                }
            }

            int sum = nums.get(0);
            for(int i = 0; i < operandExceptBlank.size(); i++) {
                if (operandExceptBlank.get(i).equals("+")) {
                    sum += nums.get(i + 1);
                } else if (operandExceptBlank.get(i).equals("-")) {
                    sum -= nums.get(i + 1);
                }
            }

            if (sum == 0) {
                String output = "1";
                for(int i = 0; i < N - 1; i++) {
                    output += operands.get(i);
                    output += (i + 2);
                }
                bw.write(output + "\n");
            }

            return;
        }

        operands.add(" ");
        bt(cnt + 1, N, operands, bw);
        operands.remove(operands.size() - 1);

        operands.add("+");
        bt(cnt + 1, N, operands, bw);
        operands.remove(operands.size() - 1);

        operands.add("-");
        bt(cnt + 1, N, operands, bw);
        operands.remove(operands.size() - 1);
    }
}
