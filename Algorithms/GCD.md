Euclidean algorthims..!
=======================

## 1. 유클리드 알고리즘이란?
<br>

> 특정 두 수의 최대 공약수를 구하는 알고리즘 <br>
> 

<br>

<br>

## 2. 구현 원리

<br>

    임의의 수 x, y로 하고 x%y = r이라고 할 때, 
    x, y의 최대 공약수는 y, r의 최대 공약수와 같다.
    r이 0이 될 때까지 반복하면 그 때 `y가 x, y의 최대 공약수이다.


<Br>

## 3. 사용 방법


## 4. 효율성
<br>

     두 수 x, y 의 최대공약수를 구할 때(x>=y), 유클리드 호제법을 이용하면 시간복잡도 O(log2x)만에 구할 수 있다.

x%y = x/2
<br>
k번 연산만에 최대 공약수 z를 구한다고 가정하면 
<br>
n * (1/2)^(k/2) = z
<br>
k = 2log2(n/z)
<br>
 ==> O(log2x) z는 x에 비해 작은 수 이므로 무시

<br>

## 5. 예시

``` java
	static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
```

<br>

## 6. 기타
<br>

## 최소 공배수 구하기

    주어진 두 수 x, y와 최대 공약수를 알고 있으면 최소 공배수를 구할 수 있다.

    최소공배수 = (x*y)/최대 공약수 이다.

<br>

## 7. 참고
<br>

<br>

참조
1. [위키백과 유클리드 호제법](https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C_%ED%98%B8%EC%A0%9C%EB%B2%95)
2. [프로그래밍 학습 블로그, 알고리즘 - 최대공약수 구하기 ](https://ko.wikipedia.org/wiki/%EC%9C%A0%ED%81%B4%EB%A6%AC%EB%93%9C_%ED%98%B8%EC%A0%9C%EB%B2%95)
3. [유클리드-호제법-최대공약수-구하기weekly ps](https://www.weeklyps.com/entry/)
4. [onsil블로그](https://onsil-thegreenhouse.github.io/programming/algorithm/2018/04/01/gcd/)