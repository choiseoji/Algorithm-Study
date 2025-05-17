#include <string>
#include <queue>
#include <vector>
using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    
    int i = 0;
    int sum = 0;
    queue<pair<int, int>> q;   // 다리 위에 있는 트럭 {weight, 다리에 들어온 시간}
    while (i < truck_weights.size())
    {
        // 트럭 빼기
        if (!q.empty())
        {
            int value = q.front().first;
            int time = q.front().second;
            if (time + bridge_length <= answer)
            {
                sum -= value;
                q.pop();
            }
        }
        
        // 트럭 추가
        if (q.size() < bridge_length && sum + truck_weights[i] <= weight)
        {
            q.push({truck_weights[i], answer});
            sum += truck_weights[i];
            i++;
        }
        answer++;
    }
    // 마지막 트럭이 다리 위에 올라가자마자 while 문이 끝나므로
    // 마지막 트럭이 다리를 건너는 시간을 더해주면 최종 경과 시간을 구할 수 있다.
    answer += bridge_length;
    return answer;
}