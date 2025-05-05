#include <iostream>
#include <cmath>
using namespace std;

int main(void)
{
    int N;
    int ans = 0;
    int alpha[26] = {0, };

    cin >> N;
    string firstWord; cin >> firstWord;
    for(int i = 0; i < firstWord.length(); i++)
    {
        alpha[firstWord[i] - 'A']++;
    }

    // solve
    N--;
    while (N--)
    {
        int count[26];
        copy(alpha, alpha + 26, count);

        int wrongCnt = 0;
        string str; cin >> str;
        for(int i = 0; i < str.length(); i++)
        {
            count[str[i] - 'A']--;
            if (count[str[i] - 'A'] < 0)
                wrongCnt++;
        }

        if (firstWord.length() == str.length() && wrongCnt <= 1)
            ans++;
        else if (firstWord.length() - str.length() == 1 && wrongCnt == 0)
            ans++;
        else if (str.length() - firstWord.length() == 1 && wrongCnt == 1)
            ans++;
    }
    cout << ans;
    return (0);
}