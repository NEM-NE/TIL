# AOP란?

    만약 성능 측정을 위해 기존에 작성되어 있는 메서드에 전부 성능 측정 로직을 추가하면 어떻게 될까?
    기존 메서드는 약 1억개라면 1억번 코드를 추가해야할까?
    다른 책임이 있는 메서드에게 이런 "부가 기능"을 추가해도 될까?

AOP는 프로그램 구조에 대한 다른 방식의 생각을 제공함으로써 OOP를 구현한다.

OOP로 작성하는 것은 사용자 관점의 프로그래밍이다.
그러나 개발자, 운영자의 관점은 다를 수 있다. (로깅, 트랜잭션)
이런 관점을 분리해서 효율적으로 다룰 수 있는 것이 AOP

다른 관점을 별도로 분리해서 관리한다. (cross-cutting concern)

## AOP의 용어

Target => 어떤 대상에 부가 기능을 부여할 것인가 (대상)

Advice => 언제 부가 기능을 제공할 것인가? / Around, afterReturning, ..

Join Point => 어디에 적용할 것인가? 메서드, 필드 객체, 생성자 등 (Spring에서는 메서드만 가능)

Point cut => 실제 Advice가 적용될 지점

weaving => Aspect가 지정된 객체를 새로운 프록시 객체를 생성하는 과정.

## AOP 구현 방법

- 컴파일 시 => 컴파일 과정에 Aspect를 끼워넣는다.
- 클래스 로드시 => 클래스 로드할 때 Aspect를 끼워넣는다.
- 프록시 패턴 => 객체 사이에 Aspect를 끼워넣는다. (Spring AOP 방식)

프록시로 감싼 객체가 실제로 타켓 오브젝트의 조인이라는 메서드를 실행할때는
이너라는 메서드가 AOP에 대해서 감싼 프록시 객체가 아니기 때문에
자기 자신을 호출 할 때는 AOP로 감싼

## Spring AOP vs AspectJ

|            | spring AOP                                | AspectJ                                                   |
| ---------- | ----------------------------------------- | --------------------------------------------------------- |
| 목표       | 간단한 AOP 기능제공                       | 완벽한 AOP 제공                                           |
| join point | 메서드 레벨만 지원                        | 생성자, 필드, 메서드 등 다양하게 지원                     |
| weaving    | 런타임 시에만 가능                        | 런타임은 제공X compile-time, post-compile, load-time 제공 |
| 대상       | Spring Container가 관리하는 Bean에만 가능 | 모든 Java Object에 가능                                   |

## Spring AOP vs Spring Filter VS Interceptor

![](https://dejavuhyo.github.io/assets/img/2021-07-15-spring-filter-interceptor-aop-differences/flow.png)

Filter와 Interceptorsms Servlet 단위에서 실행

AOP는 Spring 메서드 앞에 Proxy 패턴으로 실행

실행순서: Filter → Interceptor → AOP → Interceptor → Filter

적용방식

- Filter: web.xml
- Interceptor: servlet-context.xml
- Aop: 스프링 애플리케이션 내부 코드
