package dfs;

import java.util.*;

public class PS_43164 {

    Map<String, List<Info>> map = new HashMap<>();

    public class Info {

        String destination;
        boolean visited;

        Info(String destination, boolean visited) {
            this.destination = destination;
            this.visited = visited;
        }
    }

    // ICN 공항에서 출발
    public String[] solution(String[][] tickets) {

        String[] answer = {};

        // input
        for(int i = 0; i < tickets.length; i++) {

            String from = tickets[i][0];
            String to = tickets[i][1];

            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(new Info(to, false));
        }

        // sort
        for(List<Info> destinations : map.values()) {

            destinations.sort(Comparator.comparing(info -> info.destination));
        }

        // solve
        List<String> path = new ArrayList<>();
        path.add("ICN");
        dfs("ICN", tickets.length, path);

        answer = path.toArray(new String[0]);
        return answer;
    }

    public boolean dfs(String cur, int size, List<String> path) {

        if (path.size() == size + 1) {
            return true;
        }

        List<Info> destinations = map.get(cur);
        if (destinations == null)
            return false;

        for(int i = 0; i < destinations.size(); i++) {

            if (destinations.get(i).visited == false) {

                destinations.get(i).visited = true;
                cur = destinations.get(i).destination;
                path.add(cur);
                if (dfs(cur, size, path))
                    return true;

                path.remove(path.size() - 1);
                destinations.get(i).visited = false;
            }
        }
        return false;
    }
}
