#include <iostream>
#include <vector>
using namespace std;

int main(void)
{
    int N, T;
    int arr[101];

    cin >> N;
    for(int i = 1; i <= N; i++) {
        cin >> arr[i];
    }

    cin >> T;
    while (T--) {
        int a, b;

        cin >> a >> b;
        if (a == 1) {
            for(int i = 1; i <= N; i++) {
                if (i % b == 0)
                    arr[i] = arr[i] ^ 1;
            }
        }
        if (a == 2) {
            int left = b;
            int right = b;

            while (left >= 1 && right <= N) {
                if (arr[left] != arr[right])
                    break ;
                
                left--;
                right++;
            }
            for(int i = left + 1; i < right; i++) {
                arr[i] = arr[i] ^ 1;
            }
        }
    }

    // output
    for(int i = 1; i <= N; i++) {

        cout << arr[i];
        if (i % 20 == 0)
            cout << "\n";
        else
            cout << " ";
    }
    return (0);
}