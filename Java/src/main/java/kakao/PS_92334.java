package kakao;

import java.util.*;

public class PS_92334 {

    // 정지된 계정 메일로 알림 옴
    public int[] solution(String[] id_list, String[] report, int k) {

        int[] answer = new int[id_list.length];
        Map<String, Integer> index = new HashMap<>();
        List<List<String>> info = new ArrayList<>();
        Map<String, Integer> reported = new HashMap<>();

        // init
        for(int i = 0; i < id_list.length; i++) {

            index.put(id_list[i], i);
        }
        for(int i = 0; i < id_list.length; i++) {

            info.add(new ArrayList<>());
        }

        // solve
        for(int i = 0; i < report.length; i++) {

            String[] names = report[i].split(" ");
            List<String> infos = info.get(index.get(names[0]));

            boolean flag = false;
            for(String str : infos) {

                // 이미 신고했으면 밑에 실행 X
                if (str.equals(names[1]))
                    flag = true;
            }

            if (flag == true)
                continue;

            info.get(index.get(names[0])).add(names[1]);
            Integer value = reported.get(names[1]);
            if (value != null)
                reported.put(names[1], value + 1);
            else
                reported.put(names[1], 1);
        }

        for(int i = 0; i < info.size(); i++) {

            int cnt = 0;
            for(int j = 0; j < info.get(i).size(); j++) {

                if (reported.get(info.get(i).get(j)) >= k)
                    cnt++;
            }
            answer[i] = cnt;
        }

        return answer;
    }
}
