Dynamic Programming...!
=======================

## 1. 다이나믹 프로그래밍이란?
<br>

> 하나의 문제를 단 한번만 풀도록 하는 알고리즘 <br>
> 즉, 한번 풀었던 문제를 다시 푸는 비효율성을 개선시킨 알고리즘

<br>

### 핵심은 규칙을 찾아 **점화식**을 구하는 것이다.

<br>

## 2. 언제 사용해야되나?
<br>

    1. 큰 문제를 작은 문제로 나눌 수 있을 때
    2. 작은 문제에서 구한 정답은 나중에 다시 사용! **메모제이션 이용**
    3. 같은 작은 문제는 정답이 항상 같다.
    
> 가장 먼저 그리디, 구현, 완전 탐색 등의 아이디어로 해결할 수 있는지 확인 (생각이 안나면 다이나믹 프로그래밍으로...) <br>
> 재귀 함수로 비효율적인 완전 탐색 코드로 짠 뒤에 작은 문제에서 규칙이 있으면 코드를 개선하는 방식으로 풀 수 있음 (하향식)

<Br>

## 3. 사용 방법
<br>

> 모든 작은 문제들은 한번만 풀어야된다. 
> <br>메모제이션을 사용하여 따로 작은 문제들의 정답을 가지고 있는다.

1. 메모제이션
2. 상향식 계산법 => 작은 문제부터 차근차근 풀기 (보통은 반복문을 사용하여 늘려나감)
3. 하향식 계산법 => 재귀함수, 큰 문제를 풀 때 작은함수가 풀리기 전까지 풀리지 않음.

<br>

## 4. 효율성
<br>

    피보나치 수열을 예시로 들면 O(2^n)이 O(n)으로 바뀜

<br>

## 5. 예시
<br>

1. 피보나치 수열 : [Link](https://galid1.tistory.com/507)

``` java
import java.util.Scanner;

public class fibonacci {
    static int[] dp;

    // recurison
    static int fibo(int x){
        if(x <= 1) return 1;
        return fibo(x-1) + fibo(x-2);
    }

    //top-down
    static int fiboDP(int x){
        if(x <= 1) {
            dp[1] = 1;   
            return dp[1];
        }else if(dp[x] != 0){
            return dp[x];
        }else {
            return dp[x] = fiboDP(x-1) + fiboDP(x-2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tc = scanner.nextInt();
        dp = new int[tc+1];

        fibo(tc);

        fiboDP(tc);

        // bottom-up
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= tc; i++){
            dp[i] = dp[i-2] + dp[i-1];
        }
    }
}

```

<br>

## 6. 기타
<br>

    분할 정복과 다른 이유 : 작은 문제들의 정답이 항상 같다.

<br>

## 7. 참고
<br>

<br>

참조
1. 전반적인 부분을 다룸 : [Link](https://galid1.tistory.com/507)