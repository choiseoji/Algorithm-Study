#include <iostream>
# define MAX 0x3f3f3f3f
using namespace std;

int N;
int ans = MAX;
char inBoard[100005];
char board[100005];
char output[100005];

void input(void) {
    cin >> N;

    for(int i = 0; i < N; i++) {
        cin >> inBoard[i];
    }

    for(int i = 0; i < N; i++) {
        cin >> output[i];
    }
}

void putSwitch(int i) {
    if (board[i - 1] == '1')
        board [i - 1] = '0';
    else
        board[i - 1] = '1';

    if (board[i] == '1')
        board [i] = '0';
    else
        board[i] = '1';

    if (board[i + 1] == '1')
        board [i + 1] = '0';
    else
        board[i + 1] = '1';
}

// 만약 전 상태의 전구가 output과 다르다면 현재 전구를 눌러줌
void solve(int cnt) {
    for(int i = 1; i < N; i++) {
        if (board[i - 1] != output[i - 1]) {
            cnt++;
            putSwitch(i);
        }
    }

    if (board[N - 1] == output[N - 1])
        ans = min(ans, cnt);
}

void init(void) {
    for(int i = 0; i < N; i++) {
        board[i] = inBoard[i];
    }
}

int main(void) {
    input();

    // 첫번째 전구 안 누른 상태
    init();
    solve(0);

    // 첫번째 전구 누른 상태
    init();
    if (board[0] == '1') board[0] = '0';
    else board[0] = '1';
    if (board[1] == '1') board[1] = '0';
    else board[1] = '1';
    solve(1);

    if (ans == MAX)
        ans = -1;
    cout << ans;
    return (0);
}