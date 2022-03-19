# composite pattern

> 복합체 패턴(Composite Pattern)이란 개별 객체와 복합 객체를 모두 동일하게 다룰 수 있도록 하는 패턴

대표적인 예인 Component와 Container를 통해 살펴보면 다음과 같다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fff9ba6f-9e41-4e71-9d38-bab6dd61ae1e/Untitled.png)

Component 자식 클래스로는 Container, Label, Button, TextComponent 등이 있다. Container 이외 클래스들은 모두 1 가지의 역할을 하는 단일 클래스이지만 Container는 JFrame처럼 다양한 Component를 포함하고 있다.

```java
Button btn = new Button();
Label label = new Label();
Button btn2 = new Button();
Label label2 = new Label();
Container container = new Container();
ArrayList<Component> list = new ArrayList<Component>();

container.add(btn2);
container.add(label2);

list.add(btn);
list.add(label);
list.add(container);

for(Component c : list) {
	c.paint(); // 동일한 행동
}
```

그렇다면 위 코드처럼 이러한 단일 클래스와 복합 클래스인 Container를 동일하게 사용하고 싶다면 어떻게 해야할까?

이런 경우 사용하는 것이 `복합체 패턴(Composite Pattern)`이다.

복합체 패턴은 기본 클래스와 컨테이너 클래스를 모두 표현할 수 있는 추상화 클래스를 정의합니다.

아래 사진 같은 경우 기본 클래스가 할 수 있는 Draw()와 컨테이너 클래스가 할 수 있는 Add, Remove, Get을 정의합니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f029c900-f358-40b5-8653-143c2ca3a832/Untitled.png)

추가로 컨테이너 클래스에는 기본 클래스를 담을 수 있는 리스트를 가진다.

![스크린샷 2022-03-19 오후 11.20.47.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/379d6659-a807-4aa1-a663-6f276da054bf/스크린샷_2022-03-19_오후_11.20.47.png)

또한 Draw()메서드 역시 기본 클래스와는 다르게 컨테이너 클래스는 자신이 가지고 있는 기본 클래스를 Draw()할 수 있도록 코드를 작성한다.

```java
public class Button extends Component {
	public void paint(Graphics g) {
		// 그림 그리는 로직
	}
}

public class Container extends Component {
	ArrayList<Component> components = new ArrayList<Component>();
	...
	public void paint(Graphics g) {
		for(Component c : components){
				c.paint();// 자식 객체들에게 요청을 위임한다.
		}
	}

	...
}
```

이를 통해 발생하는 결과는 다음과 같다.

1. 기본 객체와 복합 객체로 구성된 하나의 일관된 클래스 계통을 정의한다.
2. 사용자의 코드가 단순해진다. (기본객체인지 복합객체인지 몰라도 된다.)
3. 새로운 종류의 구성요소를 쉽게 추가할 수 있다.
4. 설계가 지나치게 범용성을 많이 가진다. ⇒ 복합체의 구성요소에 제약을 가하기 힘들다.
