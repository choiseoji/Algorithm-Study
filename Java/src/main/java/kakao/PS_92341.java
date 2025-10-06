package kakao;

import java.util.*;

public class PS_92341 {

    public Integer getTime(String time) {

        String[] times = time.split(":");

        Integer hour = Integer.parseInt(times[0]);
        Integer minute = Integer.parseInt(times[1]);

        return hour * 60 + minute;
    }

    public int[] solution(int[] fees, String[] records) {

        int[] answer;

        // TreeMap 알아두기! key 기준으로 자동 오름차순 정렬해줌
        Map<String, Integer> timePerCar = new TreeMap<>();  // 누적시간
        Map<String, String> lastTimePerCar = new HashMap<>();  // 마지막 입차 시각

        for(int i = 0; i < records.length; i++) {

            String[] info = records[i].split(" ");
            String time = info[0];
            String car = info[1];
            String type = info[2];

            if (type.equals("OUT")) {

                String lastTime = lastTimePerCar.get(car);
                Integer duringTime = getTime(time) - getTime(lastTime);

                Integer value = timePerCar.getOrDefault(car, 0);
                timePerCar.put(car, value + duringTime);

                // lastTimePerCar 초기화
                lastTimePerCar.put(car, "");

            } else {

                // 마지막이 IN 이라면
                String last = lastTimePerCar.getOrDefault(car, "");
                if (!last.equals("")) {

                    String lastTime = lastTimePerCar.get(car);
                    Integer duringTime = getTime("23:59") - getTime(lastTime);
                }

                lastTimePerCar.put(car, time);
            }
        }

        // 마지막 확인
        // Map 원소 순회하는거 알아두기!
        for (Map.Entry<String, String> entry : lastTimePerCar.entrySet()) {
            String car = entry.getKey();
            String time = entry.getValue();

            if (!time.equals("")) {

                String lastTime = lastTimePerCar.get(car);
                Integer duringTime = getTime("23:59") - getTime(lastTime);

                Integer value = timePerCar.getOrDefault(car, 0);
                timePerCar.put(car, value + duringTime);
            }
        }

        // solve
        List<Integer> ans = new ArrayList<>();
        for(Integer value : timePerCar.values()) {

            if (value < fees[0])
                ans.add(fees[1]);
            else {

                int over = value - fees[0];
                // 올림하는거 알아두기! Math.ceil
                int count = (int) Math.ceil(over / (double) fees[2]);
                int cost = fees[1] + count * fees[3];
                ans.add(cost);
            }

        }

        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
