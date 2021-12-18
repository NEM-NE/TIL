RDBMS와 NoSQL
===

## 목차

1. <b>트랜잭션이란?</b>
2. <b>ACID란?</b>
3. <b>트랜잭션과 Serial Schedule?</b>
4. <b>동시성 제어</b>
4. <b>Lock</b>
4. <b>트랜잭션에서 발생할 수 있는 문제들</b>
5. <b>트랜잭션 고립 단계</b>
6. <b>분산환경에서의 트랜잭션</b>

<br><br><br>

## 트랜잭션이란?

    DB 상태 변환을 위해 수행하는 하나의 최소 작업단위. (여기서 작업단위는 쿼리문 1개를 뜻하는게 아니다. Ex 은행 입금, 출금)

예상치 못한 오류(작업 도중에 기계가 멈추는 경우, 동사에 특정 데이터에 접근하여 다른 값을 갖는 경우 등)를 막을 수 있다.

<br><br>

## ACID란?

    트랜잭션이 가지는 성질로 Atomicity, Consistency, Isolation, Durability로 나눠진다.

* Atomicity => 트랜잭션은 더 이상 쪼갤 수 없는 최소 단위이다. 따라서 트랙잭션 도중 실패하면 트랜잭션 전체가 다 실패한 것이다.
* Consistency => 언제나 같은 결과가 나온다.
* Isolation => 트랜잭션이 작업 중인 데이터를 다른 트랜잭션이 접근할 수 없다.
* Durability => 트랜잭션 후 작업 결과는 영구적으로 반영된다.

<br>

### Commit과 Rollback 

<br><br>

## 동시성 제어

    둘 이상의 트랜잭션이 수행될 때, 일관성을 해치지 않도록 데이터 접근 제어
    다중 사용자 환경을 지원하는 DBMS 경우 반드시 지원해야하는 기능

<br><br>

## Lock

    Locking : 트랜잭션들이 동일한 데이터에 대해 접근을 못하도록 해주는 기법

* 데이터에 대해 Read, Write를 수행하려면 lock 연산을 해줘야한다.
* lock 연산 후 트랜잭션이 종료되기 전에 unlock연산을 수행한다.
* 이미 lock 연산이 걸려있는 경우 다른 트랜잭션에서 lock연산을 수행할 수 없다.
* lock연산이 안된 상태에서 unlock 연산을 수행할 수 없다.
* 트랜잭션이 lock 연산을 못하면 대기 상태가 된다.
* lock은 공유 락(LS, Shared Lock)과 베타 락(LX, Exculsive Lock)으로 나뉜다.

<br>

### Shared Lock

    읽기를 할 때 사용

* 데이터를 읽을 수 있지만 write 할 수 없다.
* 읽기만 할 수 있으므로 다른 트랜잭션에서 LS을 걸어 동시에 사용할 수 있다.

<br>

### Exclusive Lock

    읽기 / 쓰기를 할 때 사용

* LX를 설정하면 다른 트랜잭션에서는 lock을 걸 수 없다.

<br>

### 2단계 Locking

WIP

### Lock 없이 트랜잭션 가능?

    Optimistic Concurrency Control => 사용자들이 빈번하게 동시에 데이터를 수정하지 않을 것이라고 가정, Lock을 걸지 않고 DB나 애플리케이션에서 자체 검증 & Lock을 안하기 때문에 성능 향상

    Pessmitic COncurrency Control => 사용자들이 빈번하게 동시에 데이터를 수정할 것이라고 가정, Lock을 걸어 작업, 시스템 동시성을 심각하게 떨어트릴 위험이 있음

