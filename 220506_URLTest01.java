//URL클래스 : 인터넷에 존재하는 서버들의 자원에 접근할 수 잇는 주소를 다루는 클래스
//ex)http://ddit.or.kr:80/index.html?ttt=123
//프로토콜 ://호스트명(도메인명)/경로명/파일명?쿼리스트링#참조


package ko.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest01 {

	public static void main(String[] args) throws MalformedURLException {
//		방법1
		URL url1 = new URL("http://www.ddit.or.kr:80/index.php?ttt=123");

//		방법2
		URL url2= new URL("http","www.ddit.or.kr",80,"index.php?ttt=123");
		
		System.out.println("Protocol : "+ url2.getProtocol());
//		Protocol : http

		System.out.println("HostName : "+ url2.getHost());
//		HostName : www.ddit.or.kr
		
		System.out.println("Port : " + url2.getPort());
//		Port : 80
		
		System.out.println("Path : "+url2.getPath());
//		Path : index.php
		
		System.out.println("Qury : " +url2.getQuery() );
//		Qury : ttt=123
		
		System.out.println("전체 : "+url2.toExternalForm());
//		전체 : http://www.ddit.or.kr:80index.php?ttt=123
		
		
		
		
	}

}
