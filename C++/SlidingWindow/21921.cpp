#include <iostream>
#include <vector>
using namespace std;

int N, X;
int maxDays;
int cnt;
int days[250000];

int main(void)
{
    cin >> N >> X;
    for(int i = 0; i < N; i++)
    {
        cin >> days[i];
    }

    // solve
    int sum = 0;

    for(int i = 0; i < N; i++)
    {
        sum += days[i];
        if (i >= X)
        {
            sum -= days[i - X];
            if (sum > maxDays)
            {
                maxDays = sum;
                cnt = 1;
            }
            else if (sum == maxDays)
            {
                cnt++;
            }
        }
        if (i == X - 1)
        {
            maxDays = sum;
            cnt = 1;
        }
    }

    // output
    if (maxDays == 0)
        cout << "SAD";
    else
        cout << maxDays << "\n" << cnt;
    return (0);
}