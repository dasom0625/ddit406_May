/*
ResourceBundle객체란?
=> 파일의 확장자가 properties인 파일의 내용을 읽어와 key값과 value값을 분리해서 정보를 갖고 있는 객체 
   (확장자가 properties인 파일만 가져 올 수 있다)

=> 읽어올 파일의 내용은 'key값=value값' 형태로 되어 있어야 한다.

 */


package kr.or.ddit.basic;

import java.util.ResourceBundle;

public class ResourceBundleTest {

	public static void main(String[] args) {

		//1.ResourceBundle객체를 이용하여 파일 읽어오기
		
		//1-1)ResourceBundle객체 생성 및 파일 읽어오기
		//		=>읽어올 파일을 지정할 때 '패키지명.파일명'만 가지고 확장자는 지정하지 않는다.
		//		  (이유 : 이 객체가 다루는 파일의 확장자는 항상 'properties'이기 때문
		ResourceBundle bundle 
		= ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");//파일이름은 확장자 빼고 이름만 적는다.
		
		//2. 읽어온 내용을 출력하기
		System.out.println("driver : " + bundle.getString("driver"));
		System.out.println("url : " + bundle.getString("url"));
		System.out.println("user : " + bundle.getString("user"));
		System.out.println("pass : " + bundle.getString("pass"));
		
	}

}
