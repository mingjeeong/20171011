package work.view;


import java.util.List;
import java.util.Scanner;

import work.controller.Controller;
import work.model.dao.MemberDao;
import work.model.dto.BooksDto;
import work.model.dto.MemberDto;


public class MenuView {
	private static MemberDao dao = MemberDao.getInstance();
	//private static BooksDao dao2 = BooksDao.getInstance();
	
	Scanner s;
	String no ;
	String id;
	String name;
	String pw;
	String addr;
	String mobile;
	String pref;
	MemberDto dto;
	
	BooksDto dto2;
	String bname;
	String author;
	String publisher;
	String publishdate;
	String isbn;
	String loc;
	
	Controller con = new Controller();
	
	
	/**
	 * 홈페이지
	 * @return
	 */
	public String home() {
		s = new Scanner(System.in);
		System.out.println("=============================");
		System.out.println("도서관 홈페이지");
		System.out.println("=============================");
		System.out.println("1.로그인");
		System.out.println("2.아이디 찾기");
		System.out.println("3.비밀번호 찾기");
		System.out.println("4.회원가입");
		System.out.println("5.도서 검색");
		System.out.println("6.종료");
		System.out.println("=============================");
		System.out.print("이용할 서비스의 번호를 입력하세요 : ");
		
		no = s.nextLine();
		System.out.println("메뉴번호 : " + no);
		return no;
	}
	
	/**
	 * 로그인페이지 : 멤버변에 아이디, 비밀번호 설정 메뉴로 반환
	 */
	public void login() {
		System.out.println("=============================");
		System.out.println("로그인화면");
		System.out.println("=============================");
		System.out.print("아이디 : ");
		id = s.nextLine();
		
		System.out.print("비밀번호 : ");
		pw = s.nextLine();
	}
	
