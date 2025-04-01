#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

int R, C;
string mov;
pair<int, int> loc;
char board[101][101];
vector<pair<int, int>> robot;
int dx[9] = {-1, 0, 1, -1, 0, 1, -1, 0 , 1};
int dy[9] = {1, 1, 1, 0, 0, 0, -1, -1, -1};

void input(void)
{
    cin >> R >> C;
    for(int i = 0; i < R; i++)
    {
        string str;

        cin >> str;
        for(int j = 0; j < C; j++)
        {
            board[i][j] = str[j];
            if (board[i][j] == 'I')
                loc = {i, j};
            if (board[i][j] == 'R')
                robot.push_back({i, j});
        }
    }
    cin >> mov;
}

int getRobotDir(pair<int, int> cur, int x, int y)
{
    int minCount = 0x3f3f3f3f;
    int minDir = -1;

    for(int i = 0; i < 9; i++)
    {
        if (i == 4)
            continue;

        int nextX = cur.second + dx[i];
        int nextY = cur.first + dy[i];

        if (nextX >= 0 && nextX < C && nextY >= 0 && nextY < R)
        {
            int count = abs(nextX - x) + abs(nextY - y);

            if (minCount > count)
            {
                minCount = count;
                minDir = i;
            }
        }
    }
    return (minDir);
}

void checkRobotIndex(void)
{
    for(int i = 0; i < robot.size(); i++)
    {
        int flag = false;
        int curX = robot[i].second;
        int curY = robot[i].first;

        for(int j = i + 1; j < robot.size(); j++)
        {
            if (curX == robot[j].second && curY == robot[j].first)
            {
                flag = true;
                // 현재 로봇 삭제해주고 인덱스 --
                robot.erase(robot.begin() + j);
                j--;
            }
        }
        if (flag == true)
        {
            // 현재 로봇 삭제해주고 인덱스 --
            robot.erase(robot.begin() + i);
            i--;
        }
    }
}

void output(void)
{
    char ans[101][101];

    ans[loc.first][loc.second] = 'I';
    for(int i = 0; i < robot.size(); i++)
    {
        ans[robot[i].first][robot[i].second] = 'R';
    }

    // 출력
    for(int i = 0; i < R; i++)
    {
        for(int j = 0; j < C; j++)
        {
            if (ans[i][j] != 'I' && ans[i][j] != 'R')
                cout << '.';
            else
                cout << ans[i][j];
        }
        cout << "\n";
    }
}

void solve(void)
{
    for(int i = 0; i < mov.size(); i++)
    {
        int dir = mov[i] - '0' - 1;

        // 1. 종수 자리 이동
        int nextX = loc.second + dx[dir];
        int nextY = loc.first + dy[dir];
        loc = {nextY, nextX};

        // 2. 종수가 이동한 자리에 아두이노가 있다면 종료
        if (find(robot.begin(), robot.end(), loc) != robot.end())
        {
            cout << "kraj " << i + 1 << "\n"; 
            return ;
        }

        // 3. 아두이노 이동
        for(int j = 0; j < robot.size(); j++)
        {
            int robotDir = getRobotDir(robot[j], nextX, nextY);
            int nextRobotX = robot[j].second + dx[robotDir];
            int nextRobotY = robot[j].first + dy[robotDir];
            robot[j] = {nextRobotY, nextRobotX};

            // 4. 종수와 아두이노의 위치가 같다면 종료
            if (nextRobotX == nextX && nextRobotY == nextY)
            {
                cout << "kraj " << i + 1 << "\n"; 
                return ;
            }
        }

        // 4. 아두이노가 서로 같은 자리에 있는지 확인
        checkRobotIndex();
    }
    output();
}

int main(void)
{
    input();
    solve();
    return (0);
}