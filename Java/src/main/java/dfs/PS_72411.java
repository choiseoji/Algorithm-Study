package dfs;

import java.util.*;

public class PS_72411 {
    Map<String, Boolean> visited = new HashMap<>();
    Map<Integer, List<String>> m = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        List<String> ans = new ArrayList<>();

        // 조합 만들기
        for(int i = 0; i < orders.length; i++) {

            List<Character> list = new ArrayList<>();
            for(int j = 0; j < orders[i].length(); j++) {
                list.add(orders[i].charAt(j));
            }
            Collections.sort(list);
            makeMenu(list, course);
        }

        // 검사하기
        for (int i = 0; i < course.length; i++) {

            int size = course[i];

            List<String> list = m.getOrDefault(size, null);
            if (list == null) {
                continue;
            }

            int maxCount = 0;
            List<String> saved = new ArrayList<>();

            for(int j = 0; j < list.size(); j++) {

                int count = 0;
                for(int k = 0; k < orders.length; k++) {

                    String newStr = orders[k].replaceAll("[^" + list.get(j) + "]", "");
                    String sortedStr = stringSort(newStr);
                    if (sortedStr.contains(list.get(j))) {
                        count++;
                    }
                }

                if (count >= 2 && count > maxCount) {
                    saved.clear();
                    saved.add(list.get(j));
                    maxCount = count;
                }
                else if (count >= 2 && count == maxCount) {
                    saved.add(list.get(j));
                }
            }
            ans.addAll(saved);
        }

        Collections.sort(ans);
        answer = ans.toArray(new String[0]);

        return answer;
    }

    void makeMenu(List<Character> list, int[] course) {

        for(int i = 0; i < course.length; i++) {

            if (list.size() >= course[i]) {
                bt(list, course[i], "", 0);
            }
        }
    }

    void bt(List<Character> list, int size, String menu, int index) {

        if (menu.length() == size) {

            if (!visited.getOrDefault(menu, false)) {

                visited.put(menu, true);
                List<String> value = m.getOrDefault(size, new ArrayList<>());
                value.add(menu);
                m.put(size, value);
            }
            return ;
        }

        for(int i = index; i < list.size(); i++) {

            String newMenu = menu + list.get(i);
            bt(list, size, newMenu, i + 1);
        }
    }

    String stringSort(String s) {

        char[] arr = s.toCharArray();
        Arrays.sort(arr);

        return new String(arr);
    }
}
