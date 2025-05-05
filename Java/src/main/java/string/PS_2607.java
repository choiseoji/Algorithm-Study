package string;

import java.io.*;

public class PS_2607 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();
        int[] alpha = new int[26];
        int ans = 0;

        for(int i = 0; i < firstWord.length(); i++)
        {
            alpha[firstWord.charAt(i) - 'A']++;
        }

        N--;
        while (N-- > 0)
        {
            String str = br.readLine();
            int wrongCnt = 0;
            int[] count = alpha.clone();

            for(int i = 0; i < str.length(); i++)
            {
                count[str.charAt(i) - 'A']--;
                if (count[str.charAt(i) - 'A'] < 0)
                    wrongCnt++;
            }

            if (firstWord.length() == str.length() && wrongCnt <= 1)
                ans++;
            else if (firstWord.length() - str.length() == 1 && wrongCnt == 0)
                ans++;
            else if (str.length() - firstWord.length() == 1 && wrongCnt <= 1)
                ans++;
        }
        bw.write(Integer.toString(ans));
        bw.flush();
        bw.close();
    }
}
