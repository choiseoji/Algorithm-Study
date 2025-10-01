package kakao;

import java.util.*;

public class PS_150370 {

    public int[] solution(String today, String[] terms, String[] privacies) {

        Map<String, Integer> m = new HashMap<>();

        // cur date parsing
        String[] dates = today.split("\\.");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]);

        // mapping
        for(String term : terms) {

            String[] t = term.split(" ");
            m.put(t[0], Integer.parseInt(t[1]));
        }

        List<Integer> tmpAns = new ArrayList<>();
        // calculate
        for(int i = 0; i < privacies.length; i++) {

            String[] privacy = privacies[i].split(" ");
            String[] pdate = privacy[0].split("\\.");

            String key = privacy[1];
            int pyear = Integer.parseInt(pdate[0]);
            int pmonth = Integer.parseInt(pdate[1]);
            int pday = Integer.parseInt(pdate[2]);

            pday += m.get(key) * 28;

            pmonth += pday / 28;
            pday = pday % 28;
            if (pday == 0) {
                pday = 28;
                pmonth --;
            }

            pyear += pmonth / 12;
            pmonth = pmonth % 12;
            if (pmonth == 0) {
                pmonth = 12;
                pyear--;
            }

            if (pyear < year)
                tmpAns.add(i + 1);
            else if (pyear == year && pmonth < month)
                tmpAns.add(i + 1);
            else if (pyear == year && pmonth == month && pday <= day)
                tmpAns.add(i + 1);
        }

        int[] answer = new int[tmpAns.size()];
        for(int i = 0; i < tmpAns.size(); i++) {

            answer[i] = tmpAns.get(i);
        }


        return answer;
    }
}
