Prototype
===

## 목차

1. <b>Prototype이란?</b>
2. <b>Prototype.prototype vs Object.__proto__</b>
3. <b>prototype chain</b>

<br><br><br>

## Prototype이란?

    JS 내에 모든 객체는 자신을 정의하는 객체를 가지는데 이를 Prototype이라고 한다.

JS는 객체 지향 기반 언어가 아닌 Prototype 기반 언어이기 때문에 기존에 알던 Class, 상속 등의 객체지향 개념과는 상반되는 개념을 가지고 있다.

객체 지향 => 실재하는 사물은 본질이 존재한다라는 전제하에 개념 시작 

Prototype => 본질의 속성이 다른 사물이 존재할 수 있음 / **상황 문맥(Context)에 따라 사물의 의미가 달라짐** (문맥에 의해 this, closure, hoisting 파생) / 동일한 속성이 아니더래도 한 범주로 묶일 수 있음.

위 설명처럼 Prototype은 객체로 존재하는데 객체안에 공통적으로 사용되는 속성들을 정의하여 새로운 객체를 생성할 때 별도의 설정 없이 사용가능하다.

<br>

## Prototype.prototype vs Object.__proto__이란?

![ss](https://miro.medium.com/max/700/1*jMTxqTYDZGhykJQoimmb0A.png)

위 사진처럼 prototype 속성은 생성자 함수에 있으며 이 속성은 Prototype 객체를 가르킨다. 이를 통해 원형 추가/변경 가능 (Ex Person.prototype.eyes = 2;)
또한 Prototype 객체에는 consturctor 속성이 있는데 이 속성은 생성자 함수를 가르킨다.

__proto__속성은 객체가 생성될 때 조상이었던 함수의 Prototype 객체를 가르킨다.

## prototype chain

kim 객체를 생성했지만 eyes 속성을 찾을 수 없을 것이다. 이를 찾기 위해 __proto__를 통해 부모 Prototype 객체를 찾아 해당 속성이 있는지 확인한다.
만약 최상위 부모인 Object Prototype 객체까지 탐색하고 해당 속성이 없다면 undefined를 반환한다.