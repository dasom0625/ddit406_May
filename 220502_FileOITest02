//파일에 데이터 출력하기





package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {

		//1.tyr-catch만들기
		
		try {
		
			//2.출력용 File객체 및 스트림 객체만들기
			File f = new File("d:/D_Other/out.txt");
			FileOutputStream fos = new FileOutputStream(f); //()속에 대상이되는 file이름을 넣어줘야 합니다.(1. 지금처럼 file객체를 만든 후 이렇게 넣어주던지)
//			FileOutputStream fos = new FileOutputStream("d:/D_Other/out.txt); //(2.이렇게 한번에 선언하던지)
			
			for(char ch ='A'; ch<='Z'; ch++) {
				fos.write(ch);
			}
			System.out.println("출력 작업 완료...");
			fos.close();//스트림 닫기
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
//=> d:/D_Other/out.txt파일이 생성되면서, 그 파일안의 내용을 보면 ABCDEFGHIJKLMNOPQRSTUVWXYZ 가 입력되어있는것을 확인 할 수 있다.
