//InetAddress클래스 : IP주소를 다루기 위한 클래스


package ko.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		
		//1. www.naver.com 의 IP정보가져오기
		InetAddress naverIP = InetAddress.getByName("www.naver.com");//InetAddress.getByName(IP를 구하고싶은 사이트 주소)
	
		System.out.println("Host Name : " + naverIP.getHostName());
//		출력값 :
//		Host Name : www.naver.com

		System.out.println("Host Address : " + naverIP.getHostAddress());
//		출력값 : 
//		Host Address : 223.130.200.107
		
		System.out.println("toString : " + naverIP.toString());
//		출력값 : 
//		toString : www.naver.com/223.130.200.107
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//2.자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		
		System.out.println("내 컴퓨터의 HostName : " + localIP.getHostName());
//		출력값 : 
//		내 컴퓨터의 HostName : Surface-Dasom
		
		System.out.println("내 컴퓨터의 HostAddress : " + localIP.getHostAddress());
//		출력값 :
//		내 컴퓨터의 HostAddress : 192.168.36.15
		
		System.out.println("toStirng : " + localIP.toString());
//		출력값 :
//		toStirng : Surface-Dasom/192.168.36.15
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		//3.IP주소가 여러개인 호스트의 정보 가져오기.
		InetAddress[] ips = InetAddress.getAllByName("www.naver.com");
		
		for(InetAddress ip : ips) {
			System.out.println(ip.toString());
		}
//		출력값
//		www.naver.com/223.130.195.95
//		www.naver.com/223.130.195.200
		
		
		
		
		
	}

}
