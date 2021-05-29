BinarySearch...!
=======================

## 1. 이분 탐색이란?
<br>

> 최적의 답을 찾을 때까지 구간을 두 곳으로 쪼깨어 탐색하는 방식입니다. 

<br>

## 2. 언제 사용해야되나?
<br>

    완전 탐색으로는 시간복잡도에 의해 오래 걸릴 때 사용하면 좋습니다.
    '이분' 탐색이므로 문제가 2개로 지속적으로 분할되는 경우에도 사용해도 좋습니다.

<Br>

## 3. 사용 방법
<br>

1. 범위를 찾아 최솟값, 최대값을 변수로 선언합니다.
2. 최솟값과 최대값에 따른 중간 값을 구해줍니다.
3. 해당 중간값에 따른 값이 최적의 정답과 얼마나 차이나는지에 따라 범위를 재조정한다.
4. 2번과 3번을 최적의 정답을 찾을 때까지 반복해준다.

<br>

## 4. 효율성
<br>

    모든 범위를 다 탐색해야하는 완전탐색에 비해 구간을
    나누어 중간값에 대해 찾다 보니 시간 복잡도는 O(N * log N)이다.

<br>

## 5. 예시
<br>

- 백준 나무자르기 : [Link](https://www.acmicpc.net/problem/2805)

``` java
package BaekJoon;

import java.io.*;
import java.util.*;

public class Ex2805 {
	// 10 : 20
	
	public static void main(String[] args) throws IOException {
        // 데이터 입력 생략
		
        // 최대, 최솟값 선언
		int max = 1000000000;
		int min = 0;

		int ans = 0;
		while((min+1) < max) {  //값이 서로 같거나 최소가 최대를 넘어설 때 멈추도록한다.
            // 중간값을 구한다.
			int mid = (max + min) / 2;
			
            // 중간값이 최적의 값을 구할 수 있는지 확인한다.
			long sum = 0;
			for(int i = 0; i < n; i++) {
				if(trees[i] > mid) sum += trees[i] - mid;
			}
			
			if(sum == m) {
				ans = mid;
				break;
			}
			
            // 결과에 따라 최소 최대 값을 수정한다. 단, 문제의 조건에 따라 적절히 수정해야한다.
			if(sum > m) {
				min = mid;
				ans = mid;
			}else {
				max = mid;
			}
		}
		sb.append(ans);
		System.out.println(sb);
		
	}

}

```

<br>

## 6. 기타
<br>

    이분탐색은 문제를 '분할'하고 '병합'하는 과정을 거친다.
    즉, 이분탐색은 분할정복에서 파생된 알고리즘이다.
    분할정복을 사용하는 알고리즘은 이분탐색, 병합정렬등이 있다.

<br>

## 7. 참고
<br>

<br>

참조
1. ries, binary search 이분 탐색 : [Link](https://blog.naver.com/kks227/220777333252)