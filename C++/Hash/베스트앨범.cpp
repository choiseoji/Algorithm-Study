#include <string>
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <algorithm>
using namespace std;

struct Compare {
    bool operator()(pair<int, int>& a, pair<int, int>& b) {
        if (a.first == b.first)
            return a.second < b.second;
        return a.first > b.first;
    }
};

map<string, int> m;   // 장르의 인덱스 (v, pq)
vector<pair<int, int>> v;   // 총합, pq 인덱스
priority_queue<pair<int, int>, vector<pair<int, int>>, Compare> pq[101];   // 장르별 우선순위 큐 (plays, 고유번호)

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;

    int idx = 1; 
    v.push_back({-1, -1});  // 버리는 값
    for(int i = 0; i < genres.size(); i++)
    {
        string g = genres[i];
        int pqIndex = -1;
        if (m[g] == 0)  // 처음 나온 장르라면
        {
            m[g] = idx;
            pqIndex = idx;
            v.push_back({plays[i], idx});
            idx++;
        }
        else
        {
            v[m[g]].first += plays[i];  // 총합 갱신
            pqIndex = m[g];
        }
        
        // queue에 원소 2개만 넣어주도록 조정
        if (pq[pqIndex].size() >= 2)
        {
            int top = pq[pqIndex].top().first;
            if (plays[i] > top)
            {
                pq[pqIndex].pop();
                pq[pqIndex].push({plays[i], i});
            }
            else if (plays[i] == top)
            {
                int id = pq[pqIndex].top().second;
                if (id > i)
                {
                    pq[pqIndex].pop();
                    pq[pqIndex].push({plays[i], i});
                }
            }
        }
        else
            pq[pqIndex].push({plays[i], i});
    }

    sort(v.begin(), v.end(), greater<pair<int, int>>());
    for(int i = 0; i < v.size() - 1; i++)
    {
        int index = v[i].second;
        
        vector<int> tmp;
        for(int j = 0; j < 2; j++)
        {
            if (!pq[index].empty())
            {
                tmp.push_back(pq[index].top().second);
                pq[index].pop();
            } 
        }
        for(int j = tmp.size() - 1; j >= 0; j--)
        {
            answer.push_back(tmp[j]);
        }
    }
    
    return answer;
}