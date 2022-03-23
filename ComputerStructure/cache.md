# 캐시란?

하드웨어 기술의 발전으로 프로세서의 속도가 매우 빠르게 향상하고 있지만 메모리는 그 향상 속도가 더디고 있다.
실제로 메모리의 속도가 떨어지면 아무리 프로세서의 속도가 빠르더라도 크게 체감을 못할 수도 있다.
이를 개선하기 위해 캐시라는 메모리가 고안되었다.

캐시는 CPU안에 있는 작은 메모리이다. 메인 메모리에 접근해서 원하는 데이터를 얻기까지 시간이 많이 소요가 되기 때문에
자주 사용하는 데이터를 캐시에 담아두고 사용하게 하여 속도를 향상시킨다.

캐시는 L1, L2, L3 cahce로 나눌 수 있으며

L1는 프로세서와 가장 가까운 캐시 I$, D$로 나눈다.

L2는 가장 큰 캐시로 text영역을 저장할 때 쓴다.

L3는 text 영역 외 나머지를 저장할 때 쓴다.

![ㅑㅡㅎ](https://user-images.githubusercontent.com/6410412/54875045-80f32980-4e3a-11e9-8854-5cef63c9c58e.jpg)

실제로 CPU에는 위 사진 처럼 캐시가 있다.

캐시의 동작 방식

캐시는 `자주 사용하는 데이터`들을 저장한다.

위 기준은 시간지역성, 공간지역성으로 나눠 판단한다.

시간 지역성은 최근 접근한 데이터를 다시 접근하는 경향을 말하고
공간 지역성은 최근 접근한 데이터 주변에 접근하는 경향을 말한다.

또한 캐시에 있는 데이터를 다시 접근했을 때 hit라고 말하고 반대인 경우는 miss라고한다.

miss비율과 hit비율 조절해서 캐시의 성능을 최대로 올릴 수 있다.

또한 캐시는 주소가 키로 주어지기 때문에 Map처럼 바로 바로 접근할 수 있다.

물론 키 충돌을 방지하기 위한 여러 과정으로 조금 복잡해지지만 key로 바로 접근할 수 있기 때문에 시간복잡도는 매우 빠르다.

<br>

### LRU(Least-Recently-Used) 캐시 동작 방식에 대해 학습하고 본인이 설계한 방식을 정리한다.

<br>

LRU 캐시는 위에서 말한 시간지역성을 중요시하는 알고리즘을 바탕으로 한 캐시이다.

캐시는 작은 크기를 가지고 있기 때문에 어느순간 캐시의 용량이 다 찰 수 있다.

이 때 캐시에서 가장 오래있었던 데이터를 빼내고 새로운 데이터를 넣는다.

주로 Doubly LinkedList, Queue, Map등을 사용하여 구현한다.

<br>

### HashMap (또는 Dictionary)와 LRU 캐시 차이점에 대해 학습하고 정리한다.

<br>

공통점

HahMap과 LRU 캐시는 키를 인덱스로 한다.
키를 인덱스로 함으로써 시간복잡도가 단축된다.

차이점

LRU캐시는 용량이 다 차면 가장 오랫동안 접근을 안한 데이터를 삭제한다.

<br>

### 구현 코드

```js
module.exports = class Cahce {
  constructor() {
    this.max = 5;
    this.cahce = new Map(); // cahce역할
  }

  //$cahce를 위한 출력
  print() {
    this.cahce.forEach((val, key) => {
      console.log(`${key}(${val.hit})`);
    });
  }

  get(key) {
    return this.cahce.get(key);
  }

  set(key, val) {
    if (this.cahce.get(key)) {
      // 캐시안에 값이 있다면 일단 지워준다.
      let obj = this.cahce.get(key);

      this.cahce.delete(key);

      obj.hit++;

      this.cahce.set(key, obj);
    } else {
      if (this.cahce.size == this.max) {
        // 특정 크기 이상 초과 되면 가장 나중에 있는 것을 지운다.
        const key = this._first();
        this.cahce.delete(key);
      }
      const newObj = { key, val, hit: 1 };

      //hit와 cahce에 값을 넣어준다.
      this.cahce.set(key, newObj);
    }
  }

  _first() {
    return this.cahce.keys().next().value;
  }
};
```
