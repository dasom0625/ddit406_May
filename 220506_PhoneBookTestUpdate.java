/*
 * 문제)
 * 이름, 주소, 전화번호를 멤버변수로 갖는 Phone클래스를 만들고, 
 * Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 * (Map의 구조는 key값으로 '이름'을 사용하고, value값으로는 'Phone 클래스의 인스턴스'를 사용한다.
 * =>HashMap<String,Phone>Phone = new HashMap<String,Phone>();
 * 
 * 추가조건 ) 
 * 			1)메뉴에' 6. 전화번호 정보 저장'메뉴를 추가하고 기능을 구현한다.=>저장된게 없을떄는 어떻게 보여줄지도 구현해야 한다.
 * 			  (이 때 저장파일명은 'phoneData.dat'로한다.)			
 * 			2)프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 세팅되도록 한다.
 * 				=>PhoneBookTest생성자에서 작업을 하라구요...?
 * 			3)프로그램을 종료 할 때 Map의 데이터가 변경되거나 추가 또는 삭제된 데이터가 있으면 저장한 후 종료되도록 한다.
 * 				=>boolean을 이용해서 map에 데이터가 있는지 없는지
 * 아래의 실행예시에 맞게 구현하시오.
 * 실행예시)
 * 	다음 메뉴에서 작업을 선택하세요.
 * 	1. 전화번호 등록
 *  2. 전화번호 수정
 *  3. 전화번호 삭제
 *  4. 전화번호 검색
 *  5. 전화번호 전체 출력
 *  0. 프로그램 종료
 *  ------------------
 *  번호 입력 >> 1
 *  
 *  새롭게 등록할 전화번호를 입력하세요.
 *  이름 : (입력받기)
 *  전화번호 : (입력받기)
 *  주소 : (입력받기)
 *  
 *  '(이름)'의 전화번호 등록 완료!
 *  
 *  -----------------
 *  (메뉴부분 다시 출력)
 *  .
 *  .
 *  .
 *  ==>전화번호 다시 등록할 때 이미 등록된 이름이 있으면, '(이름)'은 이미 등록된 사람입니다...라는 문구 출력하고 다시 메뉴로 가게 하기
 * 	==>삭제와 검색기능은 '이름'을 입력받아서 처리한다. 
 * 	==>수정 기능에서 '이름'은 변경하지 않는다.
 *  
 *  번호 입력 >> 5
 *  ===========================================
 *  번호		  이름		전화번호		    주소 
 *  ===========================================
 *  1		홍길동	010-1111-1111	대전시 중구
 */
