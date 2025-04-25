#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int N;
int score[1001];
vector<pair<int, int>> v;

// 과제 점수 기준으로 내림차순 정렬
bool compare(pair<int, int> s1, pair<int, int> s2)
{
    if (s1.second > s2.second)
        return (true);
    return (false);
}

void input(void)
{
    cin >> N;
    for(int i = 0; i < N; i++)
    {
        int d, w;

        cin >> d >> w;
        v.push_back({d, w});
    }
    sort(v.begin(), v.end(), compare);
}

void solve(void)
{
    int sum = 0;

    for(int i = 0; i < N; i++)
    {
        // 과제 점수가 큰 과제부터 자리를 찾을건데
        for(int j = v[i].first; j > 0; j--)
        {
            // 마감 날짜 ~ 1 순회하며 빈자리에 과제 점수 저장
            // 마감 날짜와 가장 가까운 날에 저장할거임
            if (!score[j])
            {
                score[j] = v[i].second;
                sum += score[j];
                break ;
            }
        }
    }
    cout << sum;
}

int main(void)
{
    input();
    solve();
    return (0);
}