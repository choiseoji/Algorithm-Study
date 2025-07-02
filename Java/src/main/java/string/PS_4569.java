package string;

import java.io.*;

public class PS_4569 {

    private static void acceptOutput(String password, BufferedWriter bw) throws IOException {

        String formatted = "<" + password + ">" + " is acceptable.\n";
        bw.write(formatted);
    }

    private static void notAcceptOutput(String password, BufferedWriter bw) throws IOException {

        String formatted = "<" + password + ">" + " is not acceptable.\n";
        bw.write(formatted);
    }

    // 1. 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
    // 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
    // 3. 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
    private static boolean check1(String password) {

        boolean flag = false;
        int cnt1 = 0;  // 모음
        int cnt2 = 0;  // 자음
        char twin = 0;

        for(int i = 0; i < password.length(); i++) {

            // java 에서는 .charAt(index)로 인덱스 접근
            if (password.charAt(i) == 'a'
                    || password.charAt(i) == 'e' || password.charAt(i) == 'i'
                    || password.charAt(i) == 'o' || password.charAt(i) == 'u') {
                flag = true;
                cnt1++;
                cnt2 = 0;
            }
            else {
                cnt1 = 0;
                cnt2++;
            }

            if (cnt1 == 3 || cnt2 == 3)
                return (false);

            if (password.charAt(i) != 'o' && password.charAt(i) != 'e' && password.charAt(i) == twin) {
                return (false);
            }
            twin = password.charAt(i);
        }

        if (!flag)
            return (false);
        return (true);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // BufferedReader은 readLine 메서드만 제공 (오직 String만 입력 받음)
        String password = br.readLine();
        while (!password.equals("end")) {  // 문자열 object 비교만 되는건가 ?

            if (check1(password)) {
                acceptOutput(password, bw);
            } else {
                notAcceptOutput(password, bw);
            }
            password = br.readLine();
        }
        bw.flush();
        bw.close();
    }
}
