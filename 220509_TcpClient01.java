//서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성
//Socket객체는 생성이 완료되면 자동으로 해당서버로 연결요청 보낸다.

//현재 자신의 컴퓨터를 나타태는 방법 : 
//1) 원래의 IP주소 : 예) 192.168.146.6
//2) 지정된 IP주소 : 예)127.0.0.1
//3) 원래의 컴퓨터 이름 : 예)SEM-PC
//4) 지정된 컴퓨터 이름 : 예)localhost
package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {

		//1.
		Socket socket = new Socket("localhost",7777);	//(내컴퓨터,포트번호)
		
		//2.이 socket객체를 생성하는 명령이후의 내용은 서버와 연결된 후에 실행되는 부분이다.
		System.out.println("서버와 연결되었습니다...");
		System.out.println();
		
		System.out.println("접속한 상대방(서버) 정보");
		System.out.println("IP주소 : "+socket.getInetAddress().getHostAddress()); //연결된 상대방의 IP주소
		System.out.println("Port번호 : " + socket.getPort()); //연결된 상대방의 Port번호
		System.out.println();
	
		//현재 컴(서버쪽 컴퓨터의 정보)출력하기
		System.out.println("연결된 현재 컴(클라이언트) 정보");
		System.out.println("IP주소 : "+ socket.getLocalAddress()); //내꺼의 IP주소
		System.out.println("Port번호 : "+socket.getLocalPort());	//내꺼의 Port번호
		System.out.println();
		
		//서버에서 보내온 메세지를 받아서 화면에 출력하기
		//=>Socket객체의 InputStream객체를 이용해서 데이터를 수신받는다.
		//	(Socket의 getInputSteam()메서드를 이용하여 InputStream객체를 구할 수 있다.)
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);

		//메세지 받아서 출력하기
		System.out.println("서버에서 보내온 메시지 : "+ din.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다...");
		//소켓과 스트림 닫기
		din.close();
		socket.close();
		
	}

}
