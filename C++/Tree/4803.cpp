#include <iostream>
#include <vector>
using namespace std;

vector<int> num[501];
bool visited[501] = {false, };

bool solve(int cur, int prev)
{
    if (visited[cur] == true)
        return (false);

    visited[cur] = true;
    for(int i = 0; i < num[cur].size(); i++)
    {
        int next = num[cur][i];

        if (next != prev)
        {
            if (solve(next, cur) == false)
                return (false);
        }
    }
    return (true);
}

// visited 개수 세기?
int main(void)
{
    int n, m;  // 정점 개수, 간선의 개수
    int t = 1;

    while (1)
    {
        cin >> n >> m;
        if (n == 0 && m == 0)
            break ;

        for(int i = 0; i < m; i++)
        {
            int u, v;

            cin >> u >> v;
            num[u].push_back(v);
            num[v].push_back(u);
        }
        
        int cnt = 0;
        for(int i = 1; i <= n; i++)
        {
            if (visited[i] == false)
            {
                if (solve(i, 0))
                    cnt++;
            }
        }

        if (cnt == 1)
            cout << "Case " << t << ": There is one tree.\n";
        else if (cnt > 1)
            cout << "Case " << t << ": A forest of " << cnt << " trees.\n";
        else
            cout << "Case " << t << ": No trees.\n";
        t++;

        // init
        for(int i = 0; i <= n; i++)
        {
            visited[i] = false;
            num[i].clear();
        }
    }
    return (0);
}