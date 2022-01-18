Index
===

## 목차

1. <b>Full Scan & Range Scan</b>
2. <b>Index란?</b>
3. <b>Index 팁</b>
3. <b>Index 자료구조</b>

<br><br><br>

## Full Scan & Range Scan

<br>

Full Scan => 테이블에 대한 모든 Record들을 다 읽음.
Range Scan => 테이블에 포함된 일부 Record들만 읽음.

일반적으로 모든 레코드들을 다 불러오는 경우는 없기 때문에 내가 찾고자 하는 범위를 지정해서 Range Scan을 하는 것이 효율적이다.

<br><br>

## Index란?

    테이블에 대한 검색속도를 올려주는 자료구조

* index는 1개 혹은 여러개의 컬럼을 기반으로 생성
* index는 key, value만 알고 있기 때문에 용량을 크게 차지하지 않음.
* index는 테이블의 사본

### 장점

* SQL문을 변경하지 않아도 성능 개선이 가능
* 테이블을 조회하는 속도를 개선 => (UPDATE나 DELETE처럼 조회를 사용하는 모든 부분에 속도가 개선됨.)
* 전반적인 시스템의 과부하를 줄여준다.

### 단점

* 인덱스를 관리하기 위해 보통 DB의 10% 해당하는 공간 사용
* 인덱스를 관리하기 위해 추가적인 작업 필요
* 잘못된 인덱스를 사용하면 성능이 저하되는 역효과가 발생 할 수 있음 (UPDATE나 DELETE를 하면 인덱스는 사라지는 것이 아닌 '사용하지 않음'이라고 변경됨. 즉, 공간은 계속 차지)

<br><br>

## Index 팁

1. `between`, `like`, `<`, `>`, 등의 범위 조건을 사용하면 첫 번째 컬럼에만 인덱스가 적용되고 아후 인덱스에는 적용 X (WHERE age < 25 AND money < 50000 이면 age만 적용)
2. `=`, `in`은 다 적용됨.
3. `AND` 연산자는 레코드를 줄이지만 `OR` 연산자는 레코드를 늘려 Full Scan을 할 수 있음
4. 컬럼 값을 변형하지 않고 그대로 사용해야한다.
5. `null`인 경우 `is null`조건으로 Range Scan 가능

<br><br>

## Index 자료구조

### B+Tree

![b-tree](https://media.vlpt.us/images/emplam27/post/ddbae2c9-da94-457d-bad8-77ff6791255b/B%ED%8A%B8%EB%A6%AC%20%EA%B8%B0%EB%B3%B8%20%ED%98%95%ED%83%9C.png)

기존 2개 이상의 자식 노드를 가질 수 있는 b-tree를 개선시킨 자료구조 / 시간복잡도 O(logN)

1. leaf 노드에만 Value 값이 있고 나머지 노드는 Key값만 있다.
2. leaf 노드는 LinkedList로 구성됨. (순차 검색에 용이하기 때문에 <, >를 처리하기 좋음)
3. 데이터 노드 크기는 인덱스 노드의 크기와 같지 않아도 된다.


### Hash Table

Hash Table를 통해 Key, Value 값을 저장하고 여기서 Key 값은 인덱스 값에 해싱함수를 거친 해쉬값으로 저장한다.

* 시간복잡도가 O(1)
* Key 값을 해싱하여 저장하기 때문에 = 연산 말고는 다른 연산은 효율이 떨어진다.

### GIN

![sss](https://postgrespro.com/media/2019/06/21/1001.png)

Postgresql에서 Text를 통한 검색(tsvector, Like)에 특화된 자료구조

기존에 값을 비교했던 b-tree와는 다르게 키워드를 기준으로 트리를 만듬.


