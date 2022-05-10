package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {

		
		//1. 입력받아 보낼 스캐너
		Scanner scan = new Scanner(System.in);
		
		//2. 송신용, 수신용 피킷객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		//3. 수신받은 데이터가 저장될 byte형의 배열 선언
		byte[] bMsg = new byte[512];
		
		//4. try-catch
		try {
			DatagramSocket socket = new DatagramSocket();//5.소켓객체 생성
			
			//6. 접속할 곳의 IP정보 생성
			InetAddress address = InetAddress.getByName("localhost");
			
			//7. 무한반복문
			while(true) {
				
			//8.전송할 메세지 입력
			System.out.print("보낼 메세지 : " );
			String msg = scan.nextLine();
				
			//9.전송할 패킷 객체 생성
			outpacket = new DatagramPacket(msg.getBytes("utf-8"),msg.getBytes("utf-8").length,address,8888);	
			
			//10. 전송
			socket.send(outpacket);
			
			//11.반복문 (while(true)) 탈출
			if("/end".equals(msg)) {	//메세지 중지여부 검사
				break; //반복문 탈출
			}
			
///////////////////////////////////////////////////////////////////////////////////////////////
			//12.서버에서 보내온 메세지를 받아서 출력하기
			
			
			//13. 수신용 피킷객체 생성
			inpacket = new DatagramPacket(bMsg, bMsg.length);
			
			//14.수신
			socket.receive(inpacket);
			
			//15.
			System.out.println("서버의 응답 메세지 : " + new String(inpacket.getData(),0,inpacket.getLength(),"utf-8"));
			System.out.println();
			
			}
			//16.반복문이 끝나면...
			System.out.println();
			System.out.println("통신 끝.");
			socket.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
 
 UDP 방식 : 비연결 지여향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없음.
 		     그렇지만, TCP보다 속도가 빠르다
 필요한 객체 : DatagramSocket객체 	/ DatagramPacket객체
 		- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행 ( 우체부와 같은 역할 )
 		- DatagramPacket : 주고받는 데이터와 관려노딘 작업을 수행 ( 소포나 편지 )
 		=> 수신용 생성자와 송신용 생성자를 따롸 제공한다.
 		
 -TCP의 경우에는 주로 스트림을 이용해서 송수신하지만,
 -UPD의 경우에는 데이터그램을 이용해서 송수신한다. 
 */

package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

	public static void main(String[] args) {

		//1.try-catch
		try {
			//2. 통신할 포트번호를 지정하여 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			System.out.println("서버가 실행중...");
			
			//3. 수신용 패킷객체변수와 송신용 패킷객체변수 선언
			DatagramPacket inpacket, outpacket;
			
			//4.무한반복문으로 돌린다.
			while(true) {
				//5.클라이언트가 메세지를 보내면 그걸 받아서 화면에 출력하고 클라이언트한테 다시 보내줌.
				//	데이터가 저장될 byte형 배열 생성
				byte[]bMsg = new byte[512];//크기는 적당하게..
				
				//5-1.수신용 패킷객체 생성
				//		=>데이터가 저장될 바이트형 배열, 배열의 길이를 이용해서 생성한다. 
				inpacket = new DatagramPacket(bMsg, bMsg.length);//데이터가 저장될 바이트와 바이트의 크기
				
				//6.데이터를 수신 (receive()메서드를 사용)
				socket.receive(inpacket);//receive()메서드는 데이터(메세지)가 올때까지 기다림.
										//수신된 데이터의 패킷정보는 지정한 패킷변수(inpacket)에 저장된다.
				
				//7.수신받은 패킷객체를 이용해서 상대방의 주소, 포트번호등을 알 수 있다.
				InetAddress address = inpacket.getAddress();//상대방의 IP정보를 구하는 것.
				int port = inpacket.getPort();//상대방의 접속한 포트번호
				
				System.out.println("상대방의 IP정보 : "+ address);
				System.out.println("상대방의 port정보 : " + port);
				System.out.println();
				
				//8. 상대방이 보낸 메세지 출력하기
				//	inpacket.getLength()메서드 => 실제 읽어온 데이터(상대방이 보내온 메세지(데이터))의 길이를 반환함.
				//  inpacket.getData()메서드      => 실제 읽어온 데이터를 byte배열로 반환한다.
				//								(실제 읽어온 데이터는 수신용 패킷에 지정한 byte형 배열에도 저장된다.)
				//9. 데이터가 문자열이기 때문에 바이트형 데이터를 문자열(String)로 변환
				String msg = new String(bMsg,0,inpacket.getLength(),"utf-8");
//				또는 String msg = new String(inpacket.getData(),0,inpacket.getLength(),"utf-8");
				//(inpacket.getData()에 0번째부터 inpacket.getLength()길이만큼)

////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
				//13.이예제에서는 클라이언트가 보낸메시지가 '/end'이면 작업을 끝내도록 한다. 
				if("/end".equals(msg)) {
					break; //무한 반복문(while(true)) 탈출
				}
				
////////////////////////////////////////////////////////////////////////////////////////////////////
				
				System.out.println("상대방이 보낸 메세지 : "+ msg);
			
				
				//10. 상대방에게 메세지 보내기(수신받는 메세지를 그대로 송신)
				
				//10-1. 송신할 메세지를 byte형 배열로 변환
				byte[] sendMsg = msg.getBytes("utf-8");
				
				//11.송신용 패킷 객체 생성 => 전송할 데이터가 들어가 있는 byte형 배열, 전송할 자료의 길이 (배열의 길이 )
				//					    상대방 주소정보, 상대방의 포트번호를 지정하여 생성한다.
				outpacket = new DatagramPacket(sendMsg, sendMsg.length,address,port);

				//12. 송신하기 => send()메서드 사용
				//			=> 전송할 패킷을 넣어준다.
				socket.send(outpacket);
				System.out.println("송신완료");
				
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