	/**
	 * 관리자 메뉴 
	 * @return
	 */
	public String adminMenu() {
		System.out.println("=============================");
		System.out.println("관리자 메뉴");
		System.out.println("=============================");
		System.out.println("1.회원 전체조회");
		System.out.println("2.회원 상세조회");
		System.out.println("3.도서 등록");
		System.out.println("4.도서 변경");
		System.out.println("5.도서 삭제");
		System.out.println("6.내 정보 변경");
		System.out.println("7.로그아웃");
		System.out.println("=============================");
		System.out.print("이용할 서비스의 번호를 입력하세요 : ");

		no = s.nextLine();
		
		return no;
	}
	/**
	 * 아이디찾기 페이지
	 */
	public void searchId() {
		System.out.println("=============================");
		System.out.println("휴대폰 번호로 아이디 찾기");
		System.out.println("=============================");
		 System.out.print("이름 : ");
		 name= s.nextLine();
		 System.out.print("휴대폰 번호 : ");
		 mobile = s.nextLine();
		 
		 if(dao.selectId(name, mobile)!=null){
				System.out.print(name+"님의 아이디는 ");
				System.out.print(dao.selectId(name, mobile));
				System.out.println(" 입니다.");
			}else {
				System.out.println("입력하신 정보와 일치하는 아이디가 없습니다.");
			}
	}
	/**
	 * 비밀번호 찾기 페이지
	 */
	public void searchPw() {
		System.out.println("=============================");
		System.out.println("비밀번호 찾기");
		System.out.println("=============================");
		 System.out.print("아이디 : ");
		 id= s.nextLine();
		 System.out.print("휴대폰 번호 : ");
		 mobile = s.nextLine();
		 
		 if(dao.selectPw(id, mobile)!=null) {
			 	System.out.print("새 비밀번호 입력 : ");
				pw = s.nextLine();
				int up = dao.updatePassword(id, pw);
			
			}else {
				System.out.println("일치하는 정보가 없습니다.");
			}
	}
	/**
	 * 로그인 후 메뉴
	 * @return
	 */
	public String menu() {
		System.out.println("=============================");
		System.out.println("메뉴");
		System.out.println("=============================");
		System.out.println("1.도서 검색");
		System.out.println("2.희망도서 신청");
		System.out.println("3.내 정보 보기");
		System.out.println("4.내 정보 변경");
		System.out.println("5.로그아웃");
		System.out.println("6.탈퇴하기");
		System.out.println("=============================");
		System.out.print("이용할 서비스의 번호를 입력하세요 : ");
		
		no = s.nextLine();
	//	s.next();
		return no;
		
	}
	/**
	 * 회원가입 페이지
	 * @return
	 */
	public boolean join() {
		System.out.println("=============================");
		System.out.println("회원가입 화면");
		System.out.println("=============================");
		System.out.println("주의사항");
		System.out.println("아이디는 5~20자리 입력해주세요.");
		System.out.println("비밀번호는 10~20자리 입력해 주세요.");
		System.out.println("핸드폰 번호는'-'을 포함하여 입력하세요.");
		System.out.println("=============================");
		System.out.println("아래를 입력하세요.");
		System.out.println("=============================");
		boolean flag = true;
		do {
			
			System.out.print("아이디 : ");
			id = s.nextLine();
			flag = con.dupli(id);
			

		} while (flag);
		
		System.out.print("이름 : ");
		name = s.nextLine();
		System.out.print("비밀번호 : ");
		pw = s.nextLine();
		System.out.print("주소 : ");
		addr = s.nextLine();
		System.out.print("핸드폰 번호 : ");
		mobile = s.nextLine();
		System.out.print("선호하는 도서분야 : ");
		pref = s.nextLine();

		dto = new MemberDto(id, name, pw, addr, mobile, pref);

		String result = con.insert(dto);
		if(result.startsWith("1")) {
			System.out.println(result.substring(1));
			return false;
			
		}else if(result.startsWith("2")) {
			System.out.println(result.substring(1));
			return true;
		}
		
		System.out.println(result);
		return true;
		
	
		
	}
	/**
	 * 내 정보보기 페이지
	 */
	public void showInfo() {
		MemberDto dto = dao.select(id);
		System.out.println("=============================");
		System.out.println("내 정보");
		System.out.println("=============================");
		System.out.println("이름 : "+dto.getUsername());
		System.out.println("주소 : "+dto.getAddr());
		System.out.println("휴대폰 번호 : "+dto.getMobile());
		System.out.println("선호하는 도서 분야 : "+dto.getPreference());
	}
	/**
	 * 내정보 변경 페이지
	 */
	public void udateInfo() {
		System.out.println("=============================");
		System.out.println("내 정보 변경하기");
		System.out.println("=============================");
		System.out.print("비밀번호 :");
		pw=s.nextLine();
		System.out.print("주소 :");
		addr=s.nextLine();
		System.out.print("휴대폰 번호 :");
		mobile = s.nextLine();
		System.out.print("선호하는 도서 분야 :");
		pref = s.nextLine();
		
		int up = dao.update(pw,addr,mobile,pref,id);
		if(up==1) {
			System.out.println("변경 되었습니다.");
		}
		
	}
	/**
	 * 탈퇴페이지 
	 */
	public boolean deleteMember() {
		
		System.out.println("정말로 탈퇴하시겠습니까?");
		System.out.println("1.네");
		System.out.println("2.아니오");
		no =s.nextLine();
		if(no.equals("1")) {
			boolean b = dao.deleteMember(id);
			if(b){
				System.out.println("탈퇴 하셨습니다.");
				return true;
			}
		}
		
		return false;
		
		
	}
//	/**
//	 * 도서검색 뷰
//	 */
//	public void searchBooks() {
//		System.out.println("=============================");
//		System.out.println("도서 검색하기");
//		System.out.println("=============================");
//		System.out.print("도서명 : ");
//		bname=s.nextLine();
//		System.out.print("저자 : ");
//		author=s.nextLine();
//		System.out.print("출판사 : ");
//		publisher = s.nextLine();
//		BooksDto dto2 = dao2.select(bname, author, publisher);
//		System.out.println(dto2);
////		System.out.println("도서 : "+ dto2.getBname());
////		System.out.println("저자 : "+ dto2.getAuthor()+" 지음");
////		System.out.println("출판사 : "+ dto2.getPublisher());
////		System.out.println("발행년도 : "+ dto2.getPublishdate().substring(0,4));
////		System.out.println("청구기호: "+ dto2.getLoc());
////		System.out.println("ISBN: : "+ dto2.getIsbn());
//		
//		
//	}
	/**
	 * 회원전체 검색 뷰
	 */
	public void selectAll() {
		System.out.println("=============================");
		System.out.println("회원 전체 검색");
		System.out.println("=============================");
		List<MemberDto> list = dao.selectAll();

		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}

	}
	/**
	 * 회원상세 검색
	 */
	public void select() {
		System.out.println("=============================");
		System.out.println("회원 상세 검색");
		System.out.println("=============================");
		System.out.println("1.id로 검색");
		System.out.println("2.선호 분야로 검색");
		System.out.println("=============================");
		System.out.print("이용할 서비스의 번호를 입력하세요 : ");
		no=s.nextLine();
		
		switch(no) {
		case "1":
			System.out.println("=============================");
			System.out.println("id로 회원 검색");
			System.out.println("=============================");
			System.out.print("검색할 아이디 :");
			id = s.nextLine();
			dto =dao.select(id);
			System.out.println(dto);
			break;
		case "2":
			System.out.println("=============================");
			System.out.println("선호 도서 분야로 회원 검색");
			System.out.println("=============================");
			System.out.print("선호 도서 키워드 : ");
			pref=s.nextLine();
		
			List<MemberDto> list =dao.selectPref(pref);
			for(int i=0; i<list.size();i++) {
				System.out.println(list.get(i));
			}
			
			break;
		}
		
	}
