//★★★★★★★★★★★★입출력의 가장 기본적인 흐름 1★★★★★★★★★★★★★★★★★★★

package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		//1.
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};	
		byte[] outSrc = null;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//2.스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);	//inSrc의 배열을 가지고 입력하겠다는 뜻
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//3.변수만들기
		int data;	//읽어온 자료가 저장될 변수
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//4.변수data를 통해 출력하기=>처음부터 마지막 자료까지 읽어서 출력하기위해 반복문 사용
		while((data = input.read()) != -1) { //input을 읽어서 data에 저장하고, 그 값이 -1과 같지 않으면(출력할 값이 없을떄가 -1) 계속 읽어오라는 듯
											//왜 -1과 비교하는가? => read()메서드는 더이상 읽어올 자료가 없으면 -1을 반환하기때문
			
			//5.이 반복문 안에서 읽어온 자료를 처리하는 명령을 기술하면 된다. 
			//읽어온 자료를 그대로 출력스트림으로 출력
			output.write(data);		//write가 출력할때쓰는 명령어이다. 
		}//이 반복문안에서 계산을 한다던지 이런 작업을 하면 된다.
		
		//6.output에서 출력된 스트림값을 배열로 변환하여 outSrc에 저장해준다.
		outSrc = output.toByteArray();
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//7.outSrc까지 완료되면 스트림을 try-catch로 닫아준다.(input.clsoe()를 작성하면 빨간줄이 뜨는데 거기서 surrounding try-catch클릭)
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//8. 출력해보기
		System.out.println("inSrc > "+ Arrays.toString(inSrc));
		System.out.println("outSrc > "+Arrays.toString(outSrc));
//		출력값 : 
//		inSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//		outSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	}

}
