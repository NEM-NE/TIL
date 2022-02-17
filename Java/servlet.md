# Servlet

<br>

## 서론

<br>

    Next-Step에서 구현했었던 web-application-server가 사실은 servlet을 구현한 것이였고 서블릿에 대해 이해가 필요하다는 자바지기님의 말씀을 듣고 정리를 해보려고한다.

##### 링크 : [내가 구현한 web-application-server](https://github.com/NEM-NE/web-application-server/tree/refactor-sol)

<br>

## 왜 servlet인가?

<br>

서블릿은 자바를 사용하여 웹 페이지를 동적으로 생성해주는 서버측 기술입니다.
웹이 점점 발전함에 따라 정적인 페이지 뿐만아니라 동적으로 처리해줄 필요가 있어 등장하게 됐습니다.

서블릿은 동적인 웹페이지를 생성할 수 있게 "도와주는" 어플리케이션이며 "생성하는" 어플리케이션은 `CGI`입니다.

CGI => 별도의 프로그램과 서버간에 소통을 할 수 있게 해주는 교환방식 (Ex DB에서 서버간의 연결)

<br>

### 서블릿 특징

<br>

- 클라이언트 요청에 따른 응답을 제공해준다.
- html, css, js 등을 응답해 줄 수 있다.
- Java Thread를 이용하여 동작한다.
- Controller로 이용된다.
- HTTP 프로토콜을 지원하는 HttpServlet 클래스를 상속받는다.
- HTML 변경 시 Servlet을 재컴파일해야 하는 단점이 있다.

<br>

## Servlet Container

<br>

만들어준 서블릿을 동작하게 하려면 관리를 해줄 수 있는 것이 필요한데 이를 서블릿 컨테이너가 담당하고 있습니다.

    서블릿 컨테이너는 클라이언트의 요청(Request)을 받아주고 응답(Response)할 수 있게, 웹서버와 소켓으로 통신하며 대표적인 예로 톰캣(Tomcat)이 있습니다.

서블릿 컨테이너의 역할은 다음과 같습니다.

1. 웹서버와의 통신
   기초적인 서버 설정을 알아서 지원해주기 때문에 이용하기 편리함
2. 서블릿 생명주기 관리
   init(), service(), destory() 등으로 서블릿의 생명주기를 관리한다.
3. 멀티쓰레드 지원 및 관리
   요청이 올때마다 새로운 쓰레드를 생성 & 처리
4. 선언적 보안 관리
   보안 관리는 XML에 별도로 관리하기 때문에 수정할 필요 없음

<br>

#### 생명주기

![ㄴㄴ](https://t1.daumcdn.net/cfile/tistory/991870335A04292F0B)

1. 첫 요청이 들어오면 서블릿이 메모리에 있는지 확인하고 없을 경우 init() 호출
2. 호출이 되어 서블릿이 메모리에 할당이 되면 service()를 통해 doPost(), doGet()으로 분기됨.
3. 이 때 요청으로 들어온 HttpServletRequest, HttpServletResponse가 제공된다.
4. 컨테이너가 서블릿에 종료 요청을 하면 destory()가 호출

<br>

## Servlet Filter

<br>

    클라이언트로부터 요청을 받기 전에 서블릿을 거쳐서 필터링 하는 것을 서블릿 필터라고 한다.

인증, 로깅 등과 같은 공통적으로 필요한 기능들을 서블릿이 호출되기 전에 실행하거나 실행 후 적용하고 싶을 때 서블릿 필터 사용

Express 프레임워크에서의 미들웨어와 기능이 같다고 유사하다고 보면된다.

<br>

## Servlet Scope

<br>

![ss](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile6.uf.tistory.com%2Fimage%2F996FC3425B382DF53619C3)

1. JSP Page Scope

   JSP 페이지 내에서만 사용가능한 지역 변수
   PageContext.setAttribute(), PageContext.getAttribute() 등의 방법으로 사용
   forward(다른 서블릿이나 JSP로 요청을 넘기는 행위)를 하면 사라진다.

2. Request Scope

   클라이언트로부터 하나의 요청이 들어와서 서버가 일을 수행한 후 응답을 보낼 때까지, 계속 사용할 수 있는 Scope
   HttpServletRequest.setAttribute(), HttpServletRequest.getAttribute() 등의 방법으로 사용
   forward를 해도 유지가 된다.

3. Session Scope

   세션이 소멸될 때까지 변수를 유지. 여러 요청이 들어와도 유지된다.
   HttpServletRequest.getSession()을 통해 세션 객체를 얻는다. 이후 session.setAttribute(), session.getAttribute()을 사용

4. Application Scope

   어플리케이션이 소멸될 때까지 변수를 유지
   getServletContext()을 통해 객체를 얻고 이후 app.setAttribute(), app.getAttribute()을 사용
   모든 클라이언트가 공통으로 사용해야할 값들이 있을 때 사용한다.
