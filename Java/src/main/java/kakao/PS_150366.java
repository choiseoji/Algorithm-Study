package kakao;

import java.util.*;

public class PS_150366 {

    String[] board = new String[2500];
    int[] parent = new int[2500];

    public int find(int x) {

        if (parent[x] == x)
            return (x);

        return (parent[x] = find(parent[x]));
    }

    public void union(int a, int b) {

        int pa = find(a);
        int pb = find(b);

        if (pa == pb)
            return ;

        parent[pb] = pa;
    }

    public int getIndex(String a, String b) {

        int intA = Integer.parseInt(a);
        int intB = Integer.parseInt(b);

        return 50*(intA - 1) + (intB - 1);
    }

    public String[] solution(String[] commands) {

        String[] answer;
        List<String> ans = new ArrayList<>();

        // init
        for(int i = 0; i < 2500; i++) {
            parent[i] = i;
        }

        // solve
        for(int i = 0; i < commands.length; i++) {

            String[] command = commands[i].split(" ");
            if (command[0].equals("UPDATE")) {

                // 인덱스로 값 수정
                if (command.length == 4) {

                    String r = command[1];
                    String c = command[2];
                    String value = command[3];

                    int index = getIndex(r, c);
                    int root = find(index);

                    board[root] = value;
                }
                // 값으로 값 수정
                if (command.length == 3) {

                    String value1 = command[1];
                    String value2 = command[2];

                    for(int j = 0; j < 2500; j++) {

                        // 문자열 비교 주의하기!
                        if (value1.equals(board[j]))
                            board[j] = value2;
                    }
                }
            }
            if (command[0].equals("MERGE")) {

                String r1 = command[1];
                String c1 = command[2];
                String r2 = command[3];
                String c2 = command[4];

                int index1 = getIndex(r1, c1);
                int index2 = getIndex(r2, c2);

                int root1 = find(index1);
                int root2 = find(index2);

                if (root1 == root2)
                    continue;

                // 값이 있는 쪽을 부모로
                if (board[root1] == null && board[root2] != null) {
                    int tmp = root1;
                    root1 = root2;
                    root2 = tmp;
                }

                // 그래프 병합
                union(root1, root2);
            }
            if (command[0].equals("UNMERGE")) {

                String r = command[1];
                String c = command[2];
                int index = getIndex(r, c);
                int root = find(index);

                String value = board[root];

                // 갱신 (a->b->c 형태로 남아있을 수 있어서 한번 전체 업데이트 해주고, c그룹을 삭제해줘야 함)
                for(int j = 0; j < 2500; j++) {
                    find(j);
                }

                for(int j = 0; j < 2500; j++) {

                    if (find(j) == root) {

                        // 초기화
                        parent[j] = j;
                        board[j] = null;
                    }
                }

                // 원래값 복원
                board[index] = value;
            }
            if (command[0].equals("PRINT")) {

                String r = command[1];
                String c = command[2];
                int index = getIndex(r, c);
                int root = find(index);

                ans.add((board[root] != null) ? board[root] : "EMPTY");
            }
        }

        // List를 Array로 변환하는 법 잘 알아두기!
        answer = ans.toArray(new String[ans.size()]);
        return answer;
    }
}
