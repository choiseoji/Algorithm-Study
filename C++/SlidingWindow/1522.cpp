#include <iostream>
#include <algorithm>
# define MAX 0x3f3f3f3f
using namespace std;

string str;
int windowSize;

// a 개수만큼 윈도우 크기 설정
void countA(void)
{
    for(int i = 0; i < str.length(); i++)
    {
        if (str[i] == 'a')
            windowSize++;
    }
}

void solve(void)
{
    int ans = MAX;
    for(int i = 0; i < str.length(); i++)
    {
        int cnt = 0;
        int idx = 0;
        while (idx < windowSize)
        {
            if (str[(i + idx) % str.length()] == 'b')
                cnt++;
            idx++;
        }
        ans = min(ans, cnt);
    }
    cout << ans;
}

int main(void)
{
    cin >> str;
    countA();
    solve();
    return (0);
}