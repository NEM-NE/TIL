Execution Context
===

## 목차

1. <b>실행컨텍스트란?</b>
2. <b>실행 컨텍스트 동작원리</b>
3. <b>스코프체인</b>
4. <b>클로져</b>

<br><br><br>

### 실행컨텍스트란?

    코드를 실행할 때 정상적으로 동작하기 위해 필요한 환경

실행할 수 있는 코드 => 해당 코드들을 기준으로 실행 컨텍스트를 생성한다.

* 전역 코드
* eval 코드
* 함수 코드
* 블록 코드

필요한 환경

* 변수
* 함수 선언
* 변수의 유효범위
* this

코드를 실행 할 때 호출 스택에 각각의 스택들이 올라가는데 이 때 올라가는 스택은 실행 컨텍스트가 되고, 코드를 처음 실행할 때 전역 실행 컨텍스트가 생성된다.

![ㅎㅎ](https://poiemaweb.com/img/excute_context_structure.png)

이전에는 위 그림과 같은 구조를 띄고 있었지만 새롭게 버전이 업데이트되면서 그에 대응하기 위해 기존 구조는 없애고 lexicalEnvironment등 다른 구조가 생성됨.

그래서 최종적으로 실행 컨텍스트는 다음과 같은 구조를 갖게 된다.


```js

    Execution Context = {
        lexicalEnvironment: [Lexical Environment],
        variableEnvironment: [Lexical Environment],
        thisbinding: [Object]
    }

lexicalEnvironment = {
    environmentRecord:-, // => Value Object와 같은 역할, Declartive, Object 2가지 타입으로 나뉨
    outerEnvironmentRef: 
}

declartiveEnvironmentRecord = { // => 직접 맵핑 
    x: 10,
    y: 5,
    name: 'sungbin'
}

objectEnvironmentRecord = {
    bindObject: globalObject    // 글로벌 객체를 맵핑
}

Object Environment Record는 Environment Record를 상속한 서브 클래스이며, 따라서 HasBinding 메서드를 구현하고 있다.
HasBinding 메서드는 내부적으로 HasProperty 함수를 호출하고, HasProperty 함수는 프로토타입 체인을 뒤져서 식별자를 찾아내다.

```

### 실행 컨텍스트 동작원리

실행 컨텍스트는 Memory Creation Phase, Code Execution Phase로 나뉜다.

예제 코드 

```js

function sum(x, y){
    console.log(barr);

    var result = x + y;
    var barr = function() {
        console.log('barr');
    }

    function printResult(){
        console.log(result);
    }

    return printResult;
}

var print = sum(10, 20)
print();

```


1. Global Execution Context 생성

```js

Global Execution Context = {
    lexicalEnvironment: globalEnv,
    variableEnvironment: {
        bindingObjec: window,
        outerEnvironmentRef: null,
    },
    thisbinding: window
}

```

2. thisBinding 결정

예제에서 호출되는 sum은 주체가 없기 때문에 thisBinding은 null로 할당된다. => null은 global, use strict에서는 null로 할당됨.

3. Lexical Environemnt 선언

```js

sumFC Execution Context = {
    lexicalEnvironment : { },
    variableEnvironment: ...,   // variableEnvironment은 값이 변하지 않고 lexicalEnvironment를 초기값으로 되돌릴 때 필요한 구조
    thisbinding: null
}

```

4. Lexical Environemnt 초기화

```js

sumFC Execution Context = {
    lexicalEnvironment = {
        environmentRecord: {
            x: 10,
            y: 20,
            printResult: function Ref, // 실행도 안했는데 함수가 할당됨 => 호이스팅 발생

            result: undefined, // => 이후 Execution Phase가 시작되면서 undefiend가 아닌 실제 값 할당
            barr: undefined
        },
        outerEnvironmentRef: Global.lexicalEnvironment  // 내 실행 컨텍스트 기준 1단계 상위 렉시컬 환경을 참조하고 있기 때문에 Scope Chain 가능
    },
    variableEnvironment: ...,
    thisbinding: null
}

```

### 스코프 체인

```js

sumFC Execution Context = {
    lexicalEnvironment = {
        environmentRecord: {
            x: 10,
            y: 20,
            printResult: function Ref, 

            result: undefined,
            barr: undefined
        },
        outerEnvironmentRef: Global.lexicalEnvironment  // 내 실행 컨텍스트 기준 1단계 상위 렉시컬 환경을 참조하고 있기 때문에 Scope Chain 가능
    },
    variableEnvironment: ...,
    thisbinding: null
}

```

### 클로져
 
    자유 변수를 갖는 코드 블럭

모든 함수는 [[scope]]를 가지고 있는데 [[scope]]는 함수가 선언된 스코프에 대한 lexicalEnvironment를 참조하고 있음.

이후 print()함수를 실행하면 해당 실행 컨텍스트의 lexicalEnvironment.outerEnvironmentRef은 [[scope]]가 참조한 lexicalEnvironment를 참조

```js 

printResult FC = {
    [[scopes]]: sumFC.lexicalEnvironment
}

printResultFC Execution Context = {
    lexicalEnvironment = {
        environmentRecord: {
            result: undefined,
        },
        outerEnvironmentRef: sumFC.lexicalEnvironment  // 내 실행 컨텍스트 선언된 기준 1단계 상위 렉시컬 환경을 참조하고 있기 때문에 Scope Chain 가능
    },
    thisbinding: null
}

```