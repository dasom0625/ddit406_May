//[ File객체 만들기 연습 ]
//1. new File(string 파일 또는 경로)
//		=>디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
//		
//2. new File(File parent, String child)=>'parent'는 폴더고, parent폴더 안에있는'child'는 파일이다.
//3. new File(String parent,String child)=> 여기선 parent도 String으로 지정할수있다.

//4. 디렉토리(폴더)만들기
//-mkdir()  => file객체의 경로 중 마지막 위치의 디렉토리를 만든다.(폴더를 생성해줌!)
//          => 반환값 : 만들기 성공이면 true, 실패면 false
//-mkdirs() => 중간부분의 경로가 없으면 중간부분의 경로도 같이 만들어준다.
package kr.or.ddit.basic.file;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		
		//1. 
		File file1 = new File("d:/D_Other/test.txt");//구분문자를 '/'로 했을 때
		File file2 = new File("d:\\D_Other\\test.txt"); //구분문자를 '\'로 했을 때
		
		System.out.println("파일1 명 : "+file1.getName());
		System.out.println("파일2 명 : "+file2.getName());
//		=>출력 값 : 파일1 명 : test.txt
//			   	   파일2 명 : test.txt
		System.out.println("디렉토리인가 ? > "+file2.isDirectory());
		System.out.println("파일인가? > "+file2.isFile());
//		=>출력 값 : 디렉토리인가 ? > false
//				   파일인가? > true
		System.out.println();
		
		
		
		File file3 = new File("d:/D_Other");//폴더정보만 들어있음
		System.out.println("파일 3 명 : "+file3.getName());
//		=>출력 값 : 파일 3 명 : D_Other

		System.out.println("디렉토리인가 ? > "+file3.isDirectory());
		System.out.println("파일인가? > "+file3.isFile());
//		출력 값 : 디렉토리인가 ? > true
//			       파일인가? > false

//////////////////////////////////////////////////////////////////////////
		//2.
		System.out.println("==========================================");
		File file4 = new File(file3, "test.txt");
		System.out.println("파일 4 명 : "+file4.getName());
		System.out.println("디렉토리인가 ? > "+file4.isDirectory());
		System.out.println("파일인가? > "+file4.isFile());
//		=>출력값 : 파일 4 명 : test.txt
//				디렉토리인가 ? > false
//				파일인가? > true
	
//////////////////////////////////////////////////////////////////////////
		//3.
		File file5 = new File("d:/D_Other", "test.txt");
		System.out.println("파일 5 명 : "+file5.getName());
		System.out.println("디렉토리인가 ? > "+file5.isDirectory());
		System.out.println("파일인가? > "+file5.isFile());
//		=>출력값 : 파일 5 명 : test.txt
//				디렉토리인가 ? > false
//				파일인가? > true
//////////////////////////////////////////////////////////////////////////
		//4.
		File file6 = new File("d:/D_Other/연습용");
		System.out.println("파일 6 명 : "+file6.getName());
		System.out.println("디렉토리인가 ? > "+file6.isDirectory());
		System.out.println("파일인가? > "+file6.isFile());
		//출력값 : 
//		파일 6 명 : 연습용
//		디렉토리인가 ? > false
//		파일인가? > false
//								=>false인 이유는, 연습용이라는 폴더도 파일도 없기 때문이다.
		System.out.println(file5.getName()+"의 존재여부 > "+ file6.exists());
//		출력값 : test.txt의 존재여부 > false
		
		if(file6.mkdir()) {//파일 또는 폴더가 있는지 없는지의 존재여부를 확인하기 때문에 if문으로 주로 쓴다.
			System.out.println(file6.getName()+ " 만들기 성공!");
		}else {
			System.out.println(file6.getName()+ " 만들기 실패!");
		}
		
		if(file6.mkdir()) {//파일 또는 폴더가 있는지 없는지의 존재여부를 확인하기 때문에 if문으로 주로 쓴다.
			System.out.println(file6.getName()+ " 만들기 성공!");
		}else {
			System.out.println(file6.getName()+ " 만들기 실패!");
		}
//		출력값 : 연습용 만들기 성공! =>실제로 D드라이브 D_Other에 들어가면 연습용이라는 폴더가 생성되어있다.
	
		//보통, 아래와 같이 한번에 한다. (if문으로 file6의 존재여부 확인하고, 그안의 if문에서 파일 만들기)
		if(!file6.exists()) {	//해당부분이 참이되면, 아래가 실행됨.
			if(file6.mkdir()) {//파일 또는 폴더가 있는지 없는지의 존재여부를 확인하기 때문에 if문으로 주로 쓴다.
				System.out.println(file6.getName()+ " 만들기 성공!");
			}else {
				System.out.println(file6.getName()+ " 만들기 실패!");
			}
		}
		System.out.println("=====================================================================");
//////////////////////////////////////////////////////////////////////////
		//4-1.
			File file7 = new File("d:/D_Other/test/java/src");
			if(!file7.exists()) {	//이게 참이면 아래가 실행됨
				if(file7.mkdir()) {//파일 또는 폴더가 있는지 없는지의 존재여부를 확인하기 때문에 if문으로 주로 쓴다.
					System.out.println(file7.getName()+ " 만들기 성공!");
				}else {
					System.out.println(file7.getName()+ " 만들기 실패!");
				}
			}
//			출력값 : src 만들기 실패! =>왜 실패일까? 그 이유는, mkdir()은 마지막 폴더만 만들어주는데 중간의 test와 java폴더가 없기 때문이다.
//			그래서 중간에 없는 경로까지 만들어주려면 mkdirs() 를 사용해야 한다. 
			if (!file7.exists()) { 
				if (file7.mkdirs()) {
					System.out.println(file7.getName() + " 만들기 성공!");
				} else {
					System.out.println(file7.getName() + " 만들기 실패!");
				}
			}
//			출력값 : src 만들기 성공! 
	}

}
