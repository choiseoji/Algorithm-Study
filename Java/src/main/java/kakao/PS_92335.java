package kakao;

public class PS_92335 {

    public int solution(int n, int k) {

        int answer = 0;

        String kNumber = Integer.toString(n, k);
        String[] arr = kNumber.split("0");

        for(String number : arr) {

            if (number.equals(""))
                continue;

            Long num = Long.parseLong(number);
            if (isPrime(num))
                answer++;
        }
        return answer;
    }

    // 예외 : 들어오는 수가 1 이하면 바로 false
    // 제곱근까지 포함해야 함
    public boolean isPrime(Long num) {

        if (num <= 1)
            return (false);

        for(int i = 2; i <= (int) Math.sqrt(num); i++) {

            if (num % i == 0)
                return (false);
        }
        return (true);
    }
}
