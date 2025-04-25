#include <iostream>
using namespace std;

int N;
int ans[11];

int main(void)
{
    cin >> N;
    for(int i = 1; i <= N; i++)
    {
        int n; cin >> n;

        // 남는 자리가 n개 있어야 함
        int emptyCount = 0;
        for(int j = 1; j <= N; j++)
        {
            if (ans[j] == 0)
            {
                if (emptyCount < n)
                    emptyCount ++;
                else
                {
                    ans[j] = i;
                    break ;
                }
            }
        }
    }

    // output
    for(int i = 1; i <= N; i++)
    {
        cout << ans[i] << " ";
    }
    return (0);
}