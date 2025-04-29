#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
using namespace std;

int N;
string str1, str2;
pair<string, int> prefix;
vector<pair<string, int>> words;
map<string, vector<pair<int, string>>> m;

void input(void)
{
    cin >> N;
    for(int i = 0; i < N; i++)
    {
        string word; cin >> word;
        words.push_back({word, i});
        if (i == 0)
            str1 = word;
        if (i == 1)
            str2 = word;
    }
    sort(words.begin(), words.end());
}

int countPrefix(string prev, string cur)
{
    int cnt = 0;
    while (cnt < cur.size() && cnt < prev.size())
    {
        if (cur[cnt] != prev[cnt])
            break;
        cnt++;
    }
    return (cnt);
}

void updateLongestPrefix(int cnt, string curPrefix)
{
    // 새로운 최대 접두사 저장
    if (cnt > prefix.first.length())
    {
        prefix.first = curPrefix;
        prefix.second = m[curPrefix][0].first;
    }
    // 동일 길이 접두사가 나온다면 입력 순서 비교하기
    else if (cnt == prefix.first.length())
    {
        int inputIndex = prefix.second;

        if (inputIndex > m[curPrefix][0].first)
        {
            prefix.first = curPrefix;
            prefix.second = m[curPrefix][0].first;
        }
    }
}

void saveStringInPrefixGroup(string curPrefix, int i, string prev, string cur)
{
    if (m[curPrefix].size() == 0)   // 새로운 prefix 저장
    {
        if (words[i].second < words[i - 1].second)
        {
            m[curPrefix].push_back({words[i].second, cur});
            m[curPrefix].push_back({words[i - 1].second, prev});
        }
        else
        {
            m[curPrefix].push_back({words[i - 1].second, prev});
            m[curPrefix].push_back({words[i].second, cur});
        }
    }
    else  // 기존 prefix에 저장 
    {
        if (m[curPrefix][0].first > words[i].second)
        {
            m[curPrefix][1].first = m[curPrefix][0].first;
            m[curPrefix][1].second = m[curPrefix][0].second;
            m[curPrefix][0].first = words[i].second;
            m[curPrefix][0].second = words[i].first;
        }
        else if (m[curPrefix][1].first > words[i].second)
        {
            m[curPrefix][1].first = words[i].second;
            m[curPrefix][1].second = words[i].first;
        }
    }
}

void solve(void)
{
    for(int i = 1; i < N; i++)
    {
        string cur = words[i].first;
        string prev = words[i - 1].first;
        int cnt = countPrefix(prev, cur);

        if (cur == prev)
            continue;

        if (cnt == 0)
            continue;

        string curPrefix = cur.substr(0, cnt);
        saveStringInPrefixGroup(curPrefix, i, prev, cur);
        updateLongestPrefix(cnt, curPrefix);
    }
}

void output(void)
{
    if (prefix.first != "")
    {
        str1 = m[prefix.first][0].second;
        str2 = m[prefix.first][1].second;
    }
    cout << str1 << "\n" << str2;
}

int main(void)
{
    input();
    solve();
    output();
    return (0);
}