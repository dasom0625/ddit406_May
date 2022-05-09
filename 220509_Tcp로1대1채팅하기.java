
//이 클래스(쓰레드)는 Socket을 통해서 메시지를 보내는 역할을 담당한다.

package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
	
	//메세지를 보낼꺼니까 socket이 필요하다
	private Socket socket;
	
	//메세지를 보내는 역할을 하는 outputstream
	private DataOutputStream dout;
	
	//누가보냈는지 
	private String name;
	
	//입력해서 보내야 하니까 Scanner도 있어야 함.
	private Scanner scan;
	
	//생성자에서 
	public Sender(Socket socket) {
		this.socket = socket;
		scan = new Scanner(System.in);
		
		//이름 입력받기
		System.out.println("이름  : " );
		name = scan.nextLine();
		
		try {
			//DataOutputStream생성
			dout = new DataOutputStream(this.socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
////////////////////////////////////////////////////////////////////////////////
	//쓰레드에서 할 일
	@Override
	public void run() {
		while(dout!=null)	{ //null이 아닐떄까지 출력
			try {
				dout.writeUTF(name + " : " + scan.nextLine());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////

//이 클래스(쓰레드)는 소켓을 통해서 메세지를 아서 화면에 출력만 하는 역할을 담당한다.


package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

public class Receiver extends Thread {

	private Socket socket;
	private DataInputStream din;
	
	//생성자
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			din = new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
/////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {

	while(din !=null) {
		try {
			System.out.println(din.readUTF());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	}	
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////


//Socket객체를 생성하여 서버에 연결 요청을 보내고
//연결이 완료되면 이 Socket을 메세지를 받는 쓰레드와 메세지를 보내는 쓰레드에 주입한다.
package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient02 {

	public static void main(String[] args) {

		
		try {
			Socket socket = new Socket("192.168.46.19",7777); //new Socket(서버의 ip주소 ,port번호);
			System.out.println("서버에 연결되었습니다..");
			System.out.println();

			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}

/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////



//ServerSocket 객체를 생성하고 클라이언트가 접속해 오면 
//연결된 Socket을 메세지를 받는 쓰레드와 메세지를 보내는 쓰레드에 주입한다. 


package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) throws IOException {
		
		
		//서버 socket만들기
		ServerSocket server = new ServerSocket(7777);

		System.out.println("서버가 준비중입니다...");
		System.out.println();
		
		Socket socket = server.accept(); //클라이언트의 접속을 기다린다.
		
		//접속이 되면 연결된 Socket객체를 주입한 쓰레드를 생성하여 실행한다.
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		//쓰레드 작동
		sender.start();
		receiver.start();
	}

}
