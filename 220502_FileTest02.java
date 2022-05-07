package kr.or.ddit.basic.file;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest02 {

	public static void main(String[] args) {

		
		//1. 파일의 크기 알아보기
		File f1 = new File ("d:/D_Other/test.txt");
		System.out.println(f1.getName()+"의 크기 : "+f1.length()+"byte(s)");
//		출력값 : test.txt의 크기 : 34byte(s)
////////////////////////////////////////////////////////////////////////////////////
		//2. Path알아보기(경로알아보기)
		System.out.println("path : "+f1.getPath());
		System.out.println("absolutePath : "+f1.getAbsolutePath());
//		출력값 : 
//		path : d:\D_Other\test.txt 
//		absolutePath : d:\D_Other\test.txt (상대경로를 절대경로로 변환해서 보여주는것)
		
////////////////////////////////////////////////////////////////////////////////////
		//3-1.File의 현재 위치 알아보기
		File f2 = new File(".");//.이 java에서의 현재 위치를 알려줌//또는 File f2 = new File("");로만 적어도 된다.
		System.out.println("path : "+f2.getPath());
		System.out.println("absolutePath : "+f2.getAbsolutePath());
//		출력값 : 
//		path : .
//		absolutePath : D:\workspace\javaIOtest\.
		
////////////////////////////////////////////////////////////////////////////////////
		//3-2. system.getProperty("suer.dir")명령을 통해 현재위치를 절대 경로로 변환하는 방법
	
		String path1 = System.getProperty("user.dir");
		System.out.println("현재 디렉토리 > "+ path1);
//		출력값 : 
//		현재 디렉토리 > D:\workspace\javaIOtest
		
////////////////////////////////////////////////////////////////////////////////////
		//3-3. system.getProperty("suer.dir")명령을 통해 상대경로에서 절대경로로 변환하는 방법
		
		Path relativePath = Paths.get("");
		String path2 = relativePath.toAbsolutePath().toString();
		System.out.println("현재 디렉토리 > "+path2);
//		출력값 : 
//		현재 디렉토리 > D:\workspace\javaIOtest
		
		
	}

}
