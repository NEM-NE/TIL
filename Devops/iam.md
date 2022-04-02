# IAM

## ROOT 사용자 MFA 적용하기

⇒ 2중 암호화

⇒ ROOT는 최상위 계정이기 때문에 보안관리가 중요

헤더에 계정 정보 / 보안자격 증명 / **멀티 팩터 인증(MFA) - 디바이스 할당 / 구글 OTP 이용**

**휴대폰을 바꾸게 된다면 반드시 마이그레이션 or MFA를 해제할 것.**

## Admin 사용자 추가

⇒ ROOT 사용자는 MFA 때문에 사용하기 번거롭고 함부로 사용하면 안되기 때문에 Admin 계정 사용

⇒ ROOT 사용자와 거의 동일한 역할을 갖지만 해킹 당할 경우 ROOT 사용자가 관리할 수 있음

1. IAM에 들어가기
2. 사용자 그룹 만들기
3. **권한 정책 설정**

   다양한 권한 정책이 있다.

   Administrator Access 선택

   ![스크린샷 2022-04-02 오전 12.47.32.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/99ea142d-1201-494a-819c-8512a00a5dd3/스크린샷_2022-04-02_오전_12.47.32.png)

   권한에 대한 JSON 형식으로 세부 정보를 볼 수 있다.

   Action : _ 가 있고, Resource: _ 가 있기 때문에 모든 액션를 할 수 있고 모든 리소스에 접근이 가능하다는 의미를 가진다.

   ![스크린샷 2022-04-02 오전 12.48.35.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2c723d04-1e08-4c42-a61f-e64bcea54dfb/스크린샷_2022-04-02_오전_12.48.35.png)

4. 사용자 생성
   1. `AWS 자격 증명 유형 선택`이 중요한데 `액세스 키`는 개발에서 사용되고 일반적인 사용은 `암호` 방식으로 활용

      ![스크린샷 2022-04-02 오전 12.50.10.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9df4cc43-5935-49c0-913d-e9c9a7c8eb80/스크린샷_2022-04-02_오전_12.50.10.png)

   2. 사용자가 속할 그룹 지정
   3. 태그 추가 ⇒ 해야된다고 하는데 일단 넘어갔음
   4. 최종적으로 아래와 같이 나오고 csv를 통해 접속 가능한 링크(IAM코드가 있는) 비밀번호 확인 가능

      ![스크린샷 2022-04-02 오전 12.53.05.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/35589f99-eaac-4243-b31b-87c97804351c/스크린샷_2022-04-02_오전_12.53.05.png)

## 개발자 사용자 추가

Admin 사용자 추가와 동일한 과정을 거치며 다만 **권한 정책 설정**에서는 `PowerUserAccess`를 선택

```json
{
            "Effect": "Allow",
            "NotAction": [
                "iam:*",
                "organizations:*",
                "account:*"
            ],
            "Resource": "*"
        },
```

iam과 organizations, account 액션은 안되고 리소스는 모든 접근이 가능한 권한

## 로그인 URL 변경하기

962239405332 이러한 숫자를 외우고 로그인 하기는 어려움

⇒ IAM 대시보드에서 IAM 코드에 대해 별칭을 지정할 수 있음

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0a6e3d1a-00af-4e0a-8e5d-148830d13d25/Untitled.png)

![스크린샷 2022-04-02 오전 12.58.23.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/42090e0f-4f79-41df-93ec-02115bf0cf02/스크린샷_2022-04-02_오전_12.58.23.png)

쉽게 로그인 가능

## 인증과 권한 부여

**IAM ⇒ AWS 리소스에 대해 액세스를 안전하게 제어할 수 있는 웹 서비스**

리소스 ⇒ AWS에서 컴퓨팅 스토리지, 네트워크

접근 ⇒ 리소스를 조작 및 제어하는 것

인증 ⇒ 자신이 누구라고 주장할 때 이를 확인하는 절차 / Ex) 사용자 로그인

권한부여 ⇒ 사용자가 원하는 리소스에 접근하는 것을 허용하는 과정

## IAM Policy

![스크린샷 2022-04-02 오전 1.07.26.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff0aac06-9bcb-43ce-9e80-72621bd93d6a/스크린샷_2022-04-02_오전_1.07.26.png)

