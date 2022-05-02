//★★★★★★★★★★★★입출력의 가장 기본적인 흐름 2★★★★★★★★★★★★★★★★★★★


package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		
		//1.
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};	
		byte[] outSrc = null;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//2.byte배열만들기
		byte[]temp = new byte[4]; //4개짜리의 byte배열 생성
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		//3.try-catch생성
		try {
			
			//4.읽어올 데이터가 있는 동안 반복한다.
			while(input.available()>0) { //input.available() =>남아있는 읽어올 데이터의 갯수	//즉, input배열안에 10개의 데이터가 있는데 그 갯수가 0이상일때 반복한다는 뜻.
				input.read(temp);	//input.read(byte배열)=>byte의 배열이 4개짜린데, input배열안의 10개의 데이터를 temp갯수(4개씩)만큼 읽어오라는 뜻.
				output.write(temp); //temp를 그대로 출력하라
				System.out.println("반복문 안에서의 temp 값 > "+Arrays.toString(temp));//temp라는 배열의 값이 뭐가 들어가있는지 확인하기 위해 출력
//				출력값 : 
//				반복문 안에서의 temp 값 > [0, 1, 2, 3]
//				반복문 안에서의 temp 값 > [4, 5, 6, 7]
//				반복문 안에서의 temp 값 > [8, 9, 6, 7]	=>input안에 10개배열밖에 없는데 4개씩 읽으면 8.9밖에 없어서 두개가 부족하기때문에 그전에 출력했떤 4,5,6,7에서 6,7이 남아있어서 그걸 출력함.
			}
			outSrc = output.toByteArray();
			
			System.out.println();
			System.out.println("inSrc > "+Arrays.toString(inSrc));
			System.out.println("outSrc > "+Arrays.toString(outSrc));
//			출력값 : 
//			inSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//			outSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7] =>위의 temp에서 마지막에 8,9,6,7이 출력되어 여기는 총 12개의 배열이 나와버림...이러면 안되요

			//5. 스트림 닫기
			input.close();
			output.close();
					
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-----------------------------------------------------------------------------------------------------------------------------------------------------------------
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
위와 같이 나오지 않고 제대로 의도한대로 나오게 하려면???
=>



//★★★★★★★★★★★★입출력의 가장 기본적인 흐름 2★★★★★★★★★★★★★★★★★★★


package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {

		
		//1.
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};	
		byte[] outSrc = null;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//2.byte배열만들기
		byte[]temp = new byte[4]; //4개짜리의 byte배열 생성
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		//3.try-catch생성
		try {
			
			//4.읽어올 데이터가 있는 동안 반복한다.
			while(input.available()>0) { //input.available() =>남아있는 읽어올 데이터의 갯수	//즉, input배열안에 10개의 데이터가 있는데 그 갯수가 0이상일때 반복한다는 뜻.
//				input.read(temp);	//input.read(byte배열)=>byte의 배열이 4개짜린데, input배열안의 10개의 데이터를 temp갯수(4개씩)만큼 읽어오라는 뜻.
//				output.write(temp); //temp를 그대로 출력하라
				
				int len = input.read(temp);	//실제 읽어온 배열의 갯수만큼 출력함. (즉, 12개가 아닌 10개만 출력하게 하려고함)
				output.write(temp,0,len);	//temp에서 0번째부터 len의 갯수(4)만큼 출력하라는 뜻. 그래서 마지막 8,9를 출력할때는 0번째부터 두개를 출력하는것과 같다.
											//그래서 아래의System.out.println("outSrc > "+Arrays.toString(outSrc));를 출력해보면 inSrc와 갯수가 동일하게 나옴!(it's Correct~!)
				System.out.println("반복문 안에서의 temp 값 > "+Arrays.toString(temp));//temp라는 배열의 값이 뭐가 들어가있는지 확인하기 위해 출력
				

			}
			outSrc = output.toByteArray();
			
			System.out.println();
			System.out.println("inSrc > "+Arrays.toString(inSrc));
			System.out.println("outSrc > "+Arrays.toString(outSrc));

			//5. 스트림 닫기
			input.close();
			output.close();
					
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

//			출력값 : 
//			반복문 안에서의 temp 값 > [0, 1, 2, 3]
//			반복문 안에서의 temp 값 > [4, 5, 6, 7]
//			반복문 안에서의 temp 값 > [8, 9, 6, 7] =>실제로 읽어온건 8,9만이고, 뒤에 6,7은 그냥 보여만 주는거임~!(아래 출력값을 보면 알 수 있음)

//			inSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//			outSrc > [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
