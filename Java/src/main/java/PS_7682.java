import java.io.*;

public class PS_7682 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        while (!input.equals("end")) {

            // 조건 검사
            int oCnt = 0;
            int xCnt = 0;
            for(int i = 0; i < 9; i++) {

                Character c = input.charAt(i);
                if (c == 'O') {
                    oCnt++;
                }
                if (c == 'X') {
                    xCnt++;
                }
            }

            if (Math.abs(oCnt - xCnt) > 1) {  // invalid

                bw.write("invalid\n");
            } else if (oCnt > xCnt) {   // invalid

                bw.write("invalid\n");
            } else {   // (개수 같은 경우) O가 이기고, X가 지는 상황이어야 valid, 아니면 invalid (개수 하나 차이면 X가 이기는 상황)

                int oWinCnt = 0;
                int xWinCnt = 0;

                // 0
                if ((input.charAt(0) == input.charAt(1)) && (input.charAt(1) == input.charAt(2))) {

                    if (input.charAt(0) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(0) == 'X') {
                        xWinCnt++;
                    }
                }
                if ((input.charAt(0) == input.charAt(3)) && (input.charAt(3) == input.charAt(6))) {

                    if (input.charAt(0) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(0) == 'X') {
                        xWinCnt++;
                    }
                }
                if ((input.charAt(0) == input.charAt(4)) && (input.charAt(4) == input.charAt(8))) {

                    if (input.charAt(0) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(0) == 'X') {
                        xWinCnt++;
                    }
                }
                // 1
                if ((input.charAt(1) == input.charAt(4)) && (input.charAt(4) == input.charAt(7))) {

                    if (input.charAt(1) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(1) == 'X') {
                        xWinCnt++;
                    }
                }
                // 2
                if ((input.charAt(2) == input.charAt(5)) && (input.charAt(5) == input.charAt(8))) {

                    if (input.charAt(2) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(2) == 'X') {
                        xWinCnt++;
                    }
                }
                if ((input.charAt(2) == input.charAt(4)) && (input.charAt(4) == input.charAt(6))) {

                    if (input.charAt(2) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(2) == 'X') {
                        xWinCnt++;
                    }
                }
                // 3
                if ((input.charAt(3) == input.charAt(4)) && (input.charAt(4) == input.charAt(5))) {

                    if (input.charAt(3) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(3) == 'X') {
                        xWinCnt++;
                    }
                }
                // 6
                if ((input.charAt(6) == input.charAt(7)) && (input.charAt(7) == input.charAt(8))) {

                    if (input.charAt(6) == 'O') {
                        oWinCnt++;
                    } else if (input.charAt(6) == 'X') {
                        xWinCnt++;
                    }
                }

                // 판단
                if (oCnt == xCnt) {
                    if (oWinCnt == 1 && xWinCnt == 0) {
                        bw.write("valid\n");
                    } else {
                        bw.write("invalid\n");
                    }
                }
                if (oCnt < xCnt) {

                    if (oWinCnt == 0 && xWinCnt >= 1) {
                        bw.write("valid\n");
                    } else if (oWinCnt == 0 && xWinCnt == 0 && (oCnt + xCnt) == 9) {
                        bw.write("valid\n");
                    } else {
                        bw.write("invalid\n");
                    }
                }
            }
            bw.flush();

            // 다음꺼 입력받기
            input = br.readLine();
        }
        bw.close();
    }
}
