#include <iostream>
#include <vector>
#include <map>
#include <sstream>
#include <string>
using namespace std;

int main(void)
{
    int N;
    int cnt = 0;
    string cmd;
    vector<string> ans;
    map<char, string> m;

    // input
    cin >> N;
    cin.ignore();  // 개행 버퍼 지우기!
    while (cnt < N && getline(cin, cmd))
    {
        // 일단 cmd를 공백 기분으로 자르기
        string token;
        vector<string> tmp;

        istringstream iss(cmd);
        while (getline(iss, token, ' '))
        {
            tmp.push_back(token);
        }

        // solve
        int flag = false;
        string newCmd = "";

        for(int i = 0; i < tmp.size(); i++)
        {
            if (flag == true || m[toupper(tmp[i][0])] != "")
                newCmd += tmp[i] + " ";
            else
            {
                flag = true;
                newCmd += "[" + string(1, tmp[i][0]) + "]" + tmp[i].substr(1) + " ";
                m[toupper(tmp[i][0])] = tmp[i];   // 단축키 등록!
            }
        }

        if (flag == false)
        {
            newCmd = "";
            for(int i = 0; i < cmd.length(); i++)
            {
                if (cmd[i] == ' ')
                {
                    newCmd += cmd[i];
                    continue;
                }
                if (flag == true || m[toupper(cmd[i])] != "")
                    newCmd += cmd[i];
                else
                {
                    newCmd += "[" + string(1, cmd[i]) + "]";
                    flag = true;
                    m[toupper(cmd[i])] = cmd;   // 단축키 등록!
                }
            }
        }
        ans.push_back(newCmd);
        cnt++;
    }

    // output
    for(int i = 0; i < ans.size(); i++)
    {
        cout << ans[i] << "\n";
    }
    return (0);
}