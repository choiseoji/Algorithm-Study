#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<int> pl;
vector<int> mi;

bool compare(int s1, int s2)
{
    if (s1 > s2)
        return (true);
    return (false);
}

void input(void)
{
    cin >> N >> M;
    for(int i = 0; i < N; i++)
    {
        int n; cin >> n;
        if (n > 0)
            pl.push_back(n);
        else
            mi.push_back(abs(n));
    }
    sort(pl.begin(), pl.end(), compare);
    sort(mi.begin(), mi.end(), compare);
}

int findDistMinus()
{
    int dist = mi[0];
            
    int cnt = 0;
    while (!mi.empty())
    {
        if (cnt == M)
            break ;
        mi.erase(mi.begin() + 0);
        cnt++;
    }
    return (dist);
}

int findDistPlus()
{
    int dist = pl[0];
    
    int cnt = 0;
    while (!pl.empty())
    {
        if (cnt == M)
            break ;
        pl.erase(pl.begin() + 0);
        cnt++;
    }
    return (dist);
}

void solve(void)
{
    int dist = 0;

    // 가장 큰 숫자가 어느 배열에 있는지 확인
    if (pl.size() > 0 && mi.size() > 0)
    {
        if (pl[0] > mi[0])
            dist = findDistPlus();
        else
            dist = findDistMinus();
    }
    else if (pl.size() > 0 && mi.size() == 0)
        dist = findDistPlus();
    else if (mi.size() > 0 && pl.size() == 0)
        dist = findDistMinus();

    // 큰거 두개를 묶기
    // 먼저 음수 배열부터
    for(int i = 0; i < mi.size(); i = i + M)
    {
        dist += (2 * mi[i]);
    }
    
    // 그 다음 양수 배열
    for(int i = 0; i < pl.size(); i = i + M)
    {
        dist += (2 * pl[i]);
    }

    cout << dist;
}

int main(void)
{
    input();
    solve();
    return (0);
}