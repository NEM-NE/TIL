우리가 express를 사용해야하는 이유
===

## Express란?

<br>

    Express는 웹 및 모바일 애플리케이션을 위한 일련의 강력한 기능을 제공하는 간결하고 유연한 Node.js 웹 애플리케이션 프레임워크입니다. (expressjs.com)

<Br>

다시 한번 나의 용어로 정의하면 Express는 라우팅과 미들웨어 기반인 경량형 Node.js 웹 프레임워크입니다.

만약 Node.js를 사용한다고 하면 대부분의 이유가 웹 서버를 구축하기 위해서 사용한다.
또한 Node.js로 웹서버를 구축한다고 하면 대부분 Express를 사용할 것이다.

<Br>

## 가벼운 Express

<Br>

Express의 가장 큰 이유는 가볍게 웹서버를 구현할 수 있다는 점이다.

실제로 구현 코드는 다음과 같다.

```js
// 기본 설정
const express = require('express');

const app = express();
const port = 3000;

app.get('/', (req, res) => {
    res.send('hello, world!');
})

app.listen(port, () => {
    console.log('서버가 연결되었습니다!');
})
 

```

위 코드는 서버를 작동시키고 첫 화면을 hello, world를 보여주는 식으로 동작한다.

기존의 다른 웹 프레임워크와는 다르게 Express는 아주 간단하게 구현 할 수 있다는 점이 사용해야할 큰 이유이다.

## 미들웨어

<Br>

    미들웨어는 운영체제와 응용프로그램 사이에서 실행되는 소프트웨어이다.
    하지만 Express에서는 조금 다른 의미로 사용되는데 바로 요청과 응답 사이에서 특정 목적을 위해 실행되는 메서드를 의미한다.

<Br>

일반적으로 다음과 같이 실행된다.


```js

// 기본 설정 생략

app.use(express.json());    // 1번
app.use('/', express.static(path.join(__dirname, 'public'))); // 2번
app.get('/', (req, res, next) => {  // 3번
    //대충 기능 처리
    next();
});

```

일반적으로 Express에서 미들웨어는 3번의 콜백 함수를 의미한다.

특정 기능 이후 next()를 이용하여 다음 미들웨어로 이동한다. (물론 1번처럼 외부 라이브러리를 사용하는 경우 함수 내부에 next()가 내장 돠어 있는 경우가 있다.)

아니면 app.use()를 사용해서 미들웨어를 사용하기도 한다. (주로 외부라이브러리를 사용할 때 씀)

## 라우팅

<Br>

    라우팅은 URL 요청에 따른 응답 처리를 의미한다.

<Br>


```js

app.get('/', (req, res) => {
    res.send('hello, world!');
})

```

위 코드 처럼 REST API(get, post, put, update)등의 메서드를 사용하고 매개변수로 주소를 그 후 처리할 내용을 넣어 작동한다.

<Br>

## 추가 (Express 동작 방식)

<Br>

Express를 처음 봤을 때 동작 방식을 이해하는데 어려움을 겪었다.

```js

// 미들웨어 셋팅
app.use(morgan('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended : false }));
app.use('/', express.static(path.join(__dirname, 'public')));

//라우트
app.get('/', (req, res) => {
    res.send('hello, world!');
})

//에러처리
app.use((req, res, next) => {
    console.log(req)
    const error = new Error('라우터가 없습니다!');
    error.status = 404;
    next(error);
})

app.use((err, req, res, next) => {
    console.log(err);
    res.render('error', err);
})


```

다음 예시가 그 예인데

Express는 일반적으로 첫 줄에서 마지막줄로 진행된다고 알고 있엇다.

그래서 만약 라우트 부분을 처리하고 나면 바로 에러 처리가 작동 될 줄 알았으나 실제로는 동작하지 않았다

그 이유는 바로 라우트 안에 next()가 들어 있지 않았기 때문이다.

즉 그림으로 정의하면 다음과 같다.

![](./img/middleware.jpeg)

1. 서버를 처음 실행 시키면 미들웨어 셋팅 부분이 동작한다.

2. 그 후 '주소/'를 들어가게 되면 라우트 부분이 실행되고 다시 요청을 기다린다.

3. 그러나 '주소/'가 아닌 다른 주소가 들어가면 라우트가 실행되지 않고 그 다음 코드인 에러처리가 동작하여 에러가 작동된다.
