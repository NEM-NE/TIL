Prototype
===

## 목차

1. <b>Prototype이란?</b>
2. <b>Prototype.prototype vs Object.__proto__</b>
3. <b>prototype chain</b>
3. <b>prototype의 진짜 의미</b>

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

<br>

## prototype chain

kim 객체를 생성했지만 eyes 속성을 찾을 수 없을 것이다. 이를 찾기 위해 __proto__를 통해 부모 Prototype 객체를 찾아 해당 속성이 있는지 확인한다.
만약 최상위 부모인 Object Prototype 객체까지 탐색하고 해당 속성이 없다면 undefined를 반환한다.

<br>

## Prototype의 진짜 의미

[자바스크립트는 왜 프로토타입을 선택했을까](https://medium.com/@limsungmook/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%8A%94-%EC%99%9C-%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85%EC%9D%84-%EC%84%A0%ED%83%9D%ED%96%88%EC%9D%84%EA%B9%8C-997f985adb42)를 기반으로 작성한 내용입니다.

우리가 흔히 익숙한 객체지향은 "실재하는 사물이 있다면 반드시 본질은 존재한다"라는 플라톤의 주장을 기반으로 "개체의 속성이 동일한 경우 개체 그룹이 같은 범주에 속한다. 범주는 정의와 구별의 합이다."라는 Classification을 정의하게 되고 이를 기반으로 유사한 객체들은 클래스로 추상화됩니다.

반면에 비트켄슈타인은 "표현은 삶의 흐름 속에서만 의미를 갖는다." 즉, 진정한 뜻은 상황, 맥락, 주체에 의해 결정되는 의미사용이론을 주장합니다. Ex) 너 진짜 잘한다~ / 대상에게 비아냥 거리는 상황 / 대상에게 칭찬하는 상황

이후 등장한 Rosch의 `프로토타입 이론은 범주화를 실존하는 가장 좋은 예시를 기준`으로 한다고 정의하며, 또한 여기서 `가장 좋은 예시는 의미사용이론이 주장하는 바와 같이 상황, 맥락, 주체에 의해 결정`됩니다.

```js
var 스프 = 기본 스프;
function 스프넣기() {
    console.log(this.스프 + ' 투하!');
}

function 신라면() {
    this.스프 = 신라면 스프;
    this.건더기 = 신라면 건더기;
    this.면굵기 = 5;
    this.스프넣기 = 스프넣기;
}

const 신라면1호 = new 신라면();

function 참깨라면() {
    this.계란건더기 = true;
}

참깨라면.prototype = 신라면1호;

const 참깨라면1호 = new 참깨라면();
참깨라면1호.스프 = 참깨라면 스프;
참깨라면1호.건더기 = 참깨라면 건더기;

console.log(참깨라면1호.스프 === 신라면1호.스프) // false;
console.log(참깨라면1호.면굵기 === 신라면1호.면굵기) // true;

스프넣기(); // 기본 스프 투하!
신라면1호.스프넣기(); // 신라면 스프 투하!
참깨라면1호.스프넣기(); // 참깨라면 스프 투하!

```

실제로 참깨라면의 prototype은 기존에 존재하는 신라면1호를 기반으로 설정되었으며 별도로 변경을 해줬다. // 프로토타입 이론은 범주화를 실존하는 가장 좋은 예시를 기준으로 한다.

또한 아래 그 다음 예시처럼 주체가 누구냐에 따라 문맥이 바뀌므로 결과 역시 바뀐다.