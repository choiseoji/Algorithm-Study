#include <iostream>
#include <vector>
using namespace std;

int N, K;
int ans;
int nums[200001];
int cnt[200001];

void input(void)
{
    cin >> N >> K;
    for(int i = 0; i < N; i++)
    {
        cin >> nums[i];
    }
}

void solve(void)
{
    int left = 0;
    int right = 0;

    while (left <= right && right < N)
    {
        if (cnt[nums[right]] == K)
        {
            cnt[nums[left]]--;
            left++;
            continue;
        }
        cnt[nums[right]]++;
        right++;
        ans = max(ans, right - left);
    }
    cout << ans;
}

int main(void)
{
    input();
    solve();
    return (0);
}