//	/**
//	 * 도서 등록 뷰
//	 */
//	public void insertBooks() {
//		System.out.println("=============================");
//		System.out.println("도서 등록");
//		System.out.println("=============================");
//		System.out.print("도서명 : ");
//		bname =s.nextLine();
//		System.out.print("저자 : ");
//		author =s.nextLine();
//		System.out.print("출판사 : ");
//		publisher = s.nextLine();
//		System.out.print("발행일자 : ");
//		publishdate = s.nextLine();
//		System.out.print("ISBN : ");
//		isbn = s.nextLine();
//		System.out.print("청구 기호 : ");
//		loc = s.nextLine();
//		
//		dto2 = new BooksDto(bname, author, publisher, publishdate, isbn, loc);
//		
//		int result =dao2.insert(dto2);
//	
//		if(result==1) {
//		System.out.println(bname+"책이 등록 되었습니다.");
//		}else {
//			System.out.println("등록에 실패하였습니다.");
//		}
//	}
//	
//	public void deleteBooks() {
//		System.out.println("=============================");
//		System.out.println("도서 삭제");
//		System.out.println("=============================");
//		System.out.print("도서명 : ");
//		bname =s.nextLine();
//		System.out.print("저자 : ");
//		author =s.nextLine();
//		System.out.print("출판사 : ");
//		publisher = s.nextLine();
//		System.out.print("ISBN : ");
//		isbn = s.nextLine();
//		
//		boolean b = dao2.deleteBook(bname, author, publisher, isbn);
//		
//		if(b) {
//			System.out.println(bname+"이 삭제되었습니다.");
//		}else {
//			System.out.println("일치하는 정보가 없습니다.");
//		}
//		
//	}
//	
//	/**
//	 * 도서 정보 변경하기
//	 */
//	public void updateBooks() {
//		System.out.println("=============================");
//		System.out.println("도서 정보 변경");
//		System.out.println("=============================");
//		System.out.print("변경할 도서의 ISBN :");
//		isbn = s.nextLine();
//		
//		if(isbn.equals(dao2.selectIsbn(isbn))){
//		
//			System.out.print("청구 기호 : ");
//			loc = s.nextLine();
//			
//			int result =dao2.updateBooks(isbn,loc);
//			if(result==1) {
//				System.out.println("변경되었습니다.");
//			}
//		}else
//			System.out.println("일치하는 도서가 없습니다.");	
//	}

}
