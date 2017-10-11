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
	 * Ȩ������
	 * @return
	 */
	public String home() {
		s = new Scanner(System.in);
		System.out.println("=============================");
		System.out.println("������ Ȩ������");
		System.out.println("=============================");
		System.out.println("1.�α���");
		System.out.println("2.���̵� ã��");
		System.out.println("3.��й�ȣ ã��");
		System.out.println("4.ȸ������");
		System.out.println("5.���� �˻�");
		System.out.println("6.����");
		System.out.println("=============================");
		System.out.print("�̿��� ������ ��ȣ�� �Է��ϼ��� : ");
		
		no = s.nextLine();
		System.out.println("�޴���ȣ : " + no);
		return no;
	}
	
	/**
	 * �α��������� : ������� ���̵�, ��й�ȣ ���� �޴��� ��ȯ
	 */
	public void login() {
		System.out.println("=============================");
		System.out.println("�α���ȭ��");
		System.out.println("=============================");
		System.out.print("���̵� : ");
		id = s.nextLine();
		
		System.out.print("��й�ȣ : ");
		pw = s.nextLine();
	}
	
	/**
	 * ������ �޴� 
	 * @return
	 */
	public String adminMenu() {
		System.out.println("=============================");
		System.out.println("������ �޴�");
		System.out.println("=============================");
		System.out.println("1.ȸ�� ��ü��ȸ");
		System.out.println("2.ȸ�� ����ȸ");
		System.out.println("3.���� ���");
		System.out.println("4.���� ����");
		System.out.println("5.���� ����");
		System.out.println("6.�� ���� ����");
		System.out.println("7.�α׾ƿ�");
		System.out.println("=============================");
		System.out.print("�̿��� ������ ��ȣ�� �Է��ϼ��� : ");

		no = s.nextLine();
		
		return no;
	}
	/**
	 * ���̵�ã�� ������
	 */
	public void searchId() {
		System.out.println("=============================");
		System.out.println("�޴��� ��ȣ�� ���̵� ã��");
		System.out.println("=============================");
		 System.out.print("�̸� : ");
		 name= s.nextLine();
		 System.out.print("�޴��� ��ȣ : ");
		 mobile = s.nextLine();
		 
		 if(dao.selectId(name, mobile)!=null){
				System.out.print(name+"���� ���̵�� ");
				System.out.print(dao.selectId(name, mobile));
				System.out.println(" �Դϴ�.");
			}else {
				System.out.println("�Է��Ͻ� ������ ��ġ�ϴ� ���̵� �����ϴ�.");
			}
	}
	/**
	 * ��й�ȣ ã�� ������
	 */
	public void searchPw() {
		System.out.println("=============================");
		System.out.println("��й�ȣ ã��");
		System.out.println("=============================");
		 System.out.print("���̵� : ");
		 id= s.nextLine();
		 System.out.print("�޴��� ��ȣ : ");
		 mobile = s.nextLine();
		 
		 if(dao.selectPw(id, mobile)!=null) {
			 	System.out.print("�� ��й�ȣ �Է� : ");
				pw = s.nextLine();
				int up = dao.updatePassword(id, pw);
			
			}else {
				System.out.println("��ġ�ϴ� ������ �����ϴ�.");
			}
	}
	/**
	 * �α��� �� �޴�
	 * @return
	 */
	public String menu() {
		System.out.println("=============================");
		System.out.println("�޴�");
		System.out.println("=============================");
		System.out.println("1.���� �˻�");
		System.out.println("2.������� ��û");
		System.out.println("3.�� ���� ����");
		System.out.println("4.�� ���� ����");
		System.out.println("5.�α׾ƿ�");
		System.out.println("6.Ż���ϱ�");
		System.out.println("=============================");
		System.out.print("�̿��� ������ ��ȣ�� �Է��ϼ��� : ");
		
		no = s.nextLine();
	//	s.next();
		return no;
		
	}
	/**
	 * ȸ������ ������
	 * @return
	 */
	public boolean join() {
		System.out.println("=============================");
		System.out.println("ȸ������ ȭ��");
		System.out.println("=============================");
		System.out.println("���ǻ���");
		System.out.println("���̵�� 5~20�ڸ� �Է����ּ���.");
		System.out.println("��й�ȣ�� 10~20�ڸ� �Է��� �ּ���.");
		System.out.println("�ڵ��� ��ȣ��'-'�� �����Ͽ� �Է��ϼ���.");
		System.out.println("=============================");
		System.out.println("�Ʒ��� �Է��ϼ���.");
		System.out.println("=============================");
		boolean flag = true;
		do {
			
			System.out.print("���̵� : ");
			id = s.nextLine();
			flag = con.dupli(id);
			

		} while (flag);
		
		System.out.print("�̸� : ");
		name = s.nextLine();
		System.out.print("��й�ȣ : ");
		pw = s.nextLine();
		System.out.print("�ּ� : ");
		addr = s.nextLine();
		System.out.print("�ڵ��� ��ȣ : ");
		mobile = s.nextLine();
		System.out.print("��ȣ�ϴ� �����о� : ");
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
	 * �� �������� ������
	 */
	public void showInfo() {
		MemberDto dto = dao.select(id);
		System.out.println("=============================");
		System.out.println("�� ����");
		System.out.println("=============================");
		System.out.println("�̸� : "+dto.getUsername());
		System.out.println("�ּ� : "+dto.getAddr());
		System.out.println("�޴��� ��ȣ : "+dto.getMobile());
		System.out.println("��ȣ�ϴ� ���� �о� : "+dto.getPreference());
	}
	/**
	 * ������ ���� ������
	 */
	public void udateInfo() {
		System.out.println("=============================");
		System.out.println("�� ���� �����ϱ�");
		System.out.println("=============================");
		System.out.print("��й�ȣ :");
		pw=s.nextLine();
		System.out.print("�ּ� :");
		addr=s.nextLine();
		System.out.print("�޴��� ��ȣ :");
		mobile = s.nextLine();
		System.out.print("��ȣ�ϴ� ���� �о� :");
		pref = s.nextLine();
		
		int up = dao.update(pw,addr,mobile,pref,id);
		if(up==1) {
			System.out.println("���� �Ǿ����ϴ�.");
		}
		
	}
	/**
	 * Ż�������� 
	 */
	public boolean deleteMember() {
		
		System.out.println("������ Ż���Ͻðڽ��ϱ�?");
		System.out.println("1.��");
		System.out.println("2.�ƴϿ�");
		no =s.nextLine();
		if(no.equals("1")) {
			boolean b = dao.deleteMember(id);
			if(b){
				System.out.println("Ż�� �ϼ̽��ϴ�.");
				return true;
			}
		}
		
		return false;
		
		
	}
//	/**
//	 * �����˻� ��
//	 */
//	public void searchBooks() {
//		System.out.println("=============================");
//		System.out.println("���� �˻��ϱ�");
//		System.out.println("=============================");
//		System.out.print("������ : ");
//		bname=s.nextLine();
//		System.out.print("���� : ");
//		author=s.nextLine();
//		System.out.print("���ǻ� : ");
//		publisher = s.nextLine();
//		BooksDto dto2 = dao2.select(bname, author, publisher);
//		System.out.println(dto2);
////		System.out.println("���� : "+ dto2.getBname());
////		System.out.println("���� : "+ dto2.getAuthor()+" ����");
////		System.out.println("���ǻ� : "+ dto2.getPublisher());
////		System.out.println("����⵵ : "+ dto2.getPublishdate().substring(0,4));
////		System.out.println("û����ȣ: "+ dto2.getLoc());
////		System.out.println("ISBN: : "+ dto2.getIsbn());
//		
//		
//	}
	/**
	 * ȸ����ü �˻� ��
	 */
	public void selectAll() {
		System.out.println("=============================");
		System.out.println("ȸ�� ��ü �˻�");
		System.out.println("=============================");
		List<MemberDto> list = dao.selectAll();

		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}

	}
	/**
	 * ȸ���� �˻�
	 */
	public void select() {
		System.out.println("=============================");
		System.out.println("ȸ�� �� �˻�");
		System.out.println("=============================");
		System.out.println("1.id�� �˻�");
		System.out.println("2.��ȣ �о߷� �˻�");
		System.out.println("=============================");
		System.out.print("�̿��� ������ ��ȣ�� �Է��ϼ��� : ");
		no=s.nextLine();
		
		switch(no) {
		case "1":
			System.out.println("=============================");
			System.out.println("id�� ȸ�� �˻�");
			System.out.println("=============================");
			System.out.print("�˻��� ���̵� :");
			id = s.nextLine();
			dto =dao.select(id);
			System.out.println(dto);
			break;
		case "2":
			System.out.println("=============================");
			System.out.println("��ȣ ���� �о߷� ȸ�� �˻�");
			System.out.println("=============================");
			System.out.print("��ȣ ���� Ű���� : ");
			pref=s.nextLine();
		
			List<MemberDto> list =dao.selectPref(pref);
			for(int i=0; i<list.size();i++) {
				System.out.println(list.get(i));
			}
			
			break;
		}
		
	}
