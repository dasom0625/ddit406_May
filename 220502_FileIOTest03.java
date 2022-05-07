//문자 기반의 스트림을 이용한 파일 내용 읽어오기



package kr.or.ddit.basic;
import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {

		//1.try-catch블럭 만들기
		try {
			//2.문자 기반의 스트림을 이용한 파일 내용 읽어오기 위해 문자기반의 입력용 스트림 객체 생성(FileReader , FileWriter사용)
			FileReader fr = new FileReader("d:/D_Other/test.txt");
			
			int c ;
			
			while((c=fr.read()) != -1) {
				System.out.print((char)c);
			}
			fr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
//출력값 => d:/D_Other/test.txt파일에 입력되어있는 문장들을 consol로 읽어온다. 
//I want go home!!
//안녕?반가워!^_^
