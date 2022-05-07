/*
 * 문제)
 * 'd:/D_Other'폴더에 잇는 '펭귄.jpg'파일을 
 * 'd:/D_Other/연습용'폴더에 '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오.
 */


//문자가 아닌 것들은 모두 byte기반의 스트림을 사용하면된다. (문자빼고 모두 가능하다고 생각하면된다.)
package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {

	public static void main(String[] args) {

		//1.읽어오고자 하는 펭귄 파일이 있는지 먼저 확인
		File file = new File("d:/D_Other/펭귄.jpg");
		
		if(!file.exists()) {	//file이 존재하는지 확인 이게 참이면 아래가 실행됨
			System.out.println(file.getPath()+ " 파일이 없습니다.");
			System.out.println("복사작업을 중지합니다.");
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		try {
			//읽어올 파일(원본 파일 스트림 객체)
			FileInputStream fin = new FileInputStream("d:/D_Other/펭귄.jpg");
			//뱉어낼 파일(복사될 파일 스트림 객체)
			FileOutputStream fos = new FileOutputStream("d:/D_Other/연습용/펭귄_복사본.jpg");
			
			System.out.println("복사시작...");
			
			int data; //읽어온 데이터가 저장될 변수
			
			while ((data = fin.read()) != -1) {
				fos.write(data);	//읽어온 데이터를 출력!
			}
			
			//스트림 닫기
			fin.close();
			fos.close();
			
			System.out.println("복사 끝...");
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//출력값 : 
//	d:\D_Other\연습용\펭귄_복사본.jpg가 생성된것을 확인 할 수 있다.
