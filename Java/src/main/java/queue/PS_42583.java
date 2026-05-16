package queue;

import java.util.*;

public class PS_42583 {

    public class Truck {

        int start;
        int weight;

        Truck(int time, int weight) {
            this.start = time;
            this.weight = weight;
        }
    }

    // 1초에 1칸씩 이동
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Truck> bridge = new LinkedList<>();

        int time = 1;
        int curWeight = 0;
        int curIndex = 0;

        while (true) {

            // 다리 나가는
            if (!bridge.isEmpty()) {
                Truck truck = bridge.peek();
                if (time - truck.start >= bridge_length) {
                    bridge.poll();
                    curWeight -= truck.weight;
                }
            }

            // 다리 들어오는
            if (bridge.size() < bridge_length) {

                if (curIndex != truck_weights.length
                        && truck_weights[curIndex] + curWeight <= weight) {

                    bridge.add(new Truck(time, truck_weights[curIndex]));
                    curWeight += truck_weights[curIndex];
                    curIndex++;
                }
            }

            if (bridge.isEmpty() && curIndex == truck_weights.length) {
                break;
            }
            time++;
        }

        return time;
    }
}
