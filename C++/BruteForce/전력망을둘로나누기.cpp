#include <string>
#include <vector>
#include <iostream>
using namespace std;
vector<int> node[102];
bool visited[102];

void init(int n)
{
    for(int i = 0; i <= n; i++)
    {
        visited[i] = false;
    }
}

int solve(int prev, int cur, int u, int v)
{
    int cnt = 1;
    
    for(int i = 0; i < node[cur].size(); i++)
    {
        int next = node[cur][i];
        
        if ((cur == u && next == v) || (cur == v && next == u))
                continue;
        
        if (!visited[next] && prev != next)
        {
            visited[next] = true;
            cnt += solve(cur, next, u, v);
        }
    }
    return (cnt);
}

int solution(int n, vector<vector<int>> wires) {
    int answer = -1;
    
    // init
    for(int i = 0; i < wires.size(); i++)
    {
        int u = wires[i][0];
        int v = wires[i][1];
        
        node[u].push_back(v);
        node[v].push_back(u);
    }
    
    // solve
    for(int i = 0; i < wires.size(); i++)
    {
        init(n);
        vector<int> nums;
        
        for(int j = 1; j <= n; j++)
        {
            if (!visited[j])
            {
                visited[j] = true;
                int num = solve(-1, j, wires[i][0], wires[i][1]);
                nums.push_back(num);
            }
        }
        int subN = abs(nums[0] - nums[1]);
        if (answer != -1)
            answer = min(answer, subN);
        else
            answer = subN;
    }
    return answer;
}