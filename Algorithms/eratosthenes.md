Euclidean algorthims..!
=======================

## 1. 유클리드 알고리즘이란?
<br>

> 소수 판별 알고리즘 <br>
>

<br>

<br>

## 2. 구현 원리

<br>

![에라토스테네스의 체](./img/eratos.gif)

    2부터 2를 제외하고 2의 배수들은 isPrime배열에 false값을 준다. 이후 그 다음 값은 이전 값에 1를 더한 3이고 만약 isPrime배열에 false값이 없을 때만 자신을 제외한 해당 배수들을 모두 false값을 넣어준다.


<Br>

## 3. 사용 방법


## 4. 효율성
<br>

    O(Nlog(logN))로 가장 빠르게 소수를 구하는 알고리즘이다.

<br>

## 5. 예시

``` java
import java.util.Scanner;

public class era {
    static boolean[] isPrime;
    static int[] ary;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tc = scanner.nextInt();
        ary = new int[tc+1];
        isPrime = new boolean[tc+1];

        for(int i = 1; i < tc; i++){
            ary[i] = i;
            isPrime[i] = true;
        }

        for(int i = 2; i < tc+1; i++){
            if(!isPrime[i]) continue;
            for(int j = i + i; j < tc+1; j += i){
                isPrime[i] = false;
            }
        }
    }
}
```

<br>

## 6. 기타
<br>

<br>

## 7. 참고
<br>

<br>
