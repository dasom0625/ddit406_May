//URLConnection 클래스 (추상클래스): 애플리케이션과 URL간의 통신 연결을 위한 클래스이다.

package ko.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
//		<특정 서버의 정보와 파일 내용을 가져와 출력하는 연습>
		URL url = new URL("https://www.naver.com/index.html");

		// 1. URLConnection 객체 구하기=>이걸 사용하면, URL에 연결이 되어 있는 상태가 됨.
		URLConnection urlCon = url.openConnection();

		// 2. 헤더 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();

		// 3. 헤터정보 출력하기
		for (String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));

		}
		System.out.println("-----------------------------------------------------");
//
//출력값 : 
//	Transfer-Encoding : [chunked]
//			null : [HTTP/1.1 200 OK]
//			Server : [NWS]
//			Connection : [keep-alive]
//			Pragma : [no-cache]
//			P3P : [CP="CAO DSP CURa ADMa TAIa PSAa OUR LAW STP PHY ONL UNI PUR FIN COM NAV INT DEM STA PRE"]
//			Date : [Fri, 06 May 2022 07:07:08 GMT]
//			Referrer-Policy : [unsafe-url]
//			X-Frame-Options : [DENY]
//			Strict-Transport-Security : [max-age=63072000; includeSubdomains]
//			Cache-Control : [no-cache, no-store, must-revalidate]
//			Set-Cookie : [PM_CK_loc=a58af8e4e422f538117224dd0993389af5b9b3cda0bec91ad92decc4b9cfb4ed; Expires=Sat, 07 May 2022 07:07:08 GMT; Path=/; HttpOnly]
//			X-XSS-Protection : [1; mode=block]
//			Content-Type : [text/html; charset=UTF-8]
//			-----------------------------------------------------
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//		<해당 URL에 설정된 문서를 일겅와 출력하기 => index.html문서의 내용을 읽어온다는 의미>
		//방법 1. URLConnection객체를 이용하기
		//1) 입력하기
/*		InputStream is = urlCon.getInputStream(); //URLConnection 객체를 이용하여 입력용 스트림 객체 구하기.
		InputStreamReader isr = new InputStreamReader(is,"utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//2)자료를 읽어와서 출력하기
		while(true) {
			String str = br.readLine();//줄단위로 자료를 읽어오기(안그럼 한글자씩 나옴..)
			if(str == null) {	//str이 null이면 더이상 읽어올 자료가 없다는 의미.(읽어온 자료가 마지막인지 검사하는 단계)
				break;
			}
			System.out.println(str);//읽어온 자료는 출력
		}
		br.close();//다 썻으니 닫아주기
		
//출력값 : HTML소스가 다 읽어져서 콘솔창에 그대로 나온다. 
*/		
//-------------------------------------------------------------------------------------------------------------

		//방법 2. URL객체의 openStream()메서드 사용하기
		
		//1)입력하기
		InputStream is2 = url.openStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2,"utf-8"));
		
		//2)자료 읽어와서 출력하기
		while(true) {
			String str = br2.readLine();//줄단위로 자료를 읽어오기(안그럼 한글자씩 나옴..)
			if(str == null) {	//str이 null이면 더이상 읽어올 자료가 없다는 의미.(읽어온 자료가 마지막인지 검사하는 단계)
				break;
			}
			System.out.println(str);//읽어온 자료는 출력
		}
		br2.close();//다 썻으니 닫아주기

//출력값 : 방법1과 동일하게 HTML소스가 다 읽어져서 콘솔창에 그대로 나온다.
		
	}

}
