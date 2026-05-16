package string;

public class PS_70129 {

    public int[] solution(String s) {
        int[] answer = new int[2];

        int time = 0;
        int count = 0;

        while (s.length() != 1) {

            String newStr = s.replace("0", "");
            count += (s.length() - newStr.length());

            int size = newStr.length();
            s = Integer.toBinaryString(size);
            time++;
        }
        answer[0] = time;
        answer[1] = count;

        return answer;
    }
}
