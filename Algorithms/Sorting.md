Sorting...!
=======================

## 1. 정렬이란?
<br>

> 데이터를 특정한 기준에 따라 순서대로 나열하는 것 <br>
> 상황에 맞는 정렬 알고리즘을 사용하면 된다. (데이터가 대부분 정렬되었을 때, 범위가 좁을 때, 데이터가 적을 때...)

<br>

<br>

## 2. 언제 사용해야되나?
<br>

    당연한 말이겠지만 정렬이 필요할 때 사용!
	정렬을 하여 문제를 쉽게 접근할 수 있다면 사용해야한다.
    
> 다만 정렬 알고리즘은 시간복잡도가 상황에 따라 서로 다르기 때문에 유의하여 사용해야한다.

<br>

##### 해당 언어의 정렬 라이브러리는 오히려 기본 정렬알고리즘 보다 효율성이 뛰어나므로 숙지해야한다. 

<Br>

## 3. 사용 방법
<br>

### 삽입 정렬
<br>

![screenshot](./img/insertionSorting.png)
> 삽입 정렬은 특정 기준을 잡고 기준점 좌/우측으로 기준값보다 큰/작은 값을 비교 하여 바꾼다. 단, 없으면 멈추고 특정 기준을 다음 값으로 바꿔서 계속 진행한다.<br>

##### Ex) 오름차순 정렬 할 때 29를 기준으로 잡고 우측값고 비교하여 만약 기준 값이 크면 서로 바꾼 후 기준이 찾을 때까지 반복한다.

<br>

``` java
import java.io.*;
import java.util.*;

public class InsertionSort {
	public static void main(String[] args) throws IOException {		
        int[] ary = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 1; i < ary.length; i++) {
            for(int j = i; j > 0; j--){
                if(ary[j] < ary[j-1]){
                    int temp = ary[j];
                    ary[j] = ary[j-1];
                    ary[j-1] = temp;
                }else break;
            }
        }

        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
		
	}
}

```


<br>

### 특징

1. 최선의 경우 O(n)의 시간복잡도를 갖는다. (거의 정렬 되어 있는 상태)
2. 선택 정렬보다는 빠르다.

<br>
<br> <br>

### 선택 정렬
<br>

![s](./img/selection.png)
> 선택 정렬은 특정 기준을 잡고 기준점 우측으로 기준값보다 가장 큰/작은 값을 선정하여 서로 바꾸는 정렬 한다.<br>

##### Ex) 오름차순 정렬 할 때 29를 기준으로 잡고 우측으로 가장 작은 값을 찾는다. 이후 기준값과 위치를 바꾼다. 

<br>

```java
import java.io.*;
import java.util.*;

public class SelectionSort {
	
	public static void main(String[] args) throws IOException {		
        int[] ary = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 0; i < ary.length; i++) {
        	int min_index = i;
        	for(int j = i+1; j < ary.length; j++) {
        		if(ary[min_index] > ary[j]) {
        			min_index = j;
        		}
        	}
        	
        	int temp = ary[i];
        	ary[i] = ary[min_index];
        	ary[min_index] = temp;
        }
		
	}
}
```


<br>

### 특징

1. 직관적이다.
2. O(n^2)의 시간복잡도를 갖는다.
<br>
<br> <br>

### 퀵 정렬
<br>

![s](./img/quick.gif)
> 피봇을 설정하고 피봇을 제외한 배열에서 좌/우끝을 기준으로 잡고 서로 값을 비교하여 교환한다.<br>

##### Ex) 31를 피봇으로 잡고 좌측에서 피봇보다 값이 크면 & 우측에서 피봇보다 값이 작으면 서로 교환한다.

##### 추가로 좌/우 시작점이 커지다 서로 지나치게 되면 그 값중 하나를 피봇과 바꾼다.

<br>

