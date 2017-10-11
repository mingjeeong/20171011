package work.model.service;

import work.model.dao.MemberDao;
import work.model.dto.MemberDto;

/**
 * ##Model(Service) ����(����) ����ϴ� Ŭ����
 * @author kosta
 *
 */
	public class Service {
	
	  //�μ����̺� data access �� ���� ��ü ����
		//DeptDao dao= new DeptDao();
		private static MemberDao dao = MemberDao.getInstance();
	
	public Service() {
		
	}
	
	/**
	 * ȸ�� ��� ��û ����
	 * @param dto �μ� ��ü
	 * @return ���ڵ� �߰� ���� ��� , ���� 1, ���� 0
	 */
	public int add(MemberDto dto) {
		//int result = dao.insert(dto);
		//return dao.insert(dto);
		
		//��� ��û�� �μ���ȣ �ߺ���ȸ
		//�ߺ����� ������ �μ����
		//�ߺ��Ǹ� ����ó���� ��Ʈ�ѷ����� ����

		if(!dao.isUserid(dto.getUserid())) {
			return dao.insert(dto);
		}
		return 0;
	}
	
	
	/**
	 * id �ߺ��˻�
	 * @param id
	 * @return
	 */
	public boolean duplicate(String id) {
		if(!dao.isUserid(id)) {//�ߺ� ���̵� �������� ������
			return false;
		}
		 return true;
	
	}
	

	/**
	 * 
	 * @param deptno
	 * @return
	 */
	public MemberDto get(int deptno) {
		return null;
	}

}
