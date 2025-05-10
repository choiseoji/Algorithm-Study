#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int T;
int N, K;

int check(int visited[10], int ans)
{
    // visited 1 ~ 9에 숫자가 다 차있으면 ans와 sum 비교
    int sum = 0;
    for(int i = 1; i <= 9; i++)
    {
        if (visited[i] == 0)
            return (ans);
        sum += visited[i];
    }
    return (max(ans, sum));
}

int main(void)
{
    cin >> T;
    while (T--)
    {
        int ans = 0;
        vector<pair<int, int>> v;
        priority_queue<pair<int, int>> pq[10];
        int visited[10] = {0, };

        // input
        cin >> N >> K;
        for(int i = 0; i < N; i++)
        {
            int p, a; cin >> p >> a;
            v.push_back({p, a});

            if (i < K)
                pq[p].push({a, i});  // 능력, 인덱스
        }

        // solve
        // 1. 초기 visited 만들기
        for(int i = 1; i <= 9; i++)
        {
            if (!pq[i].empty())
                visited[i] = pq[i].top().first;
        }
        ans = check(visited, ans);

        // 2. 이제 오른쪽으로 한칸씩 이동해주기
        int left = 0;
        int right = K - 1;
        while (right < N - 1)
        {
            // 왼쪽에 있는거 정리
            int leftPosition = v[left].first;
            // 왼쪽으로 한칸 이동
            left++;

            // leftPosition 값 갱신 + 윈도우 밖으로 나간거 정리
            visited[leftPosition] = 0;
            while (!pq[leftPosition].empty())
            {
                int curPrice = pq[leftPosition].top().first;
                int index = pq[leftPosition].top().second;

                if (index >= left)
                {
                    visited[leftPosition] = curPrice;
                    break ;
                }
                else
                    pq[leftPosition].pop();
            }

            // 오른쪽 한칸 이동
            right++;
            // 오른쪽에 있는거 삽입
            int rightPosition = v[right].first;
            int rightPrice = v[right].second;
            pq[rightPosition].push({rightPrice, right});

            // rightPosition에 대해 윈도우 밖으로 나간거 정리
            while (!pq[rightPosition].empty())
            {
                int curPrice = pq[rightPosition].top().first;
                int index = pq[rightPosition].top().second;

                if (index >= left)
                {
                    visited[rightPosition] = curPrice;
                    break ;
                }
                else
                    pq[rightPosition].pop();
            }

            // check
            ans = check(visited, ans);
        }
        cout << ans << "\n";
    }
    return (0);
}