[Optimistic Concurrency Control 관련 글](https://stackoverflow.com/questions/17431338/optimistic-locking-in-mysql)

<br><br>

## 트랜잭션과 Serial Schedule?

> Serial Schedule : 트랜잭션이 차례로 실행되는 즉, 직렬 방식을 동작하는 것을 의미
> Serialize: 트랜잭션의 결과가 Serial Schedule의 부분집합인 경우

결과의 동등인 Result equivalent를 중시하는 것이 아닌 연산의 과정이 동일할 경우

<br>

### 예시 1 

![이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fyecn7%2FbtqPqlny6qf%2FAGXk7aRkZu0aSKQYwJIsmK%2Fimg.png)

write연산이 들어갈 때 해당 스케줄을 conflict 하다고 보는데,

x와 y는 독립적인 값이기 때문에 연산의 순서가 바뀌어도 상관없다. 즉, **Serializable하다.**

<br>

### 예시 2

![아마자2](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FIKxb6%2FbtqPwl1pDiH%2Fbq3dHTb3RE6PXHUwENnDJ0%2Fimg.png)

그러나 write_item(x)가 해당 위치에 있으면 T2와 충돌이 일어나기 때문에 **Nonserializable하다.**

<br><br>

## 트랜잭션에서 발생할 수 있는 문제들

<br>

### Lost Update Problem

    두 개의 트랜잭션이 동시에 한 아이템의 데이터를 변경하는 경우 => 먼저 선택하고 변경한 트랜잭션이 적용이 안된다.

<br>

### Dirty Read Problem

    트랜잭션이 값을 변경하는 도중 다른 트랜잭션이 값을 읽었을 경우

![이미지](https://static.javatpoint.com/dbms/images/dbms-concurrency-control2.png)

<br>

### Non-repeatable Read Problem

    한 트랜잭션에서 값을 2번 읽었을 때 읽은 값들이 서로 다른 경우

![이미지](https://media.geeksforgeeks.org/wp-content/uploads/20190823132927/d51.jpg)

<br>

### Phantom Read Problem

    한 트랙잭션에서 값을 2번 읽었을 때 마지막으로 읽은 값이 없는 경우

![아마자](https://media.geeksforgeeks.org/wp-content/uploads/20190823132927/d51.jpg)

    따라서 해당 문제들을 막기 위해 동시성 제어 (concurrency control)라는 기능을 제공하여 데이터베이스의 무결성을 보호하고, transaction이 항상 정확하고 일관된 데이터를 참조할 수 있도록 해야 한다.    

<br><br>

## 트랜잭션 고립단계

    위 문제들처럼 트랜잭션에 관해 다양한 문제가 발생할 수 있다. 그래서 Lock보다는 완화된 방법으로 트랜잭션을 실행시키면서
    발생하는 문제를 해결하기위해 DBMS에서 제공하는 명령어

![img](https://t1.daumcdn.net/cfile/tistory/99958E475A2FEAB22C)

<br>

### READ UNCOMMITTED

![ㅁㅁㅁ](https://t1.daumcdn.net/cfile/tistory/998A88345A2FED6006)

    고립 수준이 가장 낮은 명령어로 자신의 데이터에 아무런 공유락을 걸지 않는다. 베타락은 건다.

<br>

### READ COMMITTED

![ㅁㅁㅁ](https://t1.daumcdn.net/cfile/tistory/9950383F5A2FEEB209)

    Dirty Problem을 위해서 공유락은 걸지만 트랜잭션이 끝나기 전에 해지 가능

<br>

### REPEATABLE READ

![ㅁㅁㅁ](https://t1.daumcdn.net/cfile/tistory/995996435A2FF24F3C)

    자신의 데이터에 설정된 공유락과 배타락을 트랜잭션이 종료될 때까지 유지 가능
    다른 트랜잭션이 자신의 데이터를 update를 할 수 없게 한다. (Mysql, Oracle 사용)

<br>

### SERIALIZABLE

![ㅁㅁㅁ](https://t1.daumcdn.net/cfile/tistory/99F9704C5A2FF31504)

    실행 중인 트랜잭션이 종료 후 다른 트랜잭션 실행가능


<br><br>

## 분산환경에서의 트랜잭션

2-Phase Commit

