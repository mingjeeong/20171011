package work.controller;

import java.util.ArrayList;

import work.model.dto.MemberDto;
import work.model.service.Service;

/**
 * <pre>
 * ##Controller Ŭ���� ����
 * 
 *1.��û�ľ� : �޼��� ����
 *2.��û������ ���� : �޼��� �ƱԸ�Ʈ ����
 *3.������ ���� : 
 *4.Model ��û�Ƿ�
 *5.Controller���� Model ��û ó����� �޾Ƽ� �ʿ��� ����
 *6.���� ��� ����
 *7.���� ��� ���(���)
 * </pre>
 * 
 * @author kosta
 *
 */

public class Controller {
	// ��û�� ó���ϴ� ��(����) ��ü ����
	private Service s = new Service();

	// �μ���� ��û ���� �޼��� (�μ���ü) : ��ȯŸ�� ����޼��� String
	// �޼��� ���� ���� signature

	public boolean dupli(String id) {
		if (id.trim().length() > 20 || id.trim().length() < 5) {
			System.out.println("Error : ���̵�� 5~20�ڸ� �Է����ּ���\n");
			return true;
		}

		if (s.duplicate(id)) {

			System.out.println("Error : �̹� ��ϵ� ���̵� �Դϴ�. ���̵� �ٽ� �Է����ּ���.");

			return true;

		} else {
			System.out.println("��� ������ ���̵� �Դϴ�.");
			return false;
		}
	}

	/**
	 * �μ���� ��û ���� �޼���
	 * 
	 * @param dto
	 * @return ��� ��� �޼���
	 */

	public String insert(MemberDto dto) {
		StringBuilder returnValue = new StringBuilder();

		if (dto == null) {
			returnValue.append("Error : ������ �������� ����");
			return returnValue.toString();
		}

		if (dto.getUserid() == null || dto.getUserid().trim().length() == 0)
			returnValue.append("Error : ���̵� �Է��ϼ��� \n");

		if (dto.getMobile() == null || dto.getMobile().trim().length() == 0)
			returnValue.append("Error :  �ڵ��� ��ȣ�� �Է��ϼ��� \n");
		else if (dto.getMobile().trim().length() != 13)
			returnValue.append("Error : �ڵ��� ��ȣ��'-'�� �����Ͽ� �Է��ϼ���\n");

		if (dto.getAddr() == null || dto.getAddr().trim().length() == 0)
			returnValue.append("Error : �ּҸ� �Է��ϼ���\n");

		if (dto.getUserpw() == null || dto.getUserpw().trim().length() == 0)
			returnValue.append("Error : ��й�ȣ�� �Է��ϼ���\n");
		else if (dto.getUserpw().trim().length() > 20 || dto.getUserpw().trim().length() < 8)
			returnValue.append("Error : ��й�ȣ�� 8~20�ڸ� �Է��� �ּ���\n");

		if (dto.getPreference() == null || dto.getUserpw().trim().length() == 0)
			returnValue.append("Error : ��ȣ�ϴ� ���� �о߸� �Է����ּ���");

		if (returnValue.length() != 0) {
			return returnValue.toString();
		}

		// ��ûó���� ���� ������ ���� �Ϸ�
		int result = s.add(dto);

		// 6.������ ����
		if (result == 1) {
			return "1success :ȸ�������� ���� �Ϸ�Ǿ����ϴ�";
		} else {
			return "2false : ȸ�������� ���������� �̷������ �ʾҽ��ϴ�";

		}

	}
	
	

	/**
	 * �μ� ����ȸ(�μ���ȣ) : �μ���ü
	 * 
	 * @param deptno
	 * @return
	 */
	public String get(int deptno) {
		return null;
	}

	
	/**
	 * �μ� ��ü��ȸ() : �μ���ü��
	 * 
	 * @param dto
	 * @return
	 */
	public ArrayList<MemberDto> getAll(MemberDto dto) {
		return null;
	}

	/**
	 * �μ� ���� (�μ���,�μ���ġ,�μ���ȣ) : ����޼���
	 * 
	 * @param dname
	 * @param dloc
	 * @param deptno
	 */
	public void set(String dname, String dloc, int deptno) {

	}

	/**
	 * �μ� ����(�μ���ȣ) : ����޼���
	 * 
	 * @param deptno
	 */
	public void remove(int deptno) {

	}

	/**
	 * �μ��� ����
	 * 
	 * @param dname
	 */
	public void setName(String dname) {

	}

	/**
	 * ���� ������ �μ� ��ü ��ȸ
	 * 
	 * @param loc
	 */
	public void getLoc(String loc) {

	}

}
