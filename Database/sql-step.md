# SQL 첫걸음

## 복수의 열을 지정해 정렬하기

```sql
SELECT 열 FROM 테이블
ORDER BY 정렬할 열1 [DESC], 정렬할 열2 [DESC]
```

⇒ 열1을 기준으로 정렬 후 값이 동일하다면 열2 기준으로 정렬한다.

ORDER BY 로 정렬을 하지 않은 경우 (`SELECT * FROM TABLE`) 순서가 항상 일정하지 않다.

⇒ **데이터 베이스의 당시 상황에 따라 어떤 순서로 행을 반환할지 결정된다.**

정렬 방법을 생략하면 기본적으로 `ASC`가 되지만 데이터베이스 제품에 따라 기본값을 변경할 수 있으므로 주의해야한다.

만약 NULL 값이 내부에 있는 경우(**NULL은 특성상 대소비교를 할 수 없어 정렬 시에는 별도의 방법으로 취급**)

따라서 ORDER BY에서 NULL 값은 가장 먼저 표시되거나 가장 나중에 표시된다. (표준으로 정해지지 않아 데이터베이스 제품마다 다르다. / MySQL 같은 경우 가장 작은 값으로 취급)

## 결과 행 제한하기 - LIMIT

```sql
SELECT 열 FROM 테이블
LIMIT 행 개수 [OFFSET 시작행]
```

⇒ 전체 결과 값 중 일부만 보고 싶을 때 사용 / 표준이 아니고 MySQL, PostgreSQL에서 사용할 수 있는 문법

LIMIT 명령어는 쿼리문 맨 마지막에 사용

LIMIT는 반환할 행 수를 제한하는 기능으로 최종적인 결과값에서 뽑아서 가져온다. ⇒ 성능이 별로 안좋음

LIMIT는 표준이 아니므로 다른 데이터베이스에서는 TOP, ROWNUM를 사용한다.

Pagination 기능을 위해 OFFSET으로 시작행을 지정하여 일부만을 가져오게 할 수도 있다.

OFFSET의 기본 값은 0으로 1번째 행을 의미한다.

## 수치 연산

### NULL 값의 연산

NULL + 1은 NULL이다. ⇒ SQL에서 NULL은 0으로 처리되지 않기 때문이다.

- NULL + 1
- 1 + NULL
- 1 + 2 \* NULL
- 1 / NULL

위 연산 값은 다 NULL이다.

### 함수 연산

`함수명(인수1, 인수2, ..)`를 통해서 사용할 수 있다.

MySQL에서는 다음과 같은 연산 함수가 있다.

- FLOOR() ⇒ 버림
- MOD() ⇒ 나머지
- ABC() ⇒ 절대값
- SIGN() ⇒ 양수, 음수 부호 리턴
- CEIL() ⇒ N보다 작지 않은 정수의 최대값 리턴
- ROUND() ⇒ 반올림 함수

## 문자열 연산

문자열 결합

```sql
'ABC' || '1234' -> 'ABC1234' // Oracle, DB2, PostgreSQL
CONCAT('ABC', '1234') -> 'ABC1234' // MySQL
```

substring

```sql
SUBSTRING('ABCDE', 1, 2) -> 2번째 자리부터 1자리 추출
```

trim

```sql
TRIM('ABCDE     ') -> 공백 제거
```

CHARACTER_LENGTH ⇒ 문자열의 길이를 계산하는 함수

## 날짜 연산

```sql
SELECT CURRENT_TIMESTAMP; // CURRENT_TIMESTAMP ⇒ 함수이지만 인수를 지정할 필요가 없다.
TO_DATE('2014/01/25', 'YYYY/MM/DD') // 문자열 데이터를 원하는 서식으로 변경
SELECT CURRENT_DATE + INTERVAL 1 DAY; //  + INTERVAL 1 DAY -> 하루를 더한다.
DATEDIFF('2014/01/25', '2014/01/25')  // MySQL 날짜 차이
```

## CASE 문으로 데이터 변환하기

```sql
CASE WHEN 조건식1 THEN 식1  // WHEN NULL은 사용할 수 없다. = NULL 은 연산할 수 없기 때문에
	[WHEN 조건식2 THEN 식2]
	[ELSE 식3] // ELSE를 생략하면 ELSE NULL이 된다. 따라서 지정하는 편이 낫다
END

SELECT CASE WHEN a is null THEN 0 ELSE a END FROM TABLE; // null인 경우 0으로 바꾸기
COALESCE(a, 0) // 위 쿼리문과 똑같은 역할 a 가 null이면 0으로 변환한다.
```

### 물리삭제와 논리삭제

데이터를 삭제하는 방법은 용도에 따라 물리삭제와 논리삭제로 나뉨.

물리삭제 ⇒ SQL의 DELETE 명령을 사용해 직접 데이터를 삭제하자는 사고 방식

- 삭제되기 이전 상태로 되돌리기 어렵다

논리삭제 ⇒ 실제로 삭제하는 대신 UPDATE를 통해 삭제된 것 처럼 보이게 하는 방식 (삭제 플래그 열을 만들어 삭제되었다고 표시만 한다)

- 삭제해도 데이터베이스의 저장공간이 늘어나지 않음
- 데이터베이스 크기가 증가함에 따라 검색속도 저하
- 애플리케이션에서도 UPDATE를 하기 때문에 혼란발생

물리삭제와 논리삭제는 상황에 맞게 선택한다.

## 상관 서브쿼리