```java
import java.io.*;
import java.util.*;

public class QuickSort {
	
	public static void quick(int[] ary, int start, int end) {
		if(start >= end) return;
		//generally pivot is first;
		int pivot = start;
		int left = start + 1;
		int right = end;
		
		while(left <= right) {
			//repeat if find data that larger than pivot
			while(left <= end && ary[left] <= ary[pivot]) left++;
			//repeat if find data that smaller than pivot
			while(right > start && ary[right] >= ary[pivot]) right--;
			
			//cross case
			if(left > right) {
				int temp = ary[pivot];
				ary[pivot] = ary[right];
				ary[right] = temp;
			}else {
				int temp = ary[left];
				ary[left] = ary[right];
				ary[right] = temp;
			}
			
			quick(ary, start, right - 1);
			quick(ary, right + 1, end);
		}
	}
	
	public static void main(String[] args) throws IOException {		
        int[] ary = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        quick(ary, 0, ary.length-1);

        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + ", ");
        }
	}
}
```

<br>

### 특징

1. 대부분 정렬에서 효율적으로 작용한다.
2. O(nlogn)의 시간복잡도를 갖는다.
3. 최악의 경우 O(n^2)의 시간복잡도를 갖는다. (이미 정렬된 경우)

<br>
<br> <br>

### 계수 정렬
<br>

![s](./img/counting.gif)
> 이미지 참고..ㅠ<br>

<br>

```java
import java.io.*;
import java.util.*;

public class CountingSort {
	public static void main(String[] args) throws IOException {		
        int[] ary = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};
        int[] cnt = new int[10];
        
        for(int i = 0; i < ary.length; i++) {
        	cnt[ary[i]]++;
        }

        for (int i = 0; i < cnt.length; i++) {
        	for(int j = 0; j < cnt[i]; j++) {
        		System.out.print(i + " ");
        	}
        }
		
	}
}
```


<br>

### 특징

1. 특정 조건이 부합해야지 사용할 수 있다. (범위가 최대 1000000)
2. O(n + k)의 시간복잡도를 갖는다.
3. 최악의 경우 0과 999,999 정렬 경우
4. 동일한 값을 가지는 데이터가 여러개 등장할 때 효과적이다.

<br>

## 4. 효율성
<br>

| 알고리즘 | 시간복잡도 | 특징 |
|---|:---:|:---:|
| `버블정렬` | O(n^2) | 일반적이면서도 가장 비효율인 정렬 |
| `선택정렬` | O(n^2) | 가장 일반적인 정렬 |
| `삽입정렬` | O(n^2) |  최선일 경우 O(n) |
| `퀵정렬` | O(nlogn) |  대부분 사용되는 정렬, 최악의 경우 O(n^2)|
| `계수정렬` | O(n + k) |  범위가 짧을 때 사용|
| `팀 정렬` | O(nlogn) |  최선일 경우 O(n), Collections.sort에서 사용|
| `병합정렬` | O(nlogn) |  항상 시간복잡도가 O(nlogn)로 같음|

<br>

## 5. 예시
<br>

    보통 정렬 문제는 다른 문제와 엮어서 나와서 정렬문제 자체는 없음...

<br>

## 6. 기타
<br>

## **Arrays.sort**와 **Collections.sort**
<br>

    실질적으로 정렬 문제에서 가장 많이 이용됨.
    compare 메서드로 논리적으로 구현 할 수 있음.
    Arrays.sort는 Dual-pivot qucik sort알고리즘을 사용하고 Collections.sort는 Tim sort(Merge sort)알고리즘을 사용.

<br>

```java
	public static void main(String[] args) {
		Integer[] ary = {1, 4, 6, 2, 7, 5, 9, 8};
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		Arrays.sort(ary, new Comparator<Integer>() {//배열도 int가 아닌 Integer여야 된다.

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		});

		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
	}
```

<br>

##### Tip : return e1 - e2는 오름차순 e2 - e1는 내림차순
<br>

## 7. 참고
<br>

<br>

참조
1. [이코테 정렬](https://freedeveloper.tistory.com/274)