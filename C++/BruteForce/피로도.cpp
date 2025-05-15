#include <string>
#include <vector>
using namespace std;

int K, ans;
int dungeonCnt;
vector<int> seq;
bool visited[10];
vector<vector<int>> d;

int checkAns()
{
    int sum = K;
    int cnt = 0;
    for(int i = 0; i < seq.size(); i++)
    {
        int idx = seq[i];
        
        if (sum < d[idx][0])
            break ;
        
        sum -= d[idx][1];
        cnt++;
    }
    return (cnt);
}

void solve()
{
    if (seq.size() == dungeonCnt)
    {
        ans = max(ans, checkAns());
        return ;
    }
    
    for(int i = 0; i < dungeonCnt; i++)
    {
        if (!visited[i])
        {
            visited[i] = true;
            seq.push_back(i);
            solve();
            visited[i] = false;
            seq.pop_back();
        }
    }
}

// k: 현재 피로도, dungeons: 최소 필요 피로도, 소모 피로도
int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1;
    
    K = k;
    d = dungeons;
    dungeonCnt = dungeons.size();
    solve();
    answer = ans;
    return answer;
}