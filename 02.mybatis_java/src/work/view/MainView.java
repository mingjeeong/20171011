package work.view;



import work.controller.Controller;
import work.model.dao.MemberDao;
import work.model.dto.MemberDto;


public class MainView {

    public MainView() {
    	
	}
	/**
	 * <pre>
	 * 자바 시작 메서드
	 * </pre>
	 */
	private static MemberDao dao = MemberDao.getInstance();

	public static void main(String[] args) {

		String no ;
		MenuView menu = new MenuView();
		startMenu:
		do {
			no = menu.home();  // 홈메뉴에서 입력받은 메뉴번호 반환
			System.out.println(">>>"+no);
			switch (no) {
			case "1":
				menu.login();	// 로그인 메뉴
				if(menu.pw.equals(dao.selectPw("admin01")) && menu.id.equals("admin01")) { // 관리자화면
					do {
						no=menu.adminMenu();
						switch (no) {
						case "1":
							menu.selectAll();
							break;
						case "2":
							menu.select();
							break;
//						case "3":
//							menu.insertBooks();
//							break;
//							
//						case "4":
//							menu.updateBooks();
//							break;
//						case "5":
//							menu.deleteBooks();
//							break;
//						case "6":
//							menu.udateInfo();
//							break;
						case "7":// 로그아웃
							no = menu.home();
							break;
						default :
							break;
						}
					} while (!no.equals("7"));
				}
				else if (menu.pw.equals(dao.selectPw(menu.id))) {  // 로그인 검증 성공
					System.out.println("로그인이 되었습니다[" + menu.id + "님]");
					do {
						no=menu.menu();	// 회원전용 메뉴
						switch(no) {
						case "1":
							//menu.searchBooks();
							break;
						case "2"://희망도서신청게시판
							break;
						case "3":
							menu.showInfo();
							break;
						case "4":
							menu.udateInfo();
							break;
						case "5"://로그아웃
							menu.id = null; // 로그아웃전에 로그인 정보 해제 
							menu.pw = null;
							System.out.println("로그아웃되었습니다. 서비스 이용하시려면 다시 로그인 하시기 바랍니다.");
							//no = menu.home();  // 도서관 시스템 초기메뉴 1.로그인2.아이디 찾기3.비밀번호 찾기4.회원가입 5.도서 검색 6.종료
							continue startMenu;
						case "6":
							menu.deleteMember();
							if(menu.deleteMember()==true) {
								menu.id = null; // 탈퇴전에 로그인 정보 해제 
								menu.pw = null;
							}
							
							break;
						default :
							System.out.println("미지원");
							break;
						}
					} while (!no.equals("5")||!no.equals("6"));
					
				} 
				else {
					System.out.println("로그인을 실패하였습니다.");
					//no = menu.home();  // 홈메뉴에서 입력받은 메뉴번호 반환
					continue;
				}
				break;
				
			case "2":
				menu.searchId();
				break;
				
			case "3":
				menu.searchPw();
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
				break;
				
			case "4":
				boolean b;
				do {
					b = menu.join();
				} while (b);
				break;
				
			case "5":
			//	menu.searchBooks();
				break;
			case "6"://종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default :
				System.out.println("지원하지않는 메뉴번호입니다. 다시 메뉴를 선택하시기 바랍니다.");
				continue;
			}
		} while (!no.equals("6"));
		
	
	}
	
}
