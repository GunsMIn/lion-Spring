# ✨Spring_STUDY
✔멋쟁이사자 스프링 학습 관련 레파지토리입니다.✔

<img width="900" alt="___________2017-02-22______5 47 08" src="https://user-images.githubusercontent.com/104709432/198947114-5f7b3711-53b9-415c-9f38-f95072dc1cd4.png">

<br>
<img src="https://img.shields.io/badge/Java-E34F26?style=flat&logo=Java&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/></a>
<img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=JUnit5&logoColor=white"/></a>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/></a>
<hr>
<b>📝파일명 : 프로젝트 진행 내용</b><br>
- refactorting : 관심사 분리를 위한 추상클래스 도입 -> 인터페이스 도입 - > 팩토리 도입  <br>
- lastRefactoring의 UserDaoTest  : @BeforeEach 사용  <br>
<hr>
<b>🎈학습 내용</b><br>
<b>-팩토리 패턴</b> :  팩토리 클래스의 주 목적은 객체의 생성방법을 결정하고 그렇게 만들어진 오브젝트를 돌려주는 것이다.
    다른 말로 말하자면 오브젝트를 생성하는 쪽과 사용하는 쪽의 역할과 책임을 깔끔하게 분리하려는 목적으로 사용한다.
    그럼 DaoFactory는 객체를 생성하는(정하는) 클래스. UserDaoTest는 객체를 사용하는 클래스이다.<br>
<b>-tdd시 주의점</b> :  언제 실행해도 동일한 결과가 나오게끔 구성해야함. 또한 결과값이 없을때는 해당 exception을 확인하는 Assertions.assertTrows메소드를 사용해야한다
     각각의 테스트에서 공통으로 사용하는 부분은 메소드 위에 @BeforEach를 붙여주어서 @Test가 실행되기전에 각각 실행되게 만들어준다..<br>
