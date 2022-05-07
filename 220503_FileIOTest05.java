// 한글이 저장된 파일 읽어오기 (한글의 인코딩 방식을 지정해서 읽어온다.)
package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {

////1.
////글씨가 저장되어이는 파일을 불러올땐 문자기반의 스트림 사용(2byte씩 읽어불러옴)
////기본 인코딩 방식으로 읽어들인다. 
////기본 인코딩 방식=> 자바프로그램의 소스의 인코딩방식을 따라간다.
//
//		try {
//
//			FileReader fr = new FileReader("d:/D_Other/test_utf8.txt"); // 인코딩방식이 utf8(한글이 깨지지 않음)
//
//			// 바이트 기반의 스트림을 문자 기본스트림으로 변화하는 보조스트림 객체 생성
//			// 기본 인코딩방식으로 읽어온다.
//
//			int c;
//			// 2.
//			while ((c = fr.read()) != -1) {
//				System.out.print((char) c);
//			}
//			fr.close();
//
//		} catch (IOException e) {
//			// TODO: handle exception
//		}
//
//	}
//
//}
//
////////////////////////////////////////////////////////////////////////////////////////////////////////
//
////3.
//		try {
//
//			// 기반이 되는 스트림 객체 생성(file에서 데이터를 읽으려고 하면서 byte기반의 스트림을 만드는것)
//			FileInputStream fin = new FileInputStream("d:/D_Other/test_utf8.txt");
//
//			// 바이트 기반의 스트림을 문자 기본스트림으로 변화하는 보조스트림 객체 생성
//			// 기본 인코딩방식으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fin); // 읽어드리는 스트림(출력하는건 OutputStreamWriter)
//
//			int c;
//
//			while ((c = isr.read()) != -1) {
//				System.out.print((char) c);
//			}
//			isr.close(); // 보조스트림을 닫으면 함께 사용한 기반이 되는 스트림도 같이 닫힌다.
//		} catch (IOException e) {
//			// TODO: handle exception
//		}
//
//	}
//
//}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////

//3.인코딩 방식을 지정해서 읽어오기
//인코딩 방식 예제
//-MS949 (CO949) => 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다)
//-UTF-8		 => 유니코드 UTF-8 인코딩 방식
//-US-ASCII		 => 영문전용 인코딩 방식

		try {

			// 1.ANSI방식으로 인코딩하여 읽어들여오기
//	FileInputStream fin = new FileInputStream("d:/D_Other/test_ansi.txt");	//읽어오기
//	InputStreamReader isr = new InputStreamReader(fin,"MS949");	//인코딩

			// 1.UTF8로 인코딩하여 읽어들여오기
			FileInputStream fin = new FileInputStream("d:/D_Other/test_UTF8.txt"); // 읽어오기
			InputStreamReader isr = new InputStreamReader(fin, "UTF-8"); // 인코딩

			int c;

			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
			}

			isr.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
