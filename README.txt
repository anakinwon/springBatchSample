<배치 템플릿 만들기>

***. 환경 설정 .***
   - java : jdk 11 & Gradle Groovy
   - Spring Boot 2.7.13
   - Spring Batch 5.0.2
   - Database : MySQL 5.6, JPA 2.2
   - Schedule : quartz 2.3.0

<배치프레임워크 관련 테이블>
   - DDL.sql 파일에 있는 쿼리로 테이블 생성을 권장합니다.


0. 예제
    - MainTaskletJob    : 기본 템플릿 (Job -> Step)
    - DeptTableCopyJob  : 원천테이블(Dept)을 select해서, 목적테이블(Dept2) 에 insert 한다.

1. Dummy Reader -> Dummy Processor -> Dummy Writer
    - DummyToDummyBatch              : 완료 (20230628)

2. Memory Reader -> Text Processor -> Console Writer
    - MemoryToConsoleBatch           : Reader / Processor / Writer 분리해서 처리하기.
    - MemoryToFileDelimitedTxtBatch  : "|" 구분자로 Text파일 생성하기. (-> /files/out/customerw.txt)
    - MemoryToFileFormattedTxtBatch  : 고정길이 Text파일 생성하기.     (-> /files/out/clientFomatted.txt)

3. File Reader -> text Processor -> Console Writer
    - FileJsonToConsoleBatch         : JSON 파일 읽고 콘솔에 쓰기
    - FileDelimitedCsvToConsoleBatch : CSV 파일 읽고 콘솔에 쓰기
    - FileFormattedTxtToConsoleBatch :

4. DB Reader -> text Processor -> DB writer
    - DBToDBSynchronizedBatch        : 완료 (20230628)
    - DBToJsonFileBatch              : 완료 (20230628)
    - DBToXMLFileBatch               : 완료 (20230628)

5. DB to Json_File to DB
    - DBToJsonToDBBatch              : 완료 (20230629)


6. File Reader -> text Processor -> DB Writer
    - FileJsonToDBBatch
    - FileDelimitedToDBBatch
    - FileFormattedToDBBatch

7. 추가 할일
    - 스케줄 확정
    - 개별 실행 방법



<CLI에서 애플리케이션으로 실행하는 방식>
D:\JpaNQueryDsl\springbatch\build\libs>
 : java -jar springbatch-0.0.1-SNAPSHOT.jar name=anakin seq(LONG)=2L creatdDt(date)=2023-06-20 age(double)=3.14




<알고리즘 12개>

합계 알고리즘: SumAlgorithm
개수 알고리즘: CountAlgorithm
평균 알고리즘: AverageAlgorithm
최댓값 알고리즘: MaxAlgorithm, 결괏값, 변숫값
최솟값 알고리즘: MinAlgorithm
근삿값 알고리즘: NearAlgorithm, 가까운 값
순위 알고리즘: RankAlgorithm, 등수
정렬 알고리즘: SortAlgorithm, 선택 정렬(Selection Sort), Bubble Sort, Quick Sort, ...
검색 알고리즘: SearchAlgorithm, 순차 검색, 이진 검색(Binary Search; 이분 탐색)
병합 알고리즘: MergeAlgorithm, 병합 정렬
최빈값 알고리즘: ModeAlgorithm
그룹 알고리즘: GroupAlgorithm



<람다식 5가지 함수적 인터페이스>

1. Consumer (T, U) : 소비자
    - void로 리턴값이 없다.
    - 접미사 Consumer 사용
    - 추상메소드 : accept()

2. Supplier (공급자)
    - 매개변수가 없다.
    - 접미사 Supplier 사용
    - 추상메소드 : get(), getAsInt()

3. Function (함수)
    - 매개변수와 반환값이 있다.
    - 접미사 Function을 사용.
    - 추상메소드 : apply(), applyAsDouble()

4. Operator (연산자)
 - 매개변수와 반환값이 있다.
    - 접미사 Function을 사용.
    - 추상메소드 : apply(), applyAsDouble()

5. Predicate (논리자)

6. andThen() , compose()

7. and(), or(), negate()

8. isEqula()

9. 메소드 참조
   - 정적 메소드    :  클래스::메소드   = 클래스명으로 직접 호출이 가능하다.
   - 인스턴스 메소드 :  참조변수::메소드 = 반드시 new로 참조변수(인스턴스)를 만들어야 호출이 가능하다.
   - Math::min                    <--  (x, y) -> Math.min(x, y)

10. 매개변수의 메소드 참조
   - ToIntBiFunction
   - 클래스::compareToIgnoreCase;  <-- (a, b) -> a.compareToIgnoreCase(b);

11. 생성자 참조
   - 클래스:: new                  <--   (a, b) -> { return new 클래스(a, b) }


<java 중급 강좌 >

1. 클래스와 인스턴스의 개념과 활용
2. 메소드와 매개변수
3. 인스턴스 멤버와 정적 멤버간의 참조
4. 메서드 오버로딩
5. final필드와 static final필드
6. 생성자, this, this()와 인스턴스
7. 맴버변수, 싱글톤 패턴
8. 상속, Object클래스와 오버라이딩, super와 super()
9. 제어자와 다형성
10. 추상클래스와 인터페이스
11. 중첩 클래스
12. 예외처리



