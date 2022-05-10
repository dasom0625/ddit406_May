

package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class TcpMultiChatClient {

	
	//1.시작메서드 만들기
	public void clientStart() {
		Socket socket = null;
		
		//3. try-catch
		try {
			socket = new Socket("192.168.46.48",7777);
			System.out.println("서버에 연결되었습니다.");
			
//맨밑에서 올라오세요.....------------------------------------------------------------------------------------------//
			
			//23.전송용 쓰레드와 수신용 쓰레드를 생성하고 실행
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			//------------------------------------------------------------------------------------------//			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}//clientStrat()메소드 끝...
	
//////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		//2.시작메서드 실행
		new TcpMultiChatClient().clientStart();
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////	
	//4. 메시지 전송용 쓰레드
	class ClientSender extends Thread{
		//5.
		private Socket socket;
		private  DataOutputStream dout;
		private DataInputStream din;
		
		private String name; ///대화명이 저장될 변수
		//6.스캐너
		private Scanner scan; //이름 받기위해
		
		//7.생성자에서 소켓받기
		public ClientSender(Socket socket) {
			this.socket = socket;//소켓 초기화
			scan = new Scanner(System.in); //스캐너 생성
			
			//8. try-catch
			try {
				din = new DataInputStream(this.socket.getInputStream()); //9.수신용
				dout = new DataOutputStream(this.socket.getOutputStream()); //10.송신용
				
				if(dout!=null) {
					//11.클라이언트용 프로그램은 처음 실행하면 서버에 접속하고 접속이 성공하면,
					//	첫번재로 대화명을 입력받아 전송하고, "대화명"의 중복 여부를 응답으로 받아서 확인한다.
					
					do {//무한반복문
						System.out.println("대화명 : ");//12.대화명 입력받기
						String name  = scan.nextLine();
						
						dout.writeUTF(name);//13.입력받은 대화명을 전송( TcpMultiCahtServer의 din.readUTF()가 받는다.)
						
						String feedBack = din.readUTF();//14.서버가 보내온 "대화명"중복여부를 전달 받음
						
						if("대화명중복".equals(feedBack)) { //15.대화명이 중복될때..
							System.out.println(name + "은 이미 존재하는 대화명입니다.");
							System.out.println("다른 대화명을 입력해주세요.");
						}else {//16.대화명이 중복되지 않을 때,
							this.name = name;
							System.out.println("[ "+name+" ] 대화명으로 대화방에 입장했습니다.");
							break;
						}
						
					}while(true);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		}//생성자 끝...
		
	//17.전송용 쓰레드 시작
	@Override
	public void run() {

	//18.try-catch
		try {
			while(din!=null) {
				//19.키보드로 입력한 메세지를 서버로 전송한다.
				dout.writeUTF("[ "+name+" ] " + scan.nextLine()); //메세지 입력
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}//전송용 쓰레드 끝...
	
	}
	//20.메세지 수신용 쓰레드
	class ClientReceiver extends Thread{
		
		private Socket socket;
		private DataInputStream din;//메세지를 받아서 출력마나 하면되니까 이거만 있음 됨
		
		//21.생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝
		
		//22.run()메서드
		@Override
		public void run() {
			try {
				while(din!=null) {
					
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//run메소드 끝...
		
		
	}//수신용 끝...
		
	
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
public class TcpMultiChatServer {
	//2.필요한 객체들 만들기
	private Map<String, Socket> clientMap;	//접속한 클라이언트 정보를 저장할 map객체변수 선언 ( key 값  : 대화명, value값 : 접속한 클라이언트의 socket객체)
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	//3. 생성자
	private TcpMultiChatServer() {
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>()); //ClientMap을 동기화 처리가 되도록 생성한다.
		
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	//1. 서버의 시작 메소드
	public void serverStart() {
		//4.
		ServerSocket server = null;
		Socket socket = null; //소켓개체
		
		//5.try-catch만들기
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다..");
			
//			socket = server.accept();//이렇게 하면 클라이어트 한명만 들어 올 수 있다.
			
			//6.
			while(true) {	//무한반복문을 만들어 소켓을 만들어주면 여러 클라언트가 들어 올 수 있다.
				socket = server.accept();	//클라이언트의 접속을 기다린다.
				System.out.println("["+socket.getInetAddress()+" : "+ socket.getPort() + "]에서 접속했습니다.\n");//어디서 접속했는지 확인차 해봄
				
				
//맨밑에서 올라오세요.....------------------------------------------------------------------------------------------//
				
				//34.쓰레드 객체 생성 및 실행
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
//------------------------------------------------------------------------------------------//
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}//serverStart()메서드 끝...

	
	//7.나(clinetMap)에게 저장된 전체 사용자에게 메세지를 전송하는 메소드만들기
	private void sendToAll(String msg) {
		//8.clientMap의 데이터 개수만큼 반반복
		for(String name : clientMap.keySet()) {
			//9.try-catch
			try {
				//10.key값(대화명)에 대응하는 Socket객체의 출력용 스트림객체를 사용한다.
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());//데이터를 내보내야 하니까 out이고, 소켓(clientMap.get(name))의 outputstream(getOutputStream())...

				dout.writeUTF(msg);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}//sendToAll()메서드 끝...
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	public static void main(String[] args) {

		new TcpMultiChatServer().serverStart();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	
	//11.서버에서 클라이언트로 메시지를 전송하는 Thread를 Inner Class로 작성한다.
	//Inner Class(메인메서드와 동일한 선상에 만드는것)로 작성하는 이유는 Outer class의 멤버변수(clientMap)를 자유롭게 사용하기 위해서이다.
	class ServerReceiver extends Thread{
		//12.메세지를 받아서 보내기
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
	
		//13.생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;

			//14.
			try {
				//15.송신용 스트림 객체 생성
				dout = new DataOutputStream(this.socket.getOutputStream());
				//16.수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝
		
		
		//17.run()메서드
		@Override
		public void run() {
			//18.대화명이 저장될 변수를 선언
			String name = "";
			
			//19.try-catch
			try {
				//20.클라이언트가 연결에 성공하면 첫번째로 "대화명"을 입력받아 보낸다.
				//	서버에서는 이 "대화명"이 중복되는지의 여부를 검사해서 응답으로 클라이언트에게 보내준다.
				
				//클라이언트가 보내온 "대화명"이 중복되지 않을 때까지 검사를 확인한다.
				while(true) {//21. 클라이언트가 대화명을 계속해서 중복해서 보내올 경우를 대비하여 무한반복을 설정
					name = din.readUTF(); //22.클라이언트가 보내온 대화명 받기
					
					if(clientMap.containsKey(name)) {//23. key값에 대화명이 있는지 확인해주는것으로, 대화명이 중복되면 아래와 같이 메세지를 보낸다.
						dout.writeUTF("대화명이 중복됩니다. 다시 입력해주세요");
						
					}else {//24.대화명이 중복되지 않으면?
						dout.writeUTF("대화명이 등록되었습니다.");
						break;	//25. "대화명"이 중복되지 않으므로, 반복문 탈출
					}
				}//while문의 끝...
				
				//26.현재 접속한 "대화명"을 이용하여 다른 전체 클라이언테에게 대화방 참여 메세지를 보낸다.
				sendToAll("[ "+ name + " ] 이 대화방에 입장했습니다.");
				
				//27.대화명과 접속한 클라이언트의 Socket객체를 clientMap에 추가하기
				clientMap.put(name, socket);
				
				//28.현재 접속자 수 알려주기
				System.out.println("현재 접속자 수 : "+clientMap.size() + "명");
				
/////////////////////////////////////////////////////////////////////////////////////////29.여기까지가 한명이 입장하는 과정.
				
				//30.현재 클라이언트가 보낸 메세지를 받아서 전체 클라이언테에게 보낸다.
				while(din!=null) {
					sendToAll(din.readUTF());//현재 접속해있는사람들에게 모두 메세지를 보내는 것.
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			//31.finally를 만들어서 이곳이 실행시켜 현재 클라언트가 접속을 종료했다는것을 알려준다.
			finally {
				sendToAll("[ " + name + " ]님이 퇴장하였습니다.");
				
				//32.사용자 목록(clientMap)에서 퇴장한 해당 대화명을 삭제시킨다.
				clientMap.remove(name);
				
				//33.접속끊긴거 확인해보기
				System.out.println("["+socket.getInetAddress()+" : "+ socket.getPort() + "]에서 접속되어있습니다..\n");//접속 끊언거 확인
				System.out.println("현재 접속자 수 : "+clientMap.size() + "명");//현재 접속자 수 확인
			}
		
		}
		
		
	}

}

