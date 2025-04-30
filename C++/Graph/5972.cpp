#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
int dist[50001];
vector<pair<int, int>> v[50001];
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

void input(void)
{
    cin >> N >> M;
    for(int i = 0; i < M; i++)
    {
        int a, b, c;

        cin >> a >> b >> c;
        v[a].push_back({b, c});
        v[b].push_back({a, c});
    }
}

void init(void)
{
    dist[1] = 0;  // 시작
    for(int i = 2; i <= N; i++)
    {
        dist[i] = 0x3f3f3f3f;
    }
}

void solve(void)
{
    pq.push({0, 1});
    while (!pq.empty())
    {
        int curNode = pq.top().second;
        int curDist = pq.top().first;
        pq.pop();

        if (curDist > dist[curNode])
            continue;

        for(int i = 0; i < v[curNode].size(); i++)
        {
            int nextNode = v[curNode][i].first;
            int dst = v[curNode][i].second;

            // 새로 거리를 갱신한 노드만 pq에 넣어줌
            if (dist[nextNode] > curDist + dst)
            {
                dist[nextNode] = curDist + dst;
                pq.push({dist[nextNode], nextNode});
            }
        }
    }
    cout << dist[N];
}

int main(void)
{
    input();
    init();
    solve();
    return (0);
}