#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

string S, T;

// 문자열 뒤에 A 추가
// 문자열 뒤에 B 추가 + 뒤집기
int bt(string cur)
{
    if (cur.size() == 0)
        return (0);

    if (cur == S)
        return (1);

    // A 삭제
    if (cur[cur.size() - 1] == 'A') {
        string nxt = cur.substr(0, cur.size() - 1);
        if (bt(nxt))
            return (1);
    }

    // 뒤집고 B 삭제
    reverse(cur.begin(), cur.end());
    if (cur[cur.size() - 1] == 'B') {
        string nxt = cur.substr(0, cur.size() - 1);
        if (bt(nxt))
            return (1);
    }
    return (0);
}

int main(void)
{
    cin >> S >> T;
    cout << bt(T);
    return (0);
}