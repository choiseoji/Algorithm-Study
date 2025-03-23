#include <iostream>
#include <algorithm>
#include <string.h>
using namespace std;

int T, N;
int card[1001];
int dp[1001][1001];

int solve(int left, int right, int turn)
{
    if (left > right)
        return (0);

    if (dp[left][right])
        return (dp[left][right]);

    if (turn % 2 == 1)  // 근우
    {
        dp[left][right] = max(card[left] + solve(left + 1, right, turn + 1), card[right] + solve(left, right - 1, turn + 1));
        return (dp[left][right]);
    }
    else  // 명우
    {
        dp[left][right] = min(solve(left + 1, right, turn + 1), solve(left, right - 1, turn + 1));
        return (dp[left][right]);
    }
}

int main(void)
{
    
    cin >> T;
    while (T--)
    {
        cin >> N;
        for(int i = 0; i < N; i++)
        {
            cin >> card[i];
        }
        solve(0, N - 1, 1);
        cout << dp[0][N - 1] << "\n";
        memset(dp, 0, sizeof(dp));
    }
    return (0);
}