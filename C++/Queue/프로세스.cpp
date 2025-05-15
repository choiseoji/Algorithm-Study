#include <string>
#include <queue>
#include <vector>
using namespace std;

// 우선순위 높은 프로세스 먼저 실행
int solution(vector<int> priorities, int location) {
    int answer = 0;
    vector<int> ans;
    priority_queue<pair<int, int>> pq;
    queue<pair<int, int>> q;
    
    for(int i = 0; i < priorities.size(); i++)
    {
        pq.push({priorities[i], i});
        q.push({priorities[i], i});
    }
    
    while (!q.empty())
    {
        if (pq.top().first == q.front().first)
        {
            ans.push_back(q.front().second);
            q.pop();
            pq.pop();
        }
        else
        {
            q.push({q.front().first, q.front().second});
            q.pop();
        }
    }
    
    for(int i = 0; i < ans.size(); i++)
    {
        if (ans[i] == location)
        {
            answer = i + 1;
            break ;
        }
    }
    return answer;
}