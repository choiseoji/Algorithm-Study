#include <iostream>
#include <vector>
using namespace std;

int N, M;
int answer;
vector<int> fr[2001];
bool visited[2001];

void input(void)
{
    cin >> N >> M;

    for(int i = 0; i < M; i++)
    {
        int u, v;

        cin >> u >> v;
        fr[u].push_back(v);
        fr[v].push_back(u);
    }
}

void solve(int cur, int depth)
{
    if (depth == 5)
    {
        answer = 1;
        return ;
    }

    for(int i = 0; i < fr[cur].size(); i++)
    {
        int nxt = fr[cur][i];

        if (!visited[nxt])
        {
            visited[nxt] = true;
            solve(nxt, depth + 1);
            visited[nxt] = false;
            if (answer == 1)
                return ;
        }
    }
}

// 깊이가 5인 그래프를 찾아라!
int main(void)
{
    input();
    for(int i = 0; i < N; i++)
    {
        visited[i] = true;
        solve(i, 1);
        visited[i] = false;
        if (answer == 1)
            break ;
    }
    cout << answer;
    return (0);
}