//	/**
//	 * ���� ��� ��
//	 */
//	public void insertBooks() {
//		System.out.println("=============================");
//		System.out.println("���� ���");
//		System.out.println("=============================");
//		System.out.print("������ : ");
//		bname =s.nextLine();
//		System.out.print("���� : ");
//		author =s.nextLine();
//		System.out.print("���ǻ� : ");
//		publisher = s.nextLine();
//		System.out.print("�������� : ");
//		publishdate = s.nextLine();
//		System.out.print("ISBN : ");
//		isbn = s.nextLine();
//		System.out.print("û�� ��ȣ : ");
//		loc = s.nextLine();
//		
//		dto2 = new BooksDto(bname, author, publisher, publishdate, isbn, loc);
//		
//		int result =dao2.insert(dto2);
//	
//		if(result==1) {
//		System.out.println(bname+"å�� ��� �Ǿ����ϴ�.");
//		}else {
//			System.out.println("��Ͽ� �����Ͽ����ϴ�.");
//		}
//	}
//	
//	public void deleteBooks() {
//		System.out.println("=============================");
//		System.out.println("���� ����");
//		System.out.println("=============================");
//		System.out.print("������ : ");
//		bname =s.nextLine();
//		System.out.print("���� : ");
//		author =s.nextLine();
//		System.out.print("���ǻ� : ");
//		publisher = s.nextLine();
//		System.out.print("ISBN : ");
//		isbn = s.nextLine();
//		
//		boolean b = dao2.deleteBook(bname, author, publisher, isbn);
//		
//		if(b) {
//			System.out.println(bname+"�� �����Ǿ����ϴ�.");
//		}else {
//			System.out.println("��ġ�ϴ� ������ �����ϴ�.");
//		}
//		
//	}
//	
//	/**
//	 * ���� ���� �����ϱ�
//	 */
//	public void updateBooks() {
//		System.out.println("=============================");
//		System.out.println("���� ���� ����");
//		System.out.println("=============================");
//		System.out.print("������ ������ ISBN :");
//		isbn = s.nextLine();
//		
//		if(isbn.equals(dao2.selectIsbn(isbn))){
//		
//			System.out.print("û�� ��ȣ : ");
//			loc = s.nextLine();
//			
//			int result =dao2.updateBooks(isbn,loc);
//			if(result==1) {
//				System.out.println("����Ǿ����ϴ�.");
//			}
//		}else
//			System.out.println("��ġ�ϴ� ������ �����ϴ�.");	
//	}

}
