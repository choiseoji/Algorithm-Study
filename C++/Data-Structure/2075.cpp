#include <iostream>
#include <queue>
using namespace std;

int N;
priority_queue<int, vector<int>, greater<int>> pq;

int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> N;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            int n; cin >> n;

            if (pq.size() < N)
                pq.push(n);
            else if (pq.top() < n) {
                pq.pop();
                pq.push(n);
            }
        }
    }
    cout << pq.top();
    return (0);
}