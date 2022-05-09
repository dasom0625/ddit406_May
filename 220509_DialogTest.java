//SWING의 파일 열기, 저장 창 연습



package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
		
		
		
//		//1.
//		JFileChooser chooser = new JFileChooser(); //이거 하나로 열기창, 저장창 다 만들수 있대...
//		
//		//2.선택할 파일의 확장자 설정
////		FileNameExtensionFilter txt = new FileNameExtensionFilter("보여줄 이름", "확장자");
//		FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일(*.txt)", "txt"); //확장자가 하나일때, 
//		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지파일", new String[] {"png","jpg","jif"}); //확장자가 여러가지일때 첫번재 방법- 배열
//		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS워드파일", "docx","doc");	//확장자가 여러가지일때 두번째 방법 -따옴표사용
//		
//		//3.설정된 확장자를 Chooser에 추가한다.
//		chooser.addChoosableFileFilter(txt);
//		chooser.addChoosableFileFilter(img);
//		chooser.addChoosableFileFilter(doc);
//		
//		//4. 
//		chooser.setFileFilter(txt);//확장자 목록 중 기본적으로 선택될 확장자(기본확장자) 지정
//		
//		//5. Dialog창 만들기
//		int result = chooser.showOpenDialog(new Panel());//열기용 Dialog창
////		int result = chooser.showSaveDialog(new Panel());//저장용 Dialog창
//		
//		//6. Dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장'버튼을 눌렀을 때 선택한 파일 정보 가져오기.
//		if(result == JFileChooser.APPROVE_OPTION) {	//열기나 저장버튼 눌렀는지 여부 검사 //JFileChooser.APPROVE_OPTION : 열기나 저장버튼 눌렀는지임 
//			File selectedFile = chooser.getSelectedFile();	// 7. 선택한 파일 가져오기.
//			
//			System.out.println("선택한 파일 : " +selectedFile.getAbsolutePath());
//			//////////이후에는 선택된 파일을 이용하여 '읽기작업'또는 '쓰기작업'을 구현하면 된다.
//		}
//	}
//
//}
//======================================================================================================================//
//출력 값 : 
//여기까지하면, 내가 만든 파일유형이 만들어진것을 볼수 있다. 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//1.
JFileChooser chooser = new JFileChooser(); //이거 하나로 열기창, 저장창 다 만들수 있대...

//2.선택할 파일의 확장자 설정
//FileNameExtensionFilter txt = new FileNameExtensionFilter("보여줄 이름", "확장자");
FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일(*.txt)", "txt"); //확장자가 하나일때, 
FileNameExtensionFilter img = new FileNameExtensionFilter("이미지파일", new String[] {"png","jpg","jif"}); //확장자가 여러가지일때 첫번재 방법- 배열
FileNameExtensionFilter doc = new FileNameExtensionFilter("MS워드파일", "docx","doc");	//확장자가 여러가지일때 두번째 방법 -따옴표사용

//3.설정된 확장자를 Chooser에 추가한다.
chooser.addChoosableFileFilter(txt);
chooser.addChoosableFileFilter(img);
chooser.addChoosableFileFilter(doc);

//4. 
chooser.setFileFilter(txt);//확장자 목록 중 기본적으로 선택될 확장자(기본확장자) 지정

//8.
chooser.setAcceptAllFileFilterUsed(false);//true로 기본설정되어있고, true일땐 파일 유형에 '모든파일'이 있지만, false로 바꾸고나면 파일 유형에 '모든파일'이 없어지고 내가 설정한 파일 유형들만 나타난다.

//9.Dialog창이 나타날때 보여주는 기본 경로 설정하기-바탕화면을 기본 경로로 설정하기
chooser.setCurrentDirectory(new File(System.getProperty("user.home")+"/desktop")); /////////////////////////////////////이렇게 설정하면, F11을 눌렀을떄 바로 보여지는 창이 바탕화면 경로가 보인다!
///////////////////////////////////// chooser.setCurrentDirectory(new File(d:/D_Other"));로 하면, F11을 눌렀을떄 바로 보여지는 창이 d:/D_Other경로로 보인다!



//5. Dialog창 만들기
int result = chooser.showOpenDialog(new Panel());// 열기용 Dialog창
//int result = chooser.showSaveDialog(new Panel());//저장용 Dialog창

//6. Dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장'버튼을 눌렀을 때 선택한 파일 정보 가져오기.
if (result == JFileChooser.APPROVE_OPTION) { // 열기나 저장버튼 눌렀는지 여부 검사 //JFileChooser.APPROVE_OPTION : 열기나 저장버튼 눌렀는지임
	File selectedFile = chooser.getSelectedFile(); // 7. 선택한 파일 가져오기.

	System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
	////////// 이후에는 선택된 파일을 이용하여 '읽기작업'또는 '쓰기작업'을 구현하면 된다.
}
}

}
