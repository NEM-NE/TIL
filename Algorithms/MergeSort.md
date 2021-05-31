Merge Sort...!
=======================

## 1. 병합 정렬이란?
<br>

> 정렬하고자 하는 배열을 계속 2개로 나누고 나눈 2개의 배열을 서로 비교하면서 정렬하는 알고리즘이다. 또한 분할, 정복, 결합 기법을 사용하기 때문에 분할정복 알고리즘에서 파생된 정렬 알고리즘이다.

<br>

## 2. 언제 사용해야되나?
<br>

    병합 정렬알고리즘은 `안정한 정렬`인데 최선의 경우든 최악의 경우든 
    모든 경우에서 시간복잡도가 O(N * log N)으로 동일하기 때문이다. 따라서 불규착한 상황에서 정렬을 해야할 때에 사용해야한다. 

<Br>

## 3. 사용 방법

<br>

![정복](./img/merge.png)

<br>

1. 범위를 찾아 최솟값, 최대값을 구합니다.
2. 재귀함수 식으로 (최솟값, 중간값), (중간값 + 1, 최대값) 2개로 나눕니다.(여기서 최솟값이 최대값과 같기 전까지 해주면 됩니다.)
3. 결합 할 때에는 임의의 배열을 만들어 나눈 2개의 배열들을 비교하면서 임의의 배열 안에 넣어주면 됩니다.



<br>

## 4. 효율성
<br>

    이분탐색과 비슷하게 2개로 나누어 분할하고 정복하고 결합을 해서
    시간 복잡도는 이분탐색과 같은 O(N * log N)이다.

<br>

## 5. 예시
<br>

``` java
package BaekJoon;

import java.io.*;
import java.util.*;

public class Test {
	// 2 : 01

	static void sort(int[] ary, int start, int mid, int end) {
		int left = start;
		int right = mid+1;
        // 인덱스 값을 start로 함. 가장 최솟값이 start이기 때문이다.
		int index = start;
		int[] temp = new int[ary.length];
		
		while(left <= mid && right <= end) {
			if(ary[left] > ary[right]) {
				temp[index++] = ary[right++];
			}else temp[index++] = ary[left++];
		}
		
		while(left <= mid) temp[index++] = ary[left++];
		while(right <= mid) temp[index++] = ary[right++];
		
        // 값을 채워주는 과정에서도 인덱스 값을 start로 함.
		for(int i = start; i < index; i++)
			ary[i] = temp[i];
	}
	
	static void mergeSort(int[] ary, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(ary, start, mid);
			mergeSort(ary, mid+1, end);
			sort(ary, start, mid, end);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int[] ary = {4, 77, 12, 34, 1, 67, 4, 20, 3, 2, 7, 5, 10};
		
		mergeSort(ary, 0, ary.length-1);
		
		for(int i = 0; i < ary.length; i++) {
			System.out.println(ary[i]);
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

참조
1. [알고리즘] 합병 정렬 : [Link](https://gmlwjd9405.github.io/2018/05/08/algorithm-merge-sort.html)