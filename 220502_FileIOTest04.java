//사용자가 입력한 내용을 그대로 파일로 저장하기.(Scanner를 사용하지 않고 입력하는 방법)

//System.in은 콘솔(표준입출력장치) 입력장치(즉, 키보드)이다.
//InputStreamReader는 입력용 바이트기반 스트림(InputStream)을 문자기반 스트림(Reader)으로 변환해주는 보조 스트림이다.



package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {

		//1.try-catch
		
		try {
			//2.입력용 
			InputStreamReader isr= new InputStreamReader(System.in);	//키보드로 입력받기 위해 System.in을 넣음
			
			//3.출력용 스트림
			FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");	//파일을 입력했는데, 파일이 없는 파일이면 새로 생성해주고, 기존에 있는 파일이면 기존 내용에 덮어쓰기때문에 조심해야 한다.
			
			System.out.println("아무내용이나 입력해주세요 \n(입력은 끝은 'Ctrl+Z 키' 입니다.) > ");
			
			int c;//4.입력한 값이 저장 될 변수
			
			//5.콘솔에서 입력할 때  입력의 끝은 'Ctrl + Z'키를 누르면 된다. 
			while((c=isr.read()) != -1) {
				fw.write(c);	//콘솔로 입력받은 내용을 파일에 출력한다.
			}
			
			//6.스트림 닫아주기.
			isr.close();
			fw.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

//출력값 : 
//콘솔창에 입력한 내용이 그대로 파일에 저장이 되는데, 이때 마지막 문장에서 만드시 enter를 한번 더 하고 ctrl+z를 해야 마지막 내용이 잘리지 않는다.
//콘솔창에 입력한 내용이 d:/D_Other/testChar.txt에 저장이 잘 되었는지 확인해보면 됨!