package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {
	boolean totalcheck = false;

	//1. 입력받을 스캐너 만들기
	private Scanner sc = new Scanner(System.in);
	static HashMap<String,Phone> pMap = new HashMap<String,Phone>();
	Set<String> memList = pMap.keySet();

	public static void main(String[] args) {

		new PhoneBookTest().GameStart();
	}

///////////////////////////////////////////////////////////////////
	
	//1. 프로그램 시작하는 메서드
	private void GameStart() {
		inputData();
		
		//2. 종료할때 까지 while문 돌리기
		while(true) {
			int input = menu();	//3
			
			switch(input){
			case 1 : insert();	//전화번호 등록
//			if(totalcheck == true) {
//				save();
//				totalcheck = false;
//			}
			break;
			
			case 2 : update(); 	//전화번호 수정
//					if(totalcheck == true) {
//						save();
//						totalcheck = false;
//					}
					break;
			case 3 : delete();	//전화번호 삭제
//			if(totalcheck == true) {
//				save();
//				totalcheck = false;
//			}
			break;
			case 4 : search(); break;	//전화번호 검색
			case 5 : print(); break;	//전화번호 출력
			case 6 : save(); break; 	//전화번호 정보 저장
			case 0 : 					//프로그램 종료
				if(totalcheck == true) {
					save();
				}				
				
				System.out.println();
					System.out.println("이용해주셔서 감사합니다.");
					return;
			default: 
				System.out.println("선택번호를 벗어나셨습니다. 다시 선택해주세요.");
			}
		}
		
	}
///////////////////////////////////////////////////////////////////
//9.전화번호 정보 저장
private void save() {

	try {
		//출력용 스트림객체 생성
		ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/phoneData.dat")));
		
		//쓰기 작업
		System.out.println("전화번호 저장하기 시작..");
		for (String member : memList) {
			oout.writeObject(pMap.get(member));
		}
		oout.writeObject(null);
		     
		System.out.println("전화번호 저장 끝...");
		//저장까지 끝나면 스트림 닫기
		oout.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		
	}

///////////////////////////////////////////////////////////////////
private void inputData() {
	
	File file = new File("d:/D_Other/phoneData.dat");
	
	if(file.exists()) {	//file이 존재하는지 확인 이게 참이면 아래가 실행됨
	
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

			Object obj;
			try {
				while ((obj = ois.readObject()) != null) {
					Phone phone = (Phone) obj;
					pMap.put(phone.getName(), phone);
				}

			} catch (EOFException e) {
				System.out.println("객체 읽기 작업 끝...");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} else {
		System.out.println("새로운 전화번호부 만들기 시작");

	}
}
	


///////////////////////////////////////////////////////////////////
	//3.메뉴를 출력하고 작업한 것들을 반환하는 메서드
private int menu() {
	System.out.println();
	System.out.println("=================================================");
	System.out.println("< Phone Book >");
	System.out.println("-------------------------------------------------");
	System.out.println("1. 전화번호 등록");
	System.out.println("2. 전화번호 수정");
	System.out.println("3. 전화번호 삭제");
	System.out.println("4. 전화번호 검색");
	System.out.println("5. 전화번호 전체");
	System.out.println("6. 전화번호 정보 저장");
	System.out.println("0. 프로그램 종료");
	System.out.println("-------------------------------------------------");
	System.out.print("메뉴 선택 > ");
	return Integer.parseInt(sc.nextLine()); //그냥하면 엔터까지 가져와버리니까....String으로 받고 int로 변환해줌.
	}	//완료

///////////////////////////////////////////////////////////////////
	//4. 전화번호 등록하는 메서드
	private void insert() {

		System.out.println();
		System.out.println("<< 전화번호 등록 >>");
		System.out.println("-------------------------------------------------");
		System.out.print("이름 : ");
		String name = sc.nextLine();

		//내방법
		for (String memName : memList) {
			if (name.equals(memName)) {
				System.out.println(" ※ ''" + name + "' 은 이미 등록되어있습니다.");
				return;
			}
		}
		//쌤의 등록되어있는 사람인 검사하는 방법--------------------------
//		if(pMap.containsKey(name)) {
//			System.out.println(name +"씨는 이미 등록되어있습니다.");
//			return;
//		}
		//--------------------------------------------------//
		
		System.out.print("전화번호 : ");
		String tel = sc.nextLine();
		System.out.print("주소 : ");
		String addr = sc.nextLine();

		Phone p = new Phone(name, tel, addr);
		pMap.put(name, p);
		//또는 한줄로 => pMap.pu(name,new Phone(name,tel,addr)); 써도 됨.
		
		System.out.println(name + "님이 새로 등록되었습니다.");
		totalcheck = true;
	}

///////////////////////////////////////////////////////////////////
	//5. 전화번호 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("<< 전화번호 수정 >>");
		System.out.print("수정 할 이름  > ");
		String name = sc.nextLine();
		Phone p = pMap.get(name);


		//쌤 방법--------------------------------------------------//
//		if(!pMap.containsKey(name)) { //해당사람이 있는지 key값을 찾아본다.
//			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
//			return;
//		}
		//-----------------------------------------------------//
		//내방법
		boolean check = pMap.containsKey(name);
		if (check == true) {
			System.out.print("전화번호 : ");
			String tel = sc.nextLine();
			System.out.print("주소 : ");
			String addr = sc.nextLine();

			pMap.put(name, new Phone(name, tel, addr));

			System.out.println(name + "님의 연락처가 업데이트 되었습니다.");
		} else {
			System.out.println(name + "님은 등록되어 있지 않은 이름입니다.");
		}
		totalcheck = true;
	}

///////////////////////////////////////////////////////////////////
	//6. 전화번호 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("<< 전화번호 삭제 >>");
		System.out.print("삭제 할 이름 > ");
		String name = sc.nextLine();

		//쌤 방법--------------------------------------------------//
//		if(!pMap.containsKey(name)) { //해당사람이 있는지 key값을 찾아본다.
//			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
//			return;
//		}
		//-----------------------------------------------------//
		//내방법
		boolean check = pMap.containsKey(name);

		if (check == true) {
			pMap.remove(name);
			System.out.println(name + "님이 삭제되었습니다.");
			totalcheck = true;
		} else {
			System.out.println(name + "님은 등록되어 있지 않은 이름입니다.");
		}
		
	}

///////////////////////////////////////////////////////////////////
	//7. 전화번호 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("<< 전화번호 검색 >>");
		System.out.print("검색 할 이름 > ");
		String name = sc.nextLine();
		Phone p = pMap.get(name);
		boolean check = pMap.containsKey(name);
		System.out.println("이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------------");
		if (check == true) {
			System.out.println(p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
		} else {
			System.out.println(name + "님은 등록되어 있지 않은 이름입니다.");
		}

	}

///////////////////////////////////////////////////////////////////
	//8. 등록된 모든 전화번호 출력
	private void print() {
		System.out.println();
		System.out.println("<< 전화번호 부 >>");
		System.out.println("num\t이름\t전화번호\t\t주소");
		System.out.println("-------------------------------------------------");

		if(memList.size()==0) {	//keySet이 하나도 없으면...
			System.out.println("현재 등록된 사람은 0명입니다.");
		}else {

			int count = 0;	//번호가 나오게 출력하기위한 변수
			for (String member : memList) {
				count++;
				Phone p = pMap.get(member);
				System.out.println(count + " )\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());

			}
		}
	}
}

///////////////////////////////////////////////////////////////////

	//1. Phone 클래스 만들기

	class Phone implements Serializable{
		
	private static final long serialVersionUID = 1L;

	protected String name;	//회원이름
		protected String tel;		//전화번호
		protected String addr;	//주소
		

		protected String getName() {
			return name;
		}


		protected void setName(String name) {
			this.name = name;
		}


		protected String getTel() {
			return tel;
		}


		protected void setTel(String tel) {
			this.tel = tel;
		}


		protected String getAddr() {
			return addr;
		}


		protected void setAddr(String addr) {
			this.addr = addr;
		}

		Phone(String name, String tel, String addr) {
			super();
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}


		@Override
		public String toString() {
			return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
		}

	}









