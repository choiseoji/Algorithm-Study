#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

int solution(vector<int> citations) {
    int answer = 0;
    
    sort(citations.begin(), citations.end());
    for(int i = 0; i < citations.size(); i++)
    {
        int s = citations.size() - i;
        if (citations[i] >= s)
        {
            answer = s;
            break ;
        }
    }
    return answer;
}