AWS 인증 ⇒ User(계정 / 시크릿 키등으로 로그인)

AWS 권한 부여 ⇒ IAM Policy(JSON 문서)

- IAM Policy는 User, Group, Role에 적용 가능
- role은 임시 자격 증명

IAM Group ⇒ 공통의 권한을 가지는 사용자의 집합 / IAM Policy 연결 가능

## IAM Policy 예제

종류

- AWS 관리 정책 ⇒ AWS가 미리 만들어 놓은 정책
- 사용자 관리 정책 ⇒ 사용자가 직접 생성한 정책 / 기존 정책으로부터 수정 가능
- inline 정책 ⇒ 1회성 정책

예제1

![스크린샷 2022-04-02 오전 1.16.51.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d2438193-de22-40f1-a2ff-a21b3956a019/스크린샷_2022-04-02_오전_1.16.51.png)

S3에 관련된 기능만 사용가능

특정 버킷 읽기 전용 정책 예제

![스크린샷 2022-04-02 오전 1.17.54.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/34821a03-d87b-402a-8869-061e58fba50c/스크린샷_2022-04-02_오전_1.17.54.png)

Get, List ⇒ 읽기 전용

mydata 버킷 리소스만 접근 가능

## IAM Role

⇒ 특정 개체(IAM 사용자, AWS 서비스, 다른 계정, AWS 관리계정)에게 리소스의 접근 권한을 부여하기 위해 사용

⇒ 임시 자격 증명용

- 주로 AWS 서비스들이 직접 다른 AWS 서비스를 제어하기 위해 사용
- 사용자나 응용 프로그램에서 일시적으로 AWS 리소스에 접근 권한을 얻을 때도 사용

구성요소

- Role ARN : 식별자, 역할을 호출하기 위해 필요
- IAM Policy: 이 역할은 어떤 권한을 부여할 수 있는가
- **신뢰관계** : 어떤 개체가 IAM Role을 호출 할 수 있는가
- 유지 시간, 이름 등 필요

사용예

- EC2 role: EC2 인스턴스에게 AWS 서비스 접근권한을 부여
- Lambda Execution Role: 람다에서 S3로부터 파일을 읽고 싶을 때 role에 권한 지정
- 다른 계정의 사용자에게 내 계정의 DynamoDB에 임시 접근 권한 부여
- 안드로이드 앱이 S3로 직접 동영상을 업로드할 때 사용

ARN ⇒

![스크린샷 2022-04-02 오후 4.34.28.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1f3142da-f0ab-401a-9665-21d2e1a01e48/스크린샷_2022-04-02_오후_4.34.28.png)

## Switch Role 만들기 & 사용하기

IAM 페이지 / 액세스 관리 그룹 - 역할 - 역할 만들기 / 적절한 엔티티 선택 / 권한 지정 및 역할명 추가

![스크린샷 2022-04-02 오후 4.24.33.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a6eeaf3-4e13-4b65-bb17-d5927d4f7798/스크린샷_2022-04-02_오후_4.24.33.png)

신뢰 관계 ⇒ principal ⇒ AWS 계정에 속한 모든 사용자들이 이 역할을 사용할 수 있다.

⇒ root는 위 계정의 모든 유저들이 사용가능 만약 변경하고 싶다면 root 대신 user/userName을 지정하면 된다.

## CLI 설치 및 간단히 사용해 보기

aws라는 명령어로 사용가능

aws configure ⇒ IAM / 특정 사용자 / 보안자격 증명 / 액세스 키 만들기

![스크린샷 2022-04-02 오후 4.50.01.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6c6f4a81-8726-451a-b5a7-1f1d4c0100f8/스크린샷_2022-04-02_오후_4.50.01.png)

역할 바꾸기

config 명령어를 통해 새로운 프로필 생성

이후 IAM 역할에 가서 arn 주소를 복사

role_arn과 source_profile(default 유저가 사용)에 값 추가

![스크린샷 2022-04-02 오후 4.55.07.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1e0dc9bc-5823-4080-9d3d-bef806293996/스크린샷_2022-04-02_오후_4.55.07.png)

—profile이라는 옵션을 통해 기능을 이용할 수 있다. ⇒ 그냥 이용하면 접근 X
