#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
int ans;
int board[23][23];
vector<int> tmp;
int blackGroupSize = 0;
vector<pair<int, int>> black[400];
vector<pair<int, int>> loc;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
bool visited[23][23];
queue<pair<int, int>> q;

void input(void) {
    cin >> N >> M;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            cin >> board[i][j]; 
        }
    }
}

void pushLoc(void) {
    for(int y = 0; y < N; y++) {
        for(int x = 0; x < M; x++) {
            if (board[y][x] != 0)
                continue;

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && board[ny][nx] == 2) {
                    loc.push_back({y, x});
                    break ;
                }
            }
        }
    }
}

void pushBlack(void) {

    int cnt = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {

            if (!visited[i][j] && board[i][j] == 2) {

                visited[i][j] = true;
                q.push({i, j});
                black[cnt].push_back({i, j});
                while (!q.empty()) {

                    int x = q.front().second;
                    int y = q.front().first;
                    q.pop();
                    
                    for(int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[ny][nx] && board[ny][nx] == 2) {
                            visited[ny][nx] = true;
                            q.push({ny, nx});
                            black[cnt].push_back({ny, nx});
                        }
                    }
                }
                cnt++;
            }
        }
    }
    blackGroupSize = cnt;
}

int checkBlackGroup(int i) {

    for(int j = 0; j < black[i].size(); j++) {
        int x = black[i][j].second;
        int y = black[i][j].first;

        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {

                if (board[ny][nx] == 0)
                    return (0);
            }
        }
    }
    return (black[i].size());
}

void check(void) {

    // 보드 만들기
    for(int i = 0; i < tmp.size(); i++) {
        int x = loc[tmp[i]].second;
        int y = loc[tmp[i]].first;

        board[y][x] = 1;
    }

    // 확인
    int cnt = 0;
    for(int i = 0; i < blackGroupSize; i++) {
        cnt += checkBlackGroup(i);
    }
    ans = max(ans, cnt);

    // 되돌리기
    for(int i = 0; i < tmp.size(); i++) {
        int x = loc[tmp[i]].second;
        int y = loc[tmp[i]].first;

        board[y][x] = 0;
    }
}

void select2Loc(int idx) {

    if (tmp.size() == 2) {     
        check();
        return ;
    }

    for(int i = idx; i < loc.size(); i++) {

        tmp.push_back(i);
        select2Loc(i + 1);
        tmp.pop_back();
    }
}

// 예외 케이스 : 돌을 하나 놓는 경우
void select1Loc(void) {
    if (loc.size() == 1) {
        board[loc[0].first][loc[0].second] = 1;

        int cnt = 0;
        for(int i = 0; i < blackGroupSize; i++) {
            cnt += checkBlackGroup(i);
        }
        ans = max(ans, cnt);
    }
}

int main(void)
{
    input();
    pushLoc();
    pushBlack();
    select2Loc(0);
    select1Loc();
    cout << ans;
    return (0);
}