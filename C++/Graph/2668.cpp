#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
int nums[101];
bool visited[101];
bool finished[101];
vector<int> ans;

void input(void)
{
    cin >> N;
    for(int i = 1; i <= N; i++)
    {
        cin >> nums[i];
        if (i == nums[i])
        {
            ans.push_back(i);
            visited[i] = finished[i] = true;
        }
    }
}

void output(void)
{
    cout << ans.size() << "\n";

    sort(ans.begin(), ans.end());
    for(int i = 0; i < ans.size(); i++)
    {
        cout << ans[i] << "\n";
    }
}

void solve(int cur)
{
    visited[cur] = true;

    int next = nums[cur];
    if (!visited[next])
        solve(next);
    else
    {
        // 이미 방문했는데 dfs가 끝나지 않았다면 사이클
        if (!finished[next])
        {
            for(int i = next; i != cur; i = nums[i])
            {
                ans.push_back(i);
            }
            ans.push_back(cur);
        }
    }
    finished[cur] = true;
}

int main(void)
{
    input();
    for(int i = 1; i <= N; i++)
    {
        if (!visited[i])
            solve(i);
    }
    output();
    return (0);
}