#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct info {
    string type;
    long long attack;
    long long life;
};

long long N, H;
vector<info> v;

void input(void)
{
    cin >> N >> H;
    for(long long i = 0; i < N; i++)
    {
        long long t, a, h;
        cin >> t >> a >> h;

        string type;
        if (t == 1)
            type = "monster";
        else if (t == 2)
            type = "heal";
        info detail;
        detail.type = type;
        detail.attack = a;
        detail.life = h;
        v.push_back(detail);
    }
}

// 생명력, 공격력
bool solve(long long hp, long long at)
{
    long long curHp = hp;
    for(int i = 0; i < N; i++)
    {
        info detail = v[i];
        if (detail.type == "monster")
        {
            long long monsterAt = detail.attack;
            long long monsterHp = detail.life;
            long long cnt = 0;

            if (monsterHp % at == 0)
                cnt = monsterHp / at - 1;
            else
            cnt = monsterHp / at;

            curHp -= monsterAt * cnt;
            if (curHp <= 0)
                return (false);
        }
        else if (detail.type == "heal")
        {
            at += detail.attack;
            curHp = min(curHp + detail.life, hp);
        }
    }
    return (true);
}

// 공격력만큼 몬스터의 생명력 깎음 -> 몬스터 생명력 0 되면 전투 종료
// 용사의 생명력 0 되면 용사 사망
// 포션 마시면 생명력, 공격력 증가
int main(void)
{
    input();
    
    long long ans;
    long long left = 1;
    long long right = 1e18;
    while (left <= right)
    {
        // false 리턴하면 초기 hp 증가시켜야함
        // true 리턴하면 초기 hp 감소시켜도됨
        long long mid = (left + right) / 2;

        if (solve(mid, H))
        {
            ans = mid;
            right = mid - 1;
        }
        else
            left = mid + 1;
    }
    cout << ans;
    return (0);
}