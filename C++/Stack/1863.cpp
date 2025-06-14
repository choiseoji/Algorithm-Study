#include <iostream>
#include <stack>
using namespace std;

// y 좌표 짝 찾는 느낌으로
int main(void)
{
    int N;
    int cnt = 0;
    stack<int> st;

    cin >> N;
    for(int i = 0; i < N; i++)
    {
        int x, y;

        cin >> x >> y;
        while (!st.empty() && st.top() > y)
        {
            st.pop();
            cnt++;
        }

        // 같은 건물
        if (!st.empty() && st.top() == y)
            continue;

        if (y > 0)
            st.push(y);
    }
    cnt += st.size();
    cout << cnt;
    return (0);
}