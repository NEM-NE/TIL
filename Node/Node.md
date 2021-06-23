우리가 노드를 사용해야하는 이유
===

## Node란?

<br>

    Node.js는 Chrome V8 Javascript 엔진으로 빌드된 Javascript 런타임입니다.

<Br>

런타임(Runtime) : 특정 언어로 만든 프로그램들을 실행할 수 있는 환경s

<Br>

즉 `Node.js`는 브라우저가 아닌 다른 환경에서 Javascript를 사용할 수 있게 해준다.

<Br>

![이미지](https://grm-project-template-bucket.s3.ap-northeast-2.amazonaws.com/lesson/les_kVVqd_1533200523344/056fbc6066a6b4f3379325043c03eb39c44d77e90196df7900de98044c4708cc.png)

위 구조에서 <strong>libe 라이브러리가 노드의 핵심 특성인 이벤트 기반, 논 블로킹 I/O 모델을 구현하고 있다.</strong>

<br>

## 이벤트 기반

<br>

이벤트 : 어떠한 사건을 의미한다. (클릭했을 때, 스크롤 했을 때...)

노드는 이벤트 리스너를 생성하고 이벤트가 발생하면 그에 맞는 콜백함수를 호출하는 방식으로 작동한다.

따라서 이벤트가 발생하면 이벤트 리스너에 따라 콜백함수가 실행된다.
또한, 이벤트가 발생하기 전 까지 대기한다.

<br>

![이미지](https://cdn.filestackcontent.com/28uVaQ7sRq6LRmU89ptG)

제대로 된 이벤트의 처리방식을 알기 위해서는 이벤트 루프와 태스크 큐, 백그라운드의 개념을 이해해야한다.

이벤트 루프 : 이벤트 발생 시 호출할 콜백 함수 관리, 호출된 콜백 함수의 순서 관리

백그라운드 : 이벤트 리스너가 대기하는 장소. 여러 작업이 동시에 실행될 수 있음 (Timer, I/O, 이벤트 리스너)

태스크큐 : 백그라운드에서 나온 리스너가 실행되는 장소. 순서대로 실행됨. (특정한 경우에 순서가 바뀔 수도 있음)

<br>

## 논 블로킹 I/O

<br>

<br>

## 싱글 쓰레드

<Br>

## 서버로서의 노드

<br>