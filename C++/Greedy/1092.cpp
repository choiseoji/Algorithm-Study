#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<int> crane;
vector<int> box;

bool compare(int s1, int s2)
{
    if (s1 > s2)
        return (true);
    return (false);
}

void input(void)
{
    int n;

    cin >> N;
    for(int i = 0; i < N; i++)
    {
        cin >> n;
        crane.push_back(n);
    }

    cin >> M;
    for(int i = 0; i < M; i++)
    {
        cin >> n;
        box.push_back(n);
    }

    sort(crane.begin(), crane.end(), compare);
    sort(box.begin(), box.end(), compare);
}

int solve(void)
{
    int T = 0;

    if (crane[0] < box[0])
        return (-1);
    
    while (!box.empty())
    {
        T++;
        for(int i = 0; i < crane.size(); i++)
        {
            for(int j = 0; j < box.size(); j++)
            {
                if (crane[i] >= box[j])
                {
                    box.erase(box.begin() + j);
                    break ;
                }
            }
        }
    }
    return (T);
}

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL); 

    input();
    cout << solve();
    return (0);
}