#include <iostream>
#include <vector>
using namespace std;

int ans = 0x3f3f3f3f;
int N, M;  // 굴다리 길이, 가로등 수
vector<int> v;

void input(void)
{
    cin >> N >> M;
    for(int i = 0; i < M; i++)
    {
        int n; cin >> n;
        v.push_back(n);
    }
}

bool check(int mid)
{
    bool visited[100001] = {false};

    for(int i = 0; i < v.size(); i++)
    {
        int cur = v[i];

        for(int j = max(cur - mid, 0); j < min(cur + mid, N); j++)
        {
            visited[j] = true;
        }
    }

    // 확인
    for(int i = 0; i < N; i++)
    {
        if (visited[i] == false)
            return (false);
    }
    return (true);
}

void solve(int left, int right)
{
    while (left <= right)
    {
        int mid = (left + right) / 2;

        if (check(mid))
        {
            ans = min(ans, mid);
            right = mid - 1;
        }
        else  // 거짓이면 높이 더 늘려야함
        {
            left = mid + 1;
        }
    }
}

int main(void)
{
    input();
    solve(1, N);   // 최소 길이 : 1, 최대 길이: N
    cout << ans;
    return (0);
}