# 3주차 정리

## 도커 커맨드 치트 시트

### 컨테이너 환경 표시

```bash
**docker version // 클라이언트, 서버 정보 표시**
docker info // + 추가 세부 정보 표시
```

### 컨테이너의 3대 기능

```bash
**docker build -t 리포지토리:태그 . // Dockerfile을 통해 이미지 생성 (-f 옵션으로 도커파일 지정가능)**
docker images // 이미지 목록
**docker rmi 이미지 // 이미지 삭제**
docker image prune -a // 일괄 삭제

**docker pull 원격_리포지터리:태그 // 원격 리포지터리에서 이미지를 가져온다.**
docker tag 이미지 A B (A -> B로 변환) // 이미지에 태그를 지정한다.
docker login // 레지스트리 서비스에 로그인
**docker push 원격_리포지터리:태그 // 원격레지스트리에 이미지를 등록한다.**
docker save -o 파일명 이미지 // 이미지를 아카이브 형식 파일(tar)로 기록
docker load -i 파일명 // 아카이브 형식 파일(tar)으로 저장된 파일을 이미지로 로드
docker export 컨테이너명 -O 파일명 // 이미지뿐만 아니라 컨테이너도
docker import 파일명 리포지터리:태그 // 이미지뿐만 아니라 컨테이너도

docker run --rm -it 이미지 커맨드 // 이미지를 통해 컨테이너 생성 컨테이너가 종료되면 컨테이너 삭제
**docker run -d -p 5000:80 이미지 // -p 포트포워딩 -d 백그라운드 실행**
docker run --name NAME -d -p 5000:80 이미지
**docker run -v 'pwd'/html:/usr/share/nginx/html(로컬_절대경로:컨테이너_내_경로) -d -p 5000:80 이미지 // 퍼시스턴트 볼륨 지정**
**docker exec -it 컨테이너 /bin/bash // 컨테이너 쉘 실행**
docker ps // 실행중인 컨테이너 리스트 
**docker ps -a // 모든 컨테이너 리스트**
**docker stop 컨테이너명|컨테이너ID // 컨테이너 중지**
**docker kill 컨테이너명|컨테이너ID // 컨테이너 강제 종료**
**docker rm 컨테이너명|컨테이너ID // 컨테이너 삭제**
**docker commit 컨테이너명|컨테이너ID 리포지토리:태그 // 컨테이너를 이미지로 저장**

```

### 디버그 관련 기능

```bash
**docker logs 컨테이너명|컨테이너ID // 컨테이너 로그 출력**
**docker logs -f 컨테이너명|컨테이너ID // 실시간 로그 출력**
docker exec -it 컨테이너명|컨테이너ID 커맨드 // 
**docker inspect 컨테이너명|컨테이너ID // 컨테이너 정보 보기**
docker stats // 컨테이너 실행 상태를 실시간으로
docker attach --sig-proxy=false 컨테이너명|컨테이너ID // 컨테이너 표준 출력을 화면에 표시
docker pause 컨테이너명|컨테이너ID // 컨테이너의 일시정지
docker unpause 컨테이너명|컨테이너ID // 컨테이너의 일시정지 해제
**docker start -a 컨테이너의 일시정지 // 컨테이너 실행 & 표준 출력과 표준 에러출력을 터미널에 출력**
```

### 쿠버네티스와 중복되는 기능

```bash
docker network create 네트워크명
docker network ls
docker network rm 네트워크 명
docker network prune // 불필요한 네트워크 삭제

docker volume create 볼륨명
docker volume ls
docker volume rm 볼륨명
docker volume prune

**docker-compose up -d // docker-compose.yml를 사용해서 복수의 컨테이너 가동**
docker-compose ps // docker-compose 관리하에 실해중인 컨테이너 목록 출력
**docker-compose down // docker-compose 관리하에 실해중인 컨테이너 종료**
**docker-compose down --rmi all // docker-compose 관리하에 실해중인 컨테이너 종료 & 이미지 삭제**
```

### Dockerfile 치트 시트

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled.png)

### 컨테이너와 네트워크

컨테이너는 IP 주소를 할당받거나 포트포워딩을 통해 컨테이너간 통신이 가능하다.

1. 컨테이너 호스트안에서 컨테이너끼리 통신하는 경우
2. PC 포트와 컨테이너 포트를 포트포워딩하여 통신하는 경우

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%201.png)

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%202.png)

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%203.png)

기본적은 네트워크

bridge ⇒ 기본 네트워크 드라이버로 일반적으로 컨테이너의 애플리케이션 통신이 필요할 때 사용(-p 옵션)

host ⇒ 컨테이너와 도커 호스트 간에 네트워크 격리를 제거하고 직접 호스트의 네트워킹을 사용

overlay ⇒ 여러 도커 데몬을 서로 연결하여 스웜 서비스가 서로 통신할 수 있도록 함. (물리적으로 분리된 여러 서버에서 컨테이너들 간에 통신이 가능해짐)

none ⇒ 모든 네트워킹 사용 불가능

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%204.png)

별도의 네트워크 영역으로 나뉨

NET 인터페이스 & veth 인터페이스 사용

veth → 2개의 인터페이스가 페어로 동작 & 한쪽에 패킷이 들어오면 다른쪽으로 나가도록 동작

net → 네트워크 인터페이스를 각각 다른 namespace에 할당함으로써 서로가 서로를 모르게끔 설정할 수 있다

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%205.png)

## 컨테이너 API

컨테이너 API를 통해 내부 로직을 몰라도 쉽게 컨테이너의 기능을 사용할 수 있음

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%206.png)

컨테이너 기동, 헬스 체크, 컨테이너 종료, 가동로그, 종료상태, 퍼시스턴트 볼륨, 후크 ,서비스 등 다양한 API가 존재한다.

- 컨테이너 기동 → 컨테이너 내 애플리케이션 가동 시에 환경 변수나 실행 인자를 읽어서 그에 맞게 동작하게 만들 수 있다.
- 헬스체크
    - 준비 완료 프로브 → 컨테이너 내 애플리케이션이 초기화가 완료되어 외부로부터의 요청을 받을 수 있게 되었음을 알리는 인터페이스 (요청을 받을 수 있는지 확인하기 위해 사용)
    - 활성 프로브 → 애플리케이션의 가동 상태(정상/비정상)를 알리는 인터페이스 (비정상이 감지되면 쿠버네티스가 재가동하여 복구를 시도한다.)
- 컨테이너 종료
    - SIGTERM → 안전한 종료, 프로세스가 처리해야할 작업이 있으면 무시할수도 있음
    - SIGKILL → 강제 종료, 제한시간 내에 SIGTERM이 안끝나면 SIGKILL이 실행된다.
- 서비스
    - 특정 포트번호로 클라이언트로부터의 요청을 받고 처리결과를 반환하는 역할을 수행
    - 쿠버네티스는 파드에 포트를 열어 클라이언트로부터의 요청을 받는다.
- 로그
    - MSA에 의해 로그를 일관되게 관리를 함, 컨테이너의 표준 출력, 오류를 로그로 간직한다
- 후크
    - 컨테이너가 가동하고 종료할 때 컨테이너 내에서 특정 처리(스크립트, HTTP 요청 처리 구현)를 실행시킬 수 있다.
- 퍼시스턴트 볼륨
    - 설정에 의해 외부에서 주입하거나 발생 데이터를 보존하기 위해 사용
    - 컨테이너는 영속적인 존재가 아니기 때문에 중요한 데이터는 반드시 외부에 보관해야한다.
- 종료상태
    - 종료코드가 0이면 정상종료, 그 외의 값은 비정상 종료

### 컨테이너 종료 관련 API 구현 예시

상황 : 컨테이너 생성 시 shell 스크립트에 Interval를 걸어서 계속 출력을 하는 중

그러나 컨테이너 종료를 하면, 기존 데이터 보존 X, 종료되는데 10초 정도 소요

```bash
# 카운터 초기화
COUNT=0

# 환경변수가 없으면 설정
if [ -z "$INTERVAL" ]; then
    INTERVAL=3
fi

# 메인 루프
while [ ture ];
do
    TM=`date|awk '{print $4}'`
    printf "%s : %s \n" $TM $COUNT
    let COUNT=COUNT+1
    sleep $INTERVAL
done
```

아래와 같이 변경

SIGTERM에 대한 핸들러를 작성해준다.

```bash
# 카운터 초기화
COUNT=0

# 환경변수가 없으면 설정
if [ -z "$INTERVAL" ]; then
    INTERVAL=3
fi

# 기동시 상태 취득
if [ -f save.dat ]; then
   COUNT=`cat save.dat`
   rm -f save.dat
fi

# SIGTERM 시그널 처리
save() {
  echo $COUNT > save.dat
  exit
}
trap save TERM

# 메인 루프
while [ ture ];
do
    TM=`date|awk '{print $4}'`
    printf "%s : %s \n" $TM $COUNT
    let COUNT=COUNT+1
    sleep $INTERVAL
done
```

![Untitled](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20%E1%84%8C%E1%85%A5%E1%86%BC%E1%84%85%E1%85%B5%204bc028d27f00440d9a35202bfa674e0f/Untitled%207.png)