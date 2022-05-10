//서버는 클라이언트가 접속해서 보내온 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.

package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {

	//쌤방법
//	private ServerSocket server;
//	private Socket socket;
//	
//	private DataInputStream din;
//	private BufferedInputStream bin;
//	private BufferedOutputStream bout;
//	
//	private void serverStrat()
//	{
//		File saveDir = new File("d:/d_Other/연습용");
//		
//		if(!saveDir.exists()) {//저장될 폴더가 없으면..
//			saveDir.mkdir();//저장될 폴더를 만들어준다.
//			
//		}
//		try {
//			server = new ServerSocket(7777);
//			System.out.println("서버가 준비되었습니다.");
//			System.out.println();
//			
//			socket = server.accept(); //클라이언트의 요청(접속)을 기다린다.
//			
//			System.out.println("파일 다운로드를 시작합니다.");
//			
//			//접속이 되면 클라이언트가 첫번째로 보내느 '파일명'을 받아온다.
//			din = new DataInputStream(socket.getInputStream()); //파일명을 수신할 입력 스트림 객체 생성
//			
//			String fileName = din.readUTF();//파일명 받기
//			File file = new File(saveDir, fileName);//저장할 파일 위치오 ㅏ파일명을 지정하여 File객체 생성
//			
//			//소켓으로 들어오는 데이터를 받아서 파일로 저장하기
//			bin = new BufferedInputStream(din);
//			bout = new BufferedOutputStream(new FileOutputStream(file));
//			
//			byte[]temp = new byte[1024];
//			int len = 0;
//			while((len = bin.read(temp))>0) {
//				bout.write(temp,0,len);
//				
//			}
//			bout.flush();
//			System.out.println("파일 다운로드 완료");
//			
//			
//		} catch (Exception e) {
//			System.out.println("파일다운로드 실패!");
//			e.printStackTrace();
//		}finally {
//			if(bin!= null)try{bin.close();}catch(IOException e	) {}
//			if(bout!= null)try{bout.close();}catch(IOException e	) {}
//			if(socket!= null)try{socket.close();}catch(IOException e	) {}
//			if(server!= null)try{server.close();}catch(IOException e	) {}
//		}
//	}
//	
//
//public static void main(String[] args) {
//
//	new TcpFileServer().serverStrat();
//}

//내방법
	public static void main(String[] args) {
		
		
		try {
			//1. 서버소켓 만들기
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비중입니다...");
			System.out.println();
			
			Socket socket = server.accept(); //클라이언트의 접속을 기다림
			
			//2. 클라이언트가 보내온 펭귄파일 읽어오기
			DataInputStream din = new DataInputStream(socket.getInputStream());
			
			String str = din.readUTF();
			//3. 뱉어낼 파일(복사될 파일 스트림 객체)
			BufferedOutputStream bos = 
			new BufferedOutputStream(new FileOutputStream("d:/D_Other/연습용/"+str));
			
			System.out.println("복사시작");

			int data;//읽어온 데이터가 저장될 변수
			
			while((data = din.read())!=-1) {
				bos.write(data); //읽어온 데이터를 출력
			}
			
			
			//4. 스트림 닫기
			bos.flush();
			din.close();
			bos.close();
			
			System.out.println("복사 끝");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//클라이언트는 서버에 접속되면 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다.
//펭귄 파일을 읽어서 소켓으로 출력하면 됨...

package kr.or.ddit.basic.tcp;
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.net.Socket;

import java.awt.Panel;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class TcpFileClient {
	
//쌤방법
//	//클라이언트는 서버에 접속되면 'd:/02_HighJava/Other' 폴더에 있는 '핑구.jpeg'을 전송한다.
//	//파일을 읽어서 Socket으로 출력한다.
//
//	public class TcpFileClient {
//	   private Socket socket;
//	   private BufferedInputStream bin;
//	   private BufferedOutputStream bout;
//	   private DataOutputStream dout;
//	   
//	   public void clientStart() {
//	      //전송할 파일의 정보를 갖는 file객체 생성
//	      File file = new File("d:/02_HighJava/Other/핑구.jpeg");
//	      String fileName = file.getName(); //파일명 구하기
//	      if(!file.exists()) {//전송할 파일이 없으면
//	         System.out.println(fileName + " 파일이 없습니다.");
//	         return;
//	      }
//	      
//	      try {
//	         socket = new Socket("localhost", 7777);
//	         System.out.println("서버에 접속되었습니다.");
//	         System.out.println("파일 전송 시작");
//	         
//	         //서버에 접속하면 첫 번째로 전송할 파일의 파일명을 전송
//	         dout = new DataOutputStream(socket.getOutputStream()); //버퍼기능이 반감 됨. 그러면 느린 쪽에 속도가 맞춰짐. 그래서 버퍼용으로 한 번 감싸줌(46번 줄)
//	         dout.writeUTF(fileName);
//	         
//	         //파일 내용을 읽어서 소켓으로 전송하기
//	         
//	         //파일 읽기용 스트림 객체 생성
//	         bin = new BufferedInputStream(new FileInputStream(file));
//	         
//	         //서버로 전송할 출력용 스트림 객체 생성
//	         bout = new BufferedOutputStream(dout);
//	         
//	         //파일 내용을 읽어서 서버로 보내기
//	         byte[] temp = new byte[1024];
//	         int len = 0;
//	         
//	         while((len = bin.read(temp))>0) {
//	            bout.write(temp, 0, len); //출력
//	         }
//	         
//	         bout.flush(); //버퍼 사용했으므로
//	         
//	         System.out.println("파일 전송 완료!");
//	         
//	         
//	      } catch (Exception e) {
//	         System.out.println("파일 전송 실패!!!!!!!!!");
//	         e.printStackTrace(); //어디서 오류가 났는지 보기위해
//	      } finally {
//	         if(bin!=null) try {bin.close();} catch (IOException e2) {}
//	         if(bout!=null) try {bout.close();} catch (IOException e2) {}
//	         if(socket!=null) try {socket.close();} catch (IOException e2) {}
//	      }
//	      
//	      
//	      
//	      
//	   }
//
//	   public static void main(String[] args) {
//	      
//	      new TcpFileClient().clientStart();
//
//	   }
//
//	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//내방법	

	//파일을 보내는 역할을 하는 OutputStream
	private DataOutputStream dout;
	public static void main(String[] args) {

		// 1. 읽어오고자 하는 파일이 있는지 확인하기

//		File file = new File("d:/D_Other/펭귄.jpg");
		File file = new TcpFileClient().fileSelectDialog("Open");

		if (!file.exists()) {
			System.out.println(file.getPath() + "파일이 존재하지 않습니다.");
		}System.out.println("파일이 존재합니다. 파일을 보냅니다.");

		
////////////////////////////////////////////////////////////////////

		try {
			
			
			//2. 소켓 만들기
			Socket socket = new Socket("localhost",7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println();
			
			
			// 3. 파일 읽어오기
			FileInputStream fin = new FileInputStream(file);
			
			// 4. 데이터를 내보내...
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName());
			//5. 읽어온 데이터가 저장 될 변수를 만들고
			int data;
			
			//6. 읽어온 데이터를 출력하기위해 하나씩 담는다.
			while((data = fin.read())!=-1) {
				dos.write(data); //읽어온 데이터를 출력
			}
			
			System.out.println("파일을 보냅니다.");
			
			
			//7. 스트림 닫기
			fin.close();
			dos.close();
			
		} catch (Exception e) {
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//1.메서드 만들기
		public File fileSelectDialog(String option) {	//option은 "SAVE" 또는 "OPEN"값을 갖는다.//=>save를 만들거냐 open을 만들거냐 option을 줌.
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
			chooser.setCurrentDirectory(new File("d:/D_Other")); 


			//5. Dialog창 만들기
			int result;
			if("OPEN".equals(option.toUpperCase())) {
				result = chooser.showOpenDialog(new Panel());// 열기용 Dialog창
			}else if("SAVE".equals(option.toUpperCase())) {
				result = chooser.showSaveDialog(new Panel());//저장용 Dialog창
			}else {
				System.out.println("option은 SAVE 또는 OPEN만 가능합니다.");
				return null;
			}

			
			File selectedFile= null;
			//6. Dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장'버튼을 눌렀을 때 선택한 파일 정보 가져오기.
			if (result == JFileChooser.APPROVE_OPTION) { // 열기나 저장버튼 눌렀는지 여부 검사 //JFileChooser.APPROVE_OPTION : 열기나 저장버튼 눌렀는지임
				selectedFile = chooser.getSelectedFile(); // 7. 선택한 파일 가져오기.

			}
			
			
			
			return selectedFile;
			
			
			
		

	}
}

