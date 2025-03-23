#include <iostream>
#include <vector>
#include <string.h>
#include <algorithm>
using namespace std;

int N;
vector<int> node[1000001];
int dp[1000001][2];  // dp[i][0] : i가 얼리어답터가 아닐 때, 본인 + 자식의 얼리 어답터 수 (두번째가 1이면 i가 얼리어답터일때)

void input(void)
{
    cin >> N;
    for(int i = 0; i < N - 1; i++)
    {
        int u, v;

        cin >> u >> v;
        node[u].push_back(v);
        node[v].push_back(u);
    }
}

void solve(int prev, int cur)
{
    dp[cur][0] = 0;
    dp[cur][1] = 1;

    for(int i = 0; i < node[cur].size(); i++)
    {
        int next = node[cur][i];

        if (next == prev)
            continue;

        solve(cur, next);

        // 현재 얼리 어답터 아니라면 -> 자식이 무조건 얼리어답터
        dp[cur][0] += dp[next][1];
        // 현재 얼리 어답터라면 -> 자식 뭐든 상관없음
        dp[cur][1] += min(dp[next][0], dp[next][1]);
    }
}

int main(void)
{
    input();
    solve(0, 1);
    cout << min(dp[1][0], dp[1][1]);
    return (0);
}