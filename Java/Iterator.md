Iterator
=======

<br>

## 서론

<br>

    백준에서 알고리즘 문제를 풀이 중 1406번을 링크드 라스트를 
    이용해서 풀었지만 시간초과라는 결과가 나오게 되었고 풀이를 
    보던중 Iterator을 이용한 풀이가 있어 이번기회에 
    Comparator처럼 공부해보려고 합니다.

##### 링크 : [1406번 풀이](https://minhamina.tistory.com/17)

<br>

## 왜 Iterator인가?

<br>

> Iterator이란 컬랙션프레임워크 내에 있는 다양한 종류의 클래스들을
> 일관성있게 읽을 수 있도록 도와주는, 즉 '읽기'를 표준화 한 
> 인터페이스입니다.

<br>

사실 Iterator는 객체를 만들어서 사용하기 때문에 컬랙션 프레임워크 안에 있는 메서드들 보다 처리속도가 늦을 수 있습니다.

##### 링크 : [size() vs Iterator](https://vaert.tistory.com/108)

<br>

그럼에도 불구하고 Iterator를 사용하는 이유는 '읽기'에 표준화 되어 있어 일관성 있는 코드를 작성할 수 있기 때문입니다. 즉, 어떤 컬랙션프레임워크 클래스가 올지 몰라도 Iterator 사용 하여 읽을 수 있습니다. 

<br>

## Iterator 내부 메서드

<br>

| 리턴 타입 | 메서드명 | 설명 |
|---|:---:|:---:|
| boolean | `hasNext()` | iteration이 다음 요소를 가지고 있으면(가져올 객체가 있다면) true 리턴, 없으면 false 리턴 |
| E | `next()` | 다음 요소 반환 |
| void | `remove()` | 마지막 반환된 요소를 제거 |

<br>

#### 추가 `ListIterator interface`

<br>

| 리턴 타입 | 메서드명 | 설명 |
|---|:---:|:---:|
| void | `add(E e)` | 해당 리스트에 전달된 요소 추가 |
| boolean | `hasNext()` | iteration이 다음 요소(순방향)를 가지고 있으면(가져올 객체가 있다면) true 리턴, 없으면 false 리턴 |
| boolean | `hasPrevious()` | iteration이 다음 요소(역방향)를 가지고 있으면(가져올 객체가 있다면) true 리턴, 없으면 false 리턴 |
| E | `next()` | 다음 요소 반환 |
| int | `nextIndex()` | 다음 요소의 인덱스 반환 |
| E | `previous()` | 이전 요소 반환 |
| int | `previousIndex()` | 이전 요소의 인덱스 반환 |
| void | `remove()` | 마지막 반환된 요소를 제거 |
| void | `set(E e)` | 마지막 반환된 요소를 전달된 객체로 대체 |

<br>

## 적용

<br>

아래 소스코드를 Iterator를 사용하여 수정해보자

```java

package BaekJoon;

import java.io.*;
import java.util.*;

public class Main {
	// 10 : 46
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		LinkedList<Character> list = new LinkedList<>();
		int tc = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		int size = list.size();
		int point = size;
		for(int i = 0; i < tc; i++) {
			String input = br.readLine();
	
			if(input.charAt(0) == 'P') {
				char a = input.charAt(2);
				list.add(point, a);
				point++;
				size++;
			}else if(input.charAt(0) == 'L') {
				if(point == 0) continue;
				point--;
			}else if(input.charAt(0) == 'B') {
				if(point == 0) continue;
				list.remove(point-1);
				point--;
				size--;
			}else if(input.charAt(0) == 'D') {
				if(point == size) continue;
				point++;
			}
		}
		
		
		String ans = "";
		for(int i = 0; i < list.size(); i++) {
			ans += list.get(i);
		}
		
		sb.append(ans);
		
		System.out.println(sb);
		
	}
}

```

    문제점 : 해당 코드에서 point라는 변수를 선언하여 일종의 커서 
    역할을 가졌으며, list객체의 remove, add메서드를 사용하여 값을 
    추가/삭제를 했습니다. 그러나 해당 메서드는 끝과 시작이 아닌 
    이상 인덱스까지의 '탐색'작업을 하기 때문에 시간복잡도가 O(1)이 
    아니게 되어버립니다. 따라서 시간복잡도가 O(1)이 나올 수 있게 
    ListIterator를 사용해야합니다.


개선 코드

```java
package BaekJoon;

import java.io.*;
import java.util.*;

public class Main {
	// 10 : 46
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		LinkedList<Character> list = new LinkedList<>();
		int tc = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		
		ListIterator it = list.listIterator();
		
		//처음 커서는 문장의 맨 뒤에 있어야하기 때문에 커서를 맨뒤로 이동시켜줌 (ex. abc|)
		while(it.hasNext()) {
			it.next();
		}
		
		for(int i = 0; i < tc; i++) {
			String input = br.readLine();
	
			if(input.charAt(0) == 'P') {
				char a = input.charAt(2);
				it.add(a);
			}else if(input.charAt(0) == 'L') {
				if(it.hasPrevious()) {
					it.previous();
				}
				
			}else if(input.charAt(0) == 'B') {
				if(it.hasPrevious()) {
					it.previous();
					it.remove();
				}

			}else if(input.charAt(0) == 'D') {
				if(it.hasNext()) it.next();
				
			}
		}
		

		for(Character c : list) {
			sb.append(c);
		}
		
		System.out.println(sb);
		
	}
}
```

##### 추가 : String 덧셈이 굉장히 비효율적인 듯 하다. [링크](https://codingdog.tistory.com/entry/java-string-%EC%97%B0%EC%82%B0-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%99%EC%9E%91%ED%95%98%EB%8A%94%EC%A7%80-%EC%95%8C%EC%95%84%EB%B4%85%EC%8B%9C%EB%8B%A4)