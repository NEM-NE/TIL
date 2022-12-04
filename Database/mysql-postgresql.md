MySQL과 PostgreSQL
===

## 목차

1. <b>MySQL</b>
2. <b>PostgreSQL</b>
3. <b>MySQL vs PostgreSQL</b>

<br><br><br>

## MySQL

<br>

### MySQL이란?

* 테이블 구조로 행과 열이 존재하는 RDBMS
* SQL 표준을 준수하기보다는 안정성과 성능에 초점을 맞춤
* 데이터 타입으로 Numeric, String, Date&Time이 존재한다.

<br>

### MySQL 장점

* 인기 있는 DBMS이기 때문에 수 많은 커뮤니티 및 관련 자료 존재하여 배우기 쉽다.
* 보안 수준이 높은 DBMS이다. 사용자 관리 가능, 암호 보안 수준 설정 가능, 모든 사용자가 접근할 수 있는 테스트 DBMS를 삭제해주는 스크립트도 실행
* 속도도 빠르다.
* 복제 기능을 지원한다. 두 개이상의 호스트에서 정보를 공유하는 방식인 복제 기능 지원.
* 단순한 CRUD 쿼리에서 성능이 뛰어남

<br>

### MySQL 단점

* MySQL은 SQL 표준을 준수하지 않기 때문에 SQL의 모든 기능을 이용할 수 없습니다.
* 라이선스에 따라 기능이 제한적입니다. 일부 기능을 유료 버전에서만 이용할 수 있습니다.
* 개발 속도가 더딥니다.

<br>

### 사용하면 좋은 경우

* 복제 기능을 통해 분산 작업을 할 때 유용
* 웹/앱 어플리케이션을 만들 때 유용
* 복제 기능을 통해 수평적 확장에 유용

<br>

### 사용하면 안되는 경우

* SQL 준수가 필요한 경우 => MySQL은 성능과 안정성을 위해 표준을 많이 따르고 있진 않다.
* 동시성이 필요한 경우 => MySQL은 읽기 작업이 많을 때 유용하지만 다수의 사용자가 읽기/쓰기 작업을 할 때 무결성이 충족 되지 않은 경우가 종종 발생

<br><br>

## PostgreSQL

### PostgreSQL이란?

![ㅁㅁ](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fn5IKO%2FbtqDg5Ew7cO%2Fgz2OTrYjPsWno47wwKwyVK%2Fimg.png)

* 서버/클라이언트 모델 사용 => 클라이언트 커넥션이 생길 때마다 서버프로세스를 배정하여 작업을 대리 수행한다.
* 확장성이 뛰어나고 표준을 준수하는 오픈소스 DBMS
* 객체 관계형 데이터베이스로 객체 관련 기능(테이블 상속, 함수 오버로딩)등을 지원
* MVCC를 통해 동시성 충족
* Numeric, Character, Date&Time, Geometric, Network address, Bit string, Text search, JSON(JSON 인덱싱 지원) 등 다양한 타입 보유

### PostgreSQL 장점

* SQL 표준 준수 => 다양한 기능(병렬처리, partial index) 존재
* 오픈소스 => 버그에 대한 대응, 지속적인 업데이트, 다양한 자료 존재
* 확장성이 뛰어나다
* 복잡한 쿼리를 처리할 때 성능이 뛰어남

### PostgreSQL 단점

* 메모리 성능이 떨어짐 => 커넥션 마다 생성되는 프로세스는 10MB 정도 차지 하기 때문에 클라이언트가 증가하면 메모리가 빠르게 증가한다.

### 사용하면 좋은 경우

* 무결성이 중요할 때 => MVCC를 통해 무결성 보장
* 복잡한 작업 연산을 수행하는 경우 적합합니다.

### 사용하면 안되는 경우

* 속도가 중요할 경우
* 간단한 설정이 필요할 경우
* 간단한 복제 작업이 필요할 경우