package work.view;



import work.controller.Controller;
import work.model.dao.MemberDao;
import work.model.dto.MemberDto;


public class MainView {

    public MainView() {
    	
	}
	/**
	 * <pre>
	 * �ڹ� ���� �޼���
	 * </pre>
	 */
	private static MemberDao dao = MemberDao.getInstance();

	public static void main(String[] args) {

		String no ;
		MenuView menu = new MenuView();
		startMenu:
		do {
			no = menu.home();  // Ȩ�޴����� �Է¹��� �޴���ȣ ��ȯ
			System.out.println(">>>"+no);
			switch (no) {
			case "1":
				menu.login();	// �α��� �޴�
				if(menu.pw.equals(dao.selectPw("admin01")) && menu.id.equals("admin01")) { // ������ȭ��
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
						case "7":// �α׾ƿ�
							no = menu.home();
							break;
						default :
							break;
						}
					} while (!no.equals("7"));
				}
				else if (menu.pw.equals(dao.selectPw(menu.id))) {  // �α��� ���� ����
					System.out.println("�α����� �Ǿ����ϴ�[" + menu.id + "��]");
					do {
						no=menu.menu();	// ȸ������ �޴�
						switch(no) {
						case "1":
							//menu.searchBooks();
							break;
						case "2"://���������û�Խ���
							break;
						case "3":
							menu.showInfo();
							break;
						case "4":
							menu.udateInfo();
							break;
						case "5"://�α׾ƿ�
							menu.id = null; // �α׾ƿ����� �α��� ���� ���� 
							menu.pw = null;
							System.out.println("�α׾ƿ��Ǿ����ϴ�. ���� �̿��Ͻ÷��� �ٽ� �α��� �Ͻñ� �ٶ��ϴ�.");
							//no = menu.home();  // ������ �ý��� �ʱ�޴� 1.�α���2.���̵� ã��3.��й�ȣ ã��4.ȸ������ 5.���� �˻� 6.����
							continue startMenu;
						case "6":
							menu.deleteMember();
							if(menu.deleteMember()==true) {
								menu.id = null; // Ż������ �α��� ���� ���� 
								menu.pw = null;
							}
							
							break;
						default :
							System.out.println("������");
							break;
						}
					} while (!no.equals("5")||!no.equals("6"));
					
				} 
				else {
					System.out.println("�α����� �����Ͽ����ϴ�.");
					//no = menu.home();  // Ȩ�޴����� �Է¹��� �޴���ȣ ��ȯ
					continue;
				}
				break;
				
			case "2":
				menu.searchId();
				break;
				
			case "3":
				menu.searchPw();
				System.out.println("��й�ȣ�� ���������� ����Ǿ����ϴ�.");
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
			case "6"://����
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			default :
				System.out.println("���������ʴ� �޴���ȣ�Դϴ�. �ٽ� �޴��� �����Ͻñ� �ٶ��ϴ�.");
				continue;
			}
		} while (!no.equals("6"));
		
	
	}
	
}
