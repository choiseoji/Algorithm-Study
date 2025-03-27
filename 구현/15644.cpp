#include <iostream>
#include <queue>
using namespace std;

struct loc {
    int redX;
    int redY;
    int blueX;
    int blueY;
};

int N, M;
char board[15][15];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
char direction[4] = {'R', 'D', 'L', 'U'};
bool visited[15][15][15][15];
queue<pair<loc, pair<int, string>>> q;

void input(void) {
    struct loc initLoc;

    cin >> N >> M;
    for(int i = 0; i < N; i++){
        string str; cin >> str;

        for(int j = 0; j < M; j++) {
            board[i][j] = str[j];

            if (board[i][j] == 'R') {
                initLoc.redY = i;
                initLoc.redX = j;
            } else if (board[i][j] == 'B') {
                initLoc.blueY = i;
                initLoc.blueX = j;
            }
        }
    }
    
    q.push({initLoc, {0, ""}});
    visited[initLoc.redY][initLoc.redX][initLoc.blueY][initLoc.blueX] = true;
}

int count(int x, int y, int dir) {

    int cnt = 0;
    while (1) {

        if (board[y][x] == '#') {
            cnt -= 1;
            break ;
        }
        if (board[y][x] == 'O')
            return (-1);

        x += dx[dir];
        y += dy[dir];
        cnt++;
    }
    return (cnt);
}

void solve(void) {
    while (!q.empty()) {
        int redX = q.front().first.redX;
        int redY = q.front().first.redY;
        int blueX = q.front().first.blueX;
        int blueY = q.front().first.blueY;
        int cnt = q.front().second.first;
        string path = q.front().second.second;
        q.pop();

        if (cnt >= 10) {
            cout << "-1";
            return ;
        }

        if (board[redY][redX] == 'O') {
            cout << cnt << "\n" << path;
            return ;
        }

        for(int i = 0; i < 4; i++) {
            // 구슬 다음 자리 찾기
            int nrx = redX;
            int nry = redY;
            int nbx = blueX;
            int nby = blueY;

            // 구슬 움직이기 + 중간에 구멍에 빠지는지 확인
            int cnt1 = count(nrx, nry, i);
            int cnt2 = count(nbx, nby, i);

            if (cnt1 == -1 && cnt2 == -1)
                continue;
            if (cnt2 == -1)
                continue;
            if (cnt1 == -1) {
                cout << cnt + 1 << "\n" << path + direction[i];
                return ;
            }

            if (i == 0 || i == 2) {
                nrx += (cnt1 * dx[i]);
                nbx += (cnt2 * dx[i]);

                if (nry == nby && nrx == nbx) {
                    if (cnt1 > cnt2)
                        nrx -= dx[i];
                    else
                        nbx -= dx[i];
                }
            } else {
                nry += (cnt1 * dy[i]);
                nby += (cnt2 * dy[i]);

                if (nry == nby && nrx == nbx) {
                    if (cnt1 > cnt2)
                        nry -= dy[i];
                    else
                        nby -= dy[i];
                }
            }

            // 빨간 구슬 위치가 유효한지
            if (!(nrx >= 0 && nrx < M && nry >= 0 && nry < N && board[nry][nrx] != '#'))
                continue;
            // 파란 구슬 위치가 유효한지 (구멍에 들어가면 안됨)
            if (!(nbx >= 0 && nbx < M && nby >= 0 && nby < N && board[nby][nbx] != '#' && board[nby][nbx] != 'O'))
                continue;
            // 이미 방문했던 자리인지
            if (visited[nry][nrx][nby][nbx])
                continue;

            struct loc newLoc;
            newLoc.redY = nry;
            newLoc.redX = nrx;
            newLoc.blueY = nby;
            newLoc.blueX = nbx;
            q.push({newLoc, {cnt + 1, path + direction[i]}});
            visited[nry][nrx][nby][nbx] = true;
        }
    }
    cout << "-1";
}

// 빨간 구슬 탈출시키기
int main(void)
{
    input();
    solve();
    return (0);
}