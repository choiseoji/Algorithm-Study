#include <iostream>
#include <vector>
using namespace std;

int N;
int building[51];
int cnt[51];

void input(void)
{
    cin >> N;
    for(int i = 0; i < N; i++)
    {
        cin >> building[i];
    }
}

void solve(void)
{
    for(int i = 0; i < N; i++)
    {
        double maxValue = -9999999999;

        // i번째 빌딩을 기준으로 기울기 측정
        for(int j = i + 1; j < N; j++)
        {
            double value = (double)(building[j] - building[i]) / (j - i);
            if (value > maxValue)
            {
                maxValue = value;
                cnt[i]++;
                cnt[j]++;
            }
        }
    }
}

void output(void)
{
    int ans = 0;
    for(int i = 0; i < N; i++)
    {
        ans = max(ans, cnt[i]);
    }
    cout << ans;
}

int main(void)
{
    input();
    solve();
    output();
    return (0);
}