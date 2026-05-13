package Implementation;

import java.util.*;

public class PS_49993 {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Map<Character, Integer> m = new HashMap<>();

        for(int i = 0; i < skill.length(); i++) {
            m.put(skill.charAt(i), i);
        }

        for(int i = 0; i < skill_trees.length; i++) {

            String curSkill = skill_trees[i];

            boolean isAnswer = true;
            int index = 0;  // skill 어디까지 배웠는지
            for(int j = 0; j < curSkill.length(); j++) {

                Character curChar = curSkill.charAt(j);
                if (m.get(curChar) != null) {

                    int curIndex = m.get(curChar);
                    if (index == curIndex) {
                        index++;
                    } else {
                        isAnswer =false;
                        break ;
                    }
                }
            }

            if (isAnswer == true) {
                answer++;
            }
        }
        return answer;
    }

    public int solution2(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i = 0; i < skill_trees.length; i++) {

            String removed = skill_trees[i].replaceAll("[^" + skill + "]" , "");
            if (skill.indexOf(removed) == 0) {
                answer ++;
            }
        }

        return answer;
    }
}
