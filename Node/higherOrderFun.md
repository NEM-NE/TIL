자바스크립트의 고차함수
=====

## 사전 지식

### 함수형 프로그래밍

자바스크립트는 함수를 매개변수로 넣을 수 있고, 리턴값으로 함수를 받을 수 있기 때문에 함수형 프로그래밍이 가능하다.

### 자바스크립트 일급객체 

자바스크립트에서 함수는 일급객체이다.

함수는 객체이기 때문에 함수에 속성을 부여할 수도 있다.

또한 일급객체인 함수에서 매개변수로 넣을 수 있고 리턴 값으로 받을 수 있고 변수에 함수를 할당하는 것이 가능하다.

    따라서 자바스크립트에서 함수는 일급객체이기 때문에 함수형 프로그래밍이 가능하다.

## 고차 함수(higher Order Function)

    고차함수란? => 함수를 매개변수로 넣을 수 있고 리턴값을 받을 수 있는 함수를 말한다.

자바스크립트는 함수형 프로그램으로 구현 가능하기 때문에 고차함수를 생성할 수 있다.

### 자바스크립트에서 고차함수

자바스크립트의 대표적인 고차함수들은 대부분 Arrays에 내장된 함수들이다.

1. Arrays.map
2. Arrays.filter
3. Arrays.reduce
4. Arrays.find

Arrays.map(callback(element, idx, array)) => 기존 Arrays 배열에 있는 element를 이용해서 새로운 배열을 생성

```js

    const ary = [4, 3, 6, 10, 24, 1, 6];
    const result = ary.map((element) => element * element);

    //result = [16, 9, 36, 100, 576, 1, 36]

```

Arrays.filter(callback(element, idx, array)) => 기존 Arrays 배열에 있는 element를 조건을 걸어 참인 경우만 배열에 넣는다.

```js

    const ary = [4, 3, 6, 10, 24, 1, 6];
    const result = ary.filter((element, idx) => element <= idx);

    //result = [1, 6]

```

Arrays.reduce(callback(acc, ele, idx, ary)) => 누적 매개변수 acc가 있어 이전 결과 값들의 합을 가진다.

```js

    const ary = [4, 3, 6, 10, 24, 1, 6];
    const result = ary.reduce((acc, element, idx) => acc + element);

    //result = 54;

```

### 고차함수 reduce 구현

자바스크립트는 이미 구현된 고차함수를 사용할 수 있을 뿐만 아니라 직접 고차함수를 만들어 사용할 수 있다.

```js

        const ary = [4, 3, 6, 10, 24, 1, 6];

        const reduce = function(ary, cb){
            let acc = 0;
            for (const idx in ary) {
                acc = cb(acc, ary[idx], idx);
            }

            return acc;
        }

        const callback = (acc, element, idx) => acc + element;

        const ans = reduce(ary, callback);

```