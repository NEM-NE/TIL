최장 증가 수열(DP) 알고리즘
=======================

## 1. 최장 증가 수열(LIS) 알고리즘이란?
<br>

> 임의의 수열이 주워졌을 때 해당 수열 중에 증가할 수 있는 수열 중 가장 긴 수열을 의미합니다. <br>
> 에를 들어 수열 {2, 1, 5, 4, 3, 7, 6, 8, 9}가 주워졌으면, 최장 증가 수열의 수는 5입니다.

<br>

<br>

## 2. 구현 원리

<br>

최장 증가 수열은 DP로 풀 수 있고 이분탐색으로도 풀 수 있습니다. DP로 푸는 최장 증가 수열은 DP에 각 위치마다 구할 수 있는 최장 증가 수열의 수를 구하고 메모제이션에 저장을 합니다.

이후 현재 인덱스에 있는 값과 메모제이션에 있는 이전 값 2개를 서로 비교하며 메모제이션에 새로 값을 추가합니다. 

즉, 만약에 현재 인덱스 값이 이전 인덱스 값보다 크면 최장 증가 수열이 될 수 있으므로 확인해야합니다.


<Br>

## 3. 구현

<Br>

```java
package BaekJoon;

import java.io.*;
import java.util.*;

public class Test {
    static int[] dp = new int[9];
    public static void main(String[] args) {
        int[] ary = new int[9];
        //input data
        ary[0] = 2;
        ary[1] = 1;
        ary[2] = 5;
        ary[3] = 4;
        ary[4] = 3;
        ary[5] = 7;
        ary[6] = 6;
        ary[7] = 8;
        ary[8] = 9;

        //LIS algorithms
        for(int i = 1; i < 9; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(ary[i] > ary[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //find max LIS num
        int max = 0;
        for(int i = 0; i < dp.length; i++){
            if(max < dp[i]) max = dp[i];
        }

        System.out.println(max);
    }
}

```

## 4. 효율성
<br>

      위 구현 식을 보면 알 수 있듯이 이중 for문을 가진다. 따라서 O(N^2)의 시간복잡도를 가진다.

<br>

## 5. 예시

백준 11053 [가장 긴 증가하는 부분 수열](https://www.acmicpc.net/problem/11053)

``` java
package BaekJoon;

import java.io.*;
import java.util.*;

public class Ex11053 {
	// 10 : 47
	
	static int[] dp = new int[1001];
	
	public static void main(String[] args) throws IOException {		
		Scanner scanner = new Scanner(System.in);

		int tc = scanner.nextInt();
		int[] array = new int[tc+1];
		
		for(int i = 1; i < tc+1; i++) {
			array[i] = scanner.nextInt();
		}
		
		for(int i = 1; i < tc+1; i++) {
			dp[i] = 1;
			for(int j = 1; j < i; j++) {
				if(array[j] < array[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		int max = 0;
		for(int i = 1; i < tc+1; i++) {
			if(max < dp[i]) max = dp[i];
		}
		
		System.out.println(max);
		
	}
	
}
```


