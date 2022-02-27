# Tip

    자주 까먹는 DB 사용법 및 팁들을 정리한 글입니다. (PostgreSQL 기준으로 작성)

## DML 쿼리

#### INSERT

```sql
    INSERT INTO 테이블 VALUES(1, 2, 3, 4...);
    INSERT INTO 테이블 (컬럼명1, 2, 3, 4...) VALUES(컬럼데이터1, 2`, 3`, 4`...);
    INSERT INTO 테이블 VALUES
    (1, 2, 3, 4...),
    (1, 2, 3, 4...),
    (1, 2, 3, 4...)
```

#### SELECT

```sql
    SELECT * / 컬럼명 FROM 테이블 (LIMIT 로우 개수 설정 / OFFSET 시작지점) (ORDER BY 컬럼 ASC/DESC)
    SELECT * / 컬럼명 FROM 테이블 WHERE '2020' = ( SELECT * FROM table ... ) => 서브쿼리
```

#### 특정 단어가 포함된 경우 찾는 법 LIKE

```sql
    SELECT \* FROM 테이블 WHERE 문자열 컬럼 LIKE 값
```

여기서 값이 맨앞에 오는 경우 %값
맨 뒤에 오는 경우 값%
그냥 포함되어 있는 경우 %값% 으로 검색하면 된다.

#### UPDATE

```sql
    UPDATE 테이블 SET 컬럼명 = 데이터 WHERE 조건
```

#### DELETE

```sql
    DELETE FROM 테이블 WHERE 조건 => 로우 삭제
    DELETE FROM 테이블 => 전체 삭제
```

## DDL 쿼리

```sql
    CREATE TABLE 테이블 AS ( SELECT 서브 쿼리) => 테이블 복사
    DROP TABLE 테이블
```

## DCL 쿼리

## SUM, MAX, MIN

#### 가장 최근에 들어온 동물은 언제 들어왔는지 조회하는 SQL 문을 작성해주세요.

가장, 최대, 최소, 나중에 등 1개만을 뽑는 경우라면 MAX, MIN을 사용

SELECT max(컬럼) FROM 테이블

갯수를 세는 것은 count()

SELECT count(컬럼 / \*) FROM 테이블

## GROUP BY

    한 테이블에 로우들을 그룹지어 새로운 결과를 만들고 싶을 때 사용 + 집계함수와 같이 조회 가능

WHERE은 집계 전 데이터를 조회할 때 HAVING은 집계 후 데이터를 조회할 때 사용

```sql
    SELECT 그룹화 할 컬럼 FROM 테이블 GROUP BY 그룹화 할 컬럼
    SELECT 그룹화 할 컬럼 FROM 테이블 GROUP BY 그룹화 할 컬럼 HAVING 조건
```

#### 중복을 제거하는 키워드 DISTINCT

    GROUP BY 역시 중복을 지울 수 있으며 추가로 집계함수 사용 가능하며 집계함수 사용 이후 Having을 통해 조건을 만들 수 있다.

```sql
    SELECT DISTINCT 컬럼 지정 FROM 테이블
```

#### 여러개의 테이블을 연결하는 UNION, UNION ALL

    전제조건
    1. 두 개의 테이블은 컬럼이 같아야한다.
    2. 같은 위치에 동일한 형식과 의미의 정보가 담겨져있어야한다.

UNION => 중복 요소는 1개로
UNION ALL => 그냥 합치기

```sql
    SELECT ANIMAL_ID FROM ANIMAL_INS
    UNION (ALL)
    SELECT ANIMAL_ID FROM ANIMAL_INS2
```

#### 여러개의 테이블의 교집합만 보여주는 INTERSECT, INTERSECT ALL

전제조건은 UNION과 동일

INTERSECT => 중복 요소는 1개로
INTERSECT ALL => 그냥 합치기

```sql
    SELECT ANIMAL_ID FROM ANIMAL_INS
    INTERSECT (ALL)
    SELECT ANIMAL_ID FROM ANIMAL_INS2
```

#### 여러개의 테이블의 차집합만 보여주는 EXCEPT, EXCEPT ALL

SELECT ANIMAL_ID FROM ANIMAL_INS 만 가지고 있는 로우들을 보여준다.

```sql
    SELECT ANIMAL_ID FROM ANIMAL_INS
    EXCEPT (ALL)
    SELECT ANIMAL_ID FROM ANIMAL_INS2
```

## IS NULL

#### 빈 값도 기본 값을 설정하여 보여주는 방식

```sql
    select coalesce(인자1, 인자2) from table  // 결과가 null이아니면 인자1값을 가져오고 null이면 인자2를 반환
```

#### 빈 값만 보여주는 방식

```sql
    SELECT ANIMAL_ID FROM ANIMAL_INS WHERE NAME is (not) null
```

## JOIN

4가지로 구성

![ss](https://theartofpostgresql.com/img/SQL-JOINS-Example-0.png)

```sql
    SELECT ANIMAL_ID FROM ANIMAL_INS (
        (LEFT, RIGHT, FULL) JOIN ANIMAL_2
        ON ANIMAL_INS.컬럼 = ANIMAL_2.컬럼 등의 조건문
    )
```

#### LEFT JOIN vs RIGHT JOIN

A LEFT JOIN B은 A테이블 기준으로 데이터를 나눈다. 즉, A테이블 값은 모두 보여주며, B테이블에서 할당 할 수 있는 값이 없다면 Null로 채운다.
A RIGHT JOIN B은 B테이블 기준으로 데이터를 나눈다.

+) FULL JOIN은 서로 연결되어 있지 않은 부분만 NULL 처리한다.

## String, Date

```sql
    SELECT to_char(날짜 타입, 'YYYY-MM-DD HH24:MI:SS') FROM 테이블
```

if문

```sql
SELECT
    CASE WHEN score > 90 THEN 'A'
    WHEN score > 70 THEN 'B'
    WHEN score > 50 THEN 'C'
    WHEN score > 30 THEN 'D'
    ELSE 'F'
  END AS code_score
FROM mine

mysql
SELECT ANIMAL_ID, NAME, if(SEX_UPON_INTAKE like 'Intact%', 'X', 'O') as '중성화' from ANIMAL_INS
if(조건문, 참, 거짓)
```

날짜

```sql

select to_char(now(), 'YYYY-MM-DD HH24:MI:SS')
select to_date('01022019','MMDDYYYY')
https://walkingfox.tistory.com/90

mysql
SELECT DATE_FORMAT('20200405','%Y/%m/%d')
https://ponyozzang.tistory.com/656

```

## 기타 팁

#### SQL 쿼리문 동작 순서

1. FROM
2. WHERE
3. GROUP BY
4. HAVING
5. SELECT
6. DISTINCT
7. ORDER BY
8. LIMIT

#### Having vs Where

Where이 먼저 동작하기 때문에 성능이 더 좋으며, 초반에 필터링이 많이 될 수 있게 작성하는 것이 좋다.
