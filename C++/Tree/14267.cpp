#include <iostream>
#include <vector>
using namespace std;

int n, m;  // 회사 직원 수, 칭찬 횟수
vector<int> node[100001];
int score[100001];

void solve(int cur, long long sc)
{
    for(int i = 0; i < node[cur].size(); i++)
    {
        int next = node[cur][i];
        score[next] += score[cur];
        solve(next, sc);
    }
}

int main(void)
{
    cin >> n >> m;
    for(int i = 1; i <= n; i++)
    {
        int u; cin >> u;

        if (u != -1)
            node[u].push_back(i);
    }

    // 칭찬을 받을 직원 번호, 칭찬의 수치
    for(int i = 0; i < m; i++)
    {
        int u, w;

        cin >> u >> w;
        score[u] = w;
    }
    solve(1, 0);

    // output
    for(int i = 1; i <= n; i++)
    {
        cout << score[i] << " ";
    }
    return (0);
}