//TCP 소켓 통신을 하기 위해 ServerSocket객체를 생성한다.


package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(7777);
		System.out.println("접속을 기다립니다...");

		//accept()메서드  
		//=> 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
		//=> 연결 요청이 들어오면 새로운 socket객체를 생성해서 클라이언트의 socket과 연결하여 반환한다.
		Socket socket = server.accept(); //여기 socket은 클라이언트와 연결된 socket이다.
		
		//accept()메서드 이후의 소스는 연결이 완료되어야만 실행되는 부분이다.
		System.out.println("클라으언트와 연결되었습니다..");
		System.out.println();
		//상대방과 연결이되면, 상대방의 정보를 알 수 있다.
		//접속한 상대방에 대한 정보 출력하기
		System.out.println("접속한 상대방 (클라이언트) 정보");
		System.out.println("IP주소 : "+socket.getInetAddress().getHostAddress()); //연결된 상대방의 IP주소
		System.out.println("Port번호 : " + socket.getPort()); //연결된 상대방의 Port번호
		System.out.println();
		 
		//현재 컴(서버쪽 컴퓨터의 정보)출력하기
		System.out.println("연결된 현재 컴(server) 정보");
		System.out.println("IP주소 : "+ socket.getLocalAddress()); //내꺼의 IP주소
		System.out.println("Port번호 : "+socket.getLocalPort());	//내꺼의 Port번호
		System.out.println();
		
		//클라이언트에게 메세지 보내기
		//=> Socket에 OutputStream객체를 이용해서 데이터를 전송한다.
		//	(Socket의 getOutputStream()메서드를 이용해서 OutputStream객체를 구할 수 있다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		//메세지보내기
		dout.writeUTF("여보세요 집에가고싶지 않아요?");
		System.out.println("메세지를 보냈습니다.");
		
		//소켓과 스트림 닫기
		socket.close();
		dout.close();
		server.close();
	}

}
