#include <iostream>
#include <vector>
#include <map>
#include <cmath>
#include <algorithm>
using namespace std;

int N;
map<char, int> m;
map<char, int> mNum;
vector<pair<int, char>> v;
vector<string> vStr;

void input(void)
{
    cin >> N;
    for(int i = 0; i < N; i++)
    {
        string str;

        cin >> str;
        vStr.push_back(str);
        for(int j = 0; j < str.length(); j++)
        {
            int baseNum = m[str[j]];

            baseNum += pow(10, str.length() - j);
            m[str[j]] = baseNum;
        }
    }
}

bool compare(pair<int, int> s1, pair<int, int> s2)
{
    if (s1.first > s2.first)
        return (true);
    return (false);
}

void solve(void)
{
    // map을 vector로 변환해주기 
    for(char c = 'A'; c <= 'Z'; c++)
    {
        if (m[c])
            v.push_back({m[c], c});
    }
    sort(v.begin(), v.end(), compare);

    // 9 ~ 0 차례대로 부여
    int num = 9;
    for(int i = 0; i < v.size(); i++)
    {
        mNum[v[i].second] = num;
        num--;
    }

    // 계산
    int sum = 0;
    for(int i = 0; i < N; i++)
    {
        int n = 0;
        for(int j = 0; j < vStr[i].length(); j++)
        {
            n *= 10;
            n += mNum[vStr[i][j]];
        }
        sum += n;
    }
    cout << sum;
}

int main(void)
{
    input();
    solve();
    return (0);
}