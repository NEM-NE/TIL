함수형 프로그래밍
========

### 프로그래밍 패러다임이란?

    함수형 프로그래밍은 선언형 프로그래밍 패러다임 중 하나이다.

> 그렇다면 선언형 프로그래밍 패러다임은 무엇일까?

프로그래밍 패러다임이란 개발자에게 프로그래밍 관점을 주고 결정하는 기준을 준다.

프로그래밍 패러다임에는 명령형, 선언형으로 나뉜다.

명령형

* 객체지향, 절차지향의 뿌리
* 어떻게 할 것인가의 How 중심

선언형

* 함수형 프로그래밍의 뿌리
* 무엇을 할 것인가의 What 중심 

### 함수형 프로그래밍이란?


    함수형 프로그래밍은 거의 모든 것을 순수함수로 나눠서 해결하고
    작은 문제를 해결하기 위한 함수 작성을 하며
    대입문이 없는 프로그래밍이다.

함수형 프로그래밍의 특징 => 부수효과(SideEffect)가 없는 순수 함수를 1급 객체로 간주하여 매개변수로 넘기거나 반환값을 사용하여 참조투명성을 지킨다.

여기서 나오는 부수효과, 순수 함수, 1급객체, 참조투명성은 다음과 같다.

* 부수효과 => 변수의 값이 변경됨, 자료구조를 제자리에서 수정, 객체의 필드값을 설정..
* 순수함수 => Thread Safe, 함수 실행이 외부에 영향 X, Memory나 I/O에 Side Effect가 없는 함수
* 1급 객체 => JS에서 함수는 1급 객체이기 때문에 함수를 변수에 할당이 가능, 매개변수로 함수 전달 가능, 반환값으로 함수 전달 가능하다.
* 참조 투명성 => Immutable Data로 Side Effect X, 동일한 인자는 동일한 결과 값을 반환.

> 즉, 함수가 1급 객체이기 때문에 순수 함수를 다양하게 사용할 수 있고 참조 투명성을 통해 순수 함수를 만들 수 있으며 순수함수를 통해 
> Side Effect가 발생하지 않아 함수형 프로그래밍을 할 수 있다. 

### 클로저

말로 설명하기보다는 예시로 보는 게 이해가 편해 예시로 작성했다.

```js

function A(){
    const name = 'sungbin';
    
    return function(){
        console.log(name);
    }
}

const callName = A();

callName(); //  result : sungbin;

```

callName 변수는 A 함수를 호출해서 리턴값으로 익명함수를 받았다.

그리고 그 익명함수를 호출하면 이미 호출하고 리턴값까지 반환한 함수에 있었던 변수가 나온다.

이와 같이 외부에 있던 변수가 저장되는 것을 클로저라고 한다.

클로저 장점

* 데이터 보존 => 외부 함수 내 변수를 저장하기 때문에 캡슐화가 가능하다.
* 모듈화 유리 => 하나의 독립적인 값을 저장하기 때문에 재사용성 극대화

### 커링

    Currying은 여러 개의 인자를 가진 함수를 호출 할 경우, 파라미터의 수보다 적은 수의 파라미터를 인자로 받으면 누락된 파라미터를 인자로 받는 기법을 말한다.

커링 특징

* 함수 재사용성이 뛰어나다.
* 가독성을 높여준다.

위 특징들에 의해 함수형 프로그래밍에서 커링을 쓰기 적합하다.

```js

const greetCurring = (greeting) => {
    return function (name) {
        return console.log(greeting + " " + name);
    }
}

const sayHello = greetCurring("Hello");

const sayHelloToSungBin = sayHello('sungbin');
const sayHelloToBeenZino = sayHello('beenzino');

```

## 스스로 확인할 사항

### 함수와 클로저 closure에 대한 정의를 비교해보고, 클로저 선언이나 축약 표현에 대해 학습하고 정리한다. => 개인 학습 정리 참고

### 본인 코드에 순수함수 pure function와 그렇지 않은 함수를 분류하고 정리한다.

    순수함수 => Thread Safe, 함수 실행이 외부에 영향 X, Memory나 I/O에 Side Effect가 없는 함수

```js

export class Bishop extends Piece{
    constructor(type, pos){
        super(type, pos);
        this.result = [];
    }

    getDirectionCase(map, x, y){
        let curX = this.pos.rank;
        let curY = this.pos.file;

        //우상단 방향으로 갈 경우    x+ , y-
        while(true){
            curX = curX + x;
            curY = curY + y;

            //체스판에 벗어난 경우
            if(curX  < 1 || curX > 8 || curY < 1 || curY > 9) break;
            //가려는 경로에 누군가가 있는 경우
            if(map[curY][curX] !== '.') break;

            this.result.push({x : curX, y : curY});
        }
    }

```

getDirectionCase() 함수를 실행하면 result 변수에 영향이 간다.


```js

const factors = (number) => {
    const factorSet = new Set();
    
    Array.from(new Array(number * number), (x,i) => i+1)
            .filter(pod => isFactor(number, pod))
                .map((pod) => {
                    factorSet.add(pod);
                    factorSet.add(number / pod);
                })
    
    return factorSet;
}

```

고차함수로 구현 & 함수 내부 변수 & const로 변경 X => 순수 함수 


### 본인이 사용하지 않은 고차 함수 higher-order function에 대해 (적어도 3개 이상) 추가적으로 학습하고 예제 코드를 만들고 정리한다.

1. Array.prototype.some()

Array 중 하나라도 조건에 맞으면 True를 반환 아니면 False를 반환한다.

```js

const ary = [4, 2, 1, 3, 9, 7, 6, 8, 5]

ary.some((element) => element > 6);

//expected Result => true;

```

2. Array.prototype.sort(compareFn?: (a: T, b: T) => number)

Array를 compareFn에 맞게 정렬한다.

```js

const ary = [4, 2, 1, 3, 9, 7, 6, 8, 5]

ary.sort((a, b) => a - b);

//expected Result => [1, 2, 3, 4, 5, 6, 7, 8, 9];

```

3. Array.prototype.find()

Array에서 처음으로 조건에 맞는 요소를 반환한다.

```js

const ary = [4, 2, 1, 3, 9, 7, 6, 8, 5]

ary.find((element) => element > 5);

//expected Result => 9;

```

4. Array.prototype.every()

Array에서 모든 요소가 조건에 만족할 시 True반환

```js

const ary = [4, 2, 1, 3, 9, 7, 6, 8, 5]

ary.find((element) => element < 10);

//expected Result => true;

```

## 다 같이 확인할 사항

### 객체지향 코드가 읽기 편한가, 함수형 코드가 읽기 편한가 서로의 코드를 보고 자신의 기준에서 토론한다.

객체지향이 편하다고 느낀다.

함수형 프로그래밍은 코드의 재사용성 가독성이 뛰어나지만 고차함수의 함축적인 내용으로 뜻을 정확하게 이해하는데는 오래걸린다.

물론 함수형 프로그래밍에 익숙해지면 효율적이라고 느끼겠지만 익숙하지 않다면 객체지향이 더 편할거라고 생각한다.

### 객체지향 패러다임과 함수형 패러다임 공통점과 차이점을 토론한다.

공통점

1. 패러다임으로서 프로그래밍 개발에 관점을 부여하고 결정하는데에 기준을 잡아준다.
2. 읽기 쉬운 코드를 지향한다.

차이점

1. 함수형 프로그래밍은 How를 중시하는 선언형, 객체지향은 What을 중시하는 명령형 패러다임이다.
2. 함수형은 immutable data를 필수로 하고 객체지향은 변수가 바뀔 수 있다.
3. Side Effect 발생 유무