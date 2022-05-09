//서버는 클라이언트가 접속해서 보내온 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.

package kr.or.ddit.basic.tcp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {

	public static void main(String[] args) {
		
		try {
			//1. 서버소켓 만들기
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비중입니다...");
			System.out.println();
			
			Socket socket = server.accept(); //클라이언트의 접속을 기다림
			
			//2. 클라이언트가 보내온 펭귄파일 읽어오기
			DataInputStream din = new DataInputStream(socket.getInputStream());
			//3. 뱉어낼 파일(복사될 파일 스트림 객체)
			BufferedOutputStream bos = 
			new BufferedOutputStream(new FileOutputStream("d:/D_Other/연습용/펭귄_복사본.jpg"));
			
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
			// TODO: handle exception
		}
		
		
	}

}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


//클라이언트는 서버에 접속되면 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다.
//펭귄 파일을 읽어서 소켓으로 출력하면 됨...

package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class TcpFileClient {

	//파일을 보내는 역할을 하는 OutputStream
	private DataOutputStream dout;
	public static void main(String[] args) {

		// 1. 읽어오고자 하는 파일이 있는지 확인하기

		File file = new File("d:/D_Other/펭귄.jpg");

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
			FileInputStream fin = new FileInputStream("d:/D_Other/펭귄.jpg");
			
			// 4. 데이터를 내보내...
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			//5. 읽어온 데이터가 저장 될 변수를 만들고
			int data;
			
			//6. 읽어온 데이터를 출력하기위해 하나씩 담는다.
			while((data = fin.read())!=-1) {
				dos.write(data); //읽어온 데이터를 출력
			}
			
			System.out.println("파일을 보냅니다.");
			
			
			//4. 스트림 닫기
			fin.close();
			dos.close();
			
		} catch (Exception e) {
		}

	}

}
