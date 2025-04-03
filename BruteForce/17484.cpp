#include <iostream>
using namespace std;

int N, M;
int ans = 0x3f3f3f3f;
int price[10][10];
int dx[3] = {-1, 0, 1};

void input(void)
{
    cin >> N >> M;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < M; j++)
        {
            cin >> price[i][j];
        }
    }
}

void solve(int y, int x, int sum, int dir)
{
    if (y == N - 1)
    {
        ans = min(ans, sum);
        return ;
    }

    for(int i = 0; i < 3; i++)
    {
        if (i != dir)
        {
            int nY = y + 1;
            int nX = x + dx[i];

            if (nX >= 0 && nX < M)
                solve(nY, nX, sum + price[nY][nX], i);
        }
    }
}

int main(void)
{
    input();
    for(int i = 0; i < M; i++)
    {
        solve(0, i, price[0][i], -1);
    }
    cout << ans;
    return(0);
}