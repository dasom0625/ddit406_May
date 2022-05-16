//JavaDoc파일 만들기 예제
//연결 방법
//1. 패키지에서 오른쪽 마우스 클릭해서 export클릭
//2. javadoc클릭->next클릭
//3. configure에서 경로설정 ->next클릭
//4. 패키지 안에 doc폴더가 생긴것을 확인 할 수 있음
//5. doc폴더에서 마우스 우클릭 -> properties클릭 -> location 오른쪽에 버튼 클릭
//6. 파일탐색기가 뜨면 원하는 html파일 더블클릭.

/**
 * @author 홍길동
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설   명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정이력<br>
 * ---------------------<br>
 * 수정일 자 : 2022-05-16<br>
 * 작성자 : 홍길동<br>
 * 수정내용 : 최초생성<br>
 * ---------------------<br>
 * </p>
 * 
 */


package kr.or.ddit.basic;

public interface JavaDocTest {
	/**
	 * 메서드명 : methodTest<br>
	 * 설명 : 반환값이 없는 메서드<br>
	 * @param a 첫번째 매개변수(정수형)
	 * @param b 두번째 매개변수(정수형)
	 */
	public void methodTest(int a , int b);

	/**
	 * 메서드명 : methodAdd<br>
	 * 설명 : 정수형 두개를 이용하여 합계를 반환하는 메서드로, 반환값이 int인 메서드<br>
	 * @param num1 첫번째 정수형 데이터
	 * @param num2 두번째 정수형 데이터
	 * @return num1 과 num2의 합계 결과를 정수형으로 반환
	 */
	public int methodAdd(int num1, int num2);
	
	/**
	 * 메서드명 : methodSub<br>
	 * 설명 : 매개변수가 없고 반환값만 존재하는 메서드<br>
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();
}
