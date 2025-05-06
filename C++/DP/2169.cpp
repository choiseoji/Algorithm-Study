#include <iostream>
using namespace std;

int N, M;
int ground[1002][1002];
int dp[1002][1002];

void input(void) {
    cin >> N >> M;
    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= M; j++) {
            cin >> ground[i][j];
        }
    }
}

void solve(void) {
    // 첫번째 줄 초기화
    dp[1][1] = ground[1][1];
    for(int i = 2; i <= M; i++) {
        dp[1][i] = dp[1][i - 1] + ground[1][i];
    }

    // solve
    for(int i = 2; i <= N; i++) {
        
        // 위에서 온거 VS 왼쪽에서 온거
        int left[1002] = {0, };
        left[1] = dp[i - 1][1] + ground[i][1];
        for(int j = 2; j <= M; j++) {
            left[j] = max(left[j - 1], dp[i - 1][j]) + ground[i][j];
        }

        // 위에서 온거 VS 오른쪽에서 온거
        int right[1002] = {0, };
        right[M] = dp[i - 1][M] + ground[i][M];
        for(int j = M - 1; j >= 1; j--) {
            right[j] = max(right[j + 1], dp[i - 1][j]) + ground[i][j];
        }

        // dp 배열 갱신
        for(int j = 1; j <= M; j++) {
            dp[i][j] = max(left[j], right[j]);
        }

    }
    cout << dp[N][M];
}

int main(void) {
    input();
    solve();
    return (0);
}