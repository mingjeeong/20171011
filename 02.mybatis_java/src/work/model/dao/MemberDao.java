package work.model.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import work.model.dto.MemberDto;

	/**
	 * <pre>
	 * Singleton pattern
	 * 
	 *
	 * </pre>��
	 * 
	 * @author kosta
	 *
	 */
public class MemberDao {
	/** singleton pattern : �ش� Ŭ���� Ÿ���� �ν��Ͻ� ���� final ���� */
	private static final MemberDao INSTANCE = new MemberDao() ;
	
	/** singleton pattern : private constructor 
	 *  �ϳ��� �ν��Ͻ� ���� �Ŀ� ���ο��� ��ü�� �����ÿ� ���� �߻�
	 *  ��, �ݵ�� �ϳ��� �ν��Ͻ������� ��ü ���񽺸� ���� ���å 
	 * */
	private MemberDao() {
		if (INSTANCE != null) {
			throw new AssertionError();
		}
	}
	
	/** singleton pattern */
	public static MemberDao getInstance() {
		return INSTANCE;
	}

	
	private FactoryDao factory = FactoryDao.getInstance();
//	private static MemberDao instance = new MemberDao();
//	
//	/** db������ ���� property ������� ���� */
//	private String driver ;
//	private String url ;
//	private String username ;
//	private String password ;
//	private ResourceBundle bundle;
//
//	//sql �ܺ� ���� ���� ������� ����
//	private ResourceBundle queryResource;
//	
//	/**
//	 * �⺻ ������
//	 */
//	private MemberDao() {
//		//�ܺ� ���� ���� �����ͼ� ������� �ʱ�ȭ ����
//		queryResource =ResourceBundle.getBundle("conf/query");
//
//	}
//	
//	public static MemberDao getInstance() {
//		return instance;
//	}
//
	/**
	 * ȸ�� �߰�
	 * @param dto
	 * @return ���� 1 ���� 0
	 */
	public int insert(MemberDto dto) {
		SqlSession session = factory.getSqlSession(true);  // auto commit
		int result = 0;
		try { 
			result = session.insert("memberInsert", dto);
			
		} finally {
			session.close();
		}
		return result;
	}
//	/**
//	 * ȸ�� ���ڵ� �߰� �޼ҵ�
//	 * 
//	 * @param dto
//	 * @return ���н� 0 , ������  ��ϵ� ���ڵ� ��� ��ȯ
//	 */
//	public int insert(MemberDto dto) {
//		
//		String userid = dto.getUserid();
//		String userpw = dto.getUserpw();
//		String username = dto.getUsername();
//		String addr = dto.getAddr();
//		String mobile = dto.getMobile();
//		String preference = dto.getPreference();
//		
//		Connection conn = null;
//		PreparedStatement pstm = null;
//		
//		//String sql = "insert into members values(?,?,?,?,?,?)";
//		String sql = queryResource.getString("Member.insert");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);//�̿ϼ� ���� sql���
//			pstm.setString(1, userid);
//			pstm.setString(2, username);
//			pstm.setString(3, userpw);
//			pstm.setString(4, addr);
//			pstm.setString(5, mobile);
//			pstm.setString(6, preference);//�Ϻ��� ���� sql���
//	
//			return pstm.executeUpdate(); //sql ���� :�Ϻ��� ����̹Ƿ� sql�����ؼ��� �ȵ�
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ����Ͽ���");
//		} 
//		factory.close(conn, pstm);
//		return 0;
//	}
//
//		
	/*
	 * �ߺ� ���̵� �˻� �޼���
	 * -- CRUD ���� ������ ���ο��� ȣ�� ��� : private 
	 * -- ȸ�����Խ� ���� ��û���� ���̵� �ߺ�Ȯ�νÿ� ��� : public
	 * -- ���ϰ� : boolean, ���(String)
	 * 
	 * @param userid
	 * @return ����� true, ������� false
	 * 
	 * 1. SqlSessionFactory���� SqlSession ��ü ��û
	 * 2. SqlSession ��ü�� ���ؼ� sql-mapper.xml�� �ۼ��� sql ���� �����û
	 * 3. ������ ó��
	 * 4. SqlSession ��ü�� �ش� �޼��� �����Ŀ� �ڿ��ݳ��Ǿ���� 
	 */
	public boolean isUserid(String userid) {
		SqlSession session = factory.getSqlSession(); // boolean : auto commit

		String isUserid = null;
		try {
			isUserid = session.selectOne("isuserid", userid);
			if(isUserid != null) {
				return true;
			}
		} finally {
			session.close();
		}
		
		return false;
	}
//
//
//	/**
//	 * ���̵� �ߺ� ��ȸ �޼���
//	 * @param userid
//	 * @return �����ϸ� true �������� ������ false
//	 */
//	public boolean isUserid(String userid) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//	
//		//select * from members where userid='user01';
//		//String sql = "select * from members where userid=?";
//		String sql = queryResource.getString("Member.selectIsId");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			rs = pstm.executeQuery();
//			return rs.next();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ����� �ߺ���ȸ ����");
//		} 
//		factory.close(conn, pstm);
//		
//		return false;
//	}
//	

//	/**
//	 * ȸ�� ��й�ȣ ���� �޼ҵ�  
//	 * @param dto
//	 * @param pw
//	 * @return ������ 1, ���н� 0 ����
//	 */
//	public int updatePassword(MemberDto dto,String pw) {
//		String userid = dto.getUserid();
//		Connection conn = null;
//		// Statement stm = null;
//		PreparedStatement pstm = null;
//		
//		// update members set userpw='1234' where userid='user04';
//		//String sql = "update members set userpw=?  where userid=?";
//		String sql = queryResource.getString("Member.updatePw");
//
//		try {
//			conn = factory.getConnection();
//			// stm = conn.createStatement();
//			// return stm.executeUpdate(sql);
//			pstm = conn.prepareStatement(sql);// �̿ϼ� ���� sql���
//			pstm.setString(1, pw);
//			pstm.setString(2, userid);// �Ϻ��� ���� sql���
//
//			return pstm.executeUpdate(); // sql ���� :�Ϻ��� ����̹Ƿ� sql�����ؼ��� �ȵ�
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��й�ȣ ���� ����");
//		}
//		factory.close(conn, pstm);
//		return 0;
//	}
//	
	public int updatePassword(String userid,String userpw) {
		HashMap<String,String> hashmap = new HashMap<>();
		hashmap.put("userpw", userpw);
		hashmap.put("userid", userid);
		SqlSession session = factory.getSqlSession(true);
		int result = 0;
		try{
			result=session.update("memberupdatepw", hashmap);
			
		}finally {
			session.close();
		}
		return result;
		
	}
//	/**
//	 * ��й�ȣ ����
//	 * @param id
//	 * @param pw
//	 * @return
//	 */
//	public int updatePassword(String id,String pw) {
//		
//		Connection conn = null;
//		// Statement stm = null;
//		PreparedStatement pstm = null;
//		
//		// update members set userpw='1234' where userid='user04';
//		//String sql = "update members set userpw=?  where userid=?";
//		String sql = queryResource.getString("Member.updatePw");
//		
//		try {
//			conn = factory.getConnection();
//			// stm = conn.createStatement();
//			// return stm.executeUpdate(sql);
//			pstm = conn.prepareStatement(sql);// �̿ϼ� ���� sql���
//			pstm.setString(1, pw);
//			pstm.setString(2, id);// �Ϻ��� ���� sql���
//			
//			return pstm.executeUpdate(); // sql ���� :�Ϻ��� ����̹Ƿ� sql�����ؼ��� �ȵ�
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��й�ȣ ���� ����");
//		}
//		factory.close(conn, pstm);
//		return 0;
//	}

	public int update(String userpw , String addr ,String mobile,String preference ,String userid) {
		HashMap<String,String> hashmap= new HashMap<>();
		hashmap.put("userpw", userpw);
		hashmap.put("addr", addr);
		hashmap.put("mobile", mobile);
		hashmap.put("preference", preference);
		hashmap.put("userid", userid);
		
		SqlSession session = factory.getSqlSession(true);
		int result=0;
		try {
			result=session.update("memberupdate", hashmap);
			
		}finally {
			session.close();
		}
		return result;
	}
//	/**
//	 * ȸ������ ����
//	 * @param pw
//	 * @param addr
//	 * @param mobile
//	 * @param preference
//	 * @param id
//	 * @return ���� 1���� ����0����
//	 */
//	public int update(String pw , String addr ,String mobile,String preference ,String id) {
//	
//		Connection conn = null;
//		// Statement stm = null;
//		PreparedStatement pstm = null;
//		
//		// update members set userpw='12345',addr ='��⵵',mobile='010-7421-8900',preference='' where userid='user04';
//		//String sql = "update members set userpw=?,addr =?,mobile=?,preference=?  where userid=?";
//		String sql = queryResource.getString("Member.update");
//		
//		try {
//			conn = factory.getConnection();
//			// stm = conn.createStatement();
//			// return stm.executeUpdate(sql);
//			pstm = conn.prepareStatement(sql);// �̿ϼ� ���� sql���
//			pstm.setString(1, pw);
//			pstm.setString(2, addr);// �Ϻ��� ���� sql���
//			pstm.setString(3, mobile);
//			pstm.setString(4, preference);
//			pstm.setString(5, id);
//			
//			return pstm.executeUpdate(); // sql ���� :�Ϻ��� ����̹Ƿ� sql�����ؼ��� �ȵ�
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��й�ȣ ���� ����");
//		}
//		factory.close(conn, pstm);
//		return 0;
//	}
//
	
	public boolean deleteMember(String userid) {
		SqlSession session = factory.getSqlSession(true);
		int result =0;
		try {
			result=session.delete("memberdelete", userid);
			if(result!=0) {
				return true;
			}
		}finally {
			session.close();
		}
		return false;
				
	}
//	/**
//	 * ȸ�� ���� �޼ҵ� (Ż��)
//	 * @param userid
//	 * @return �������� true ���н� false ��ȯ
//	 */
//	public boolean deleteMember(String userid) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//String sql = "delete from members where userid=?";
//		String sql = queryResource.getString("Member.delete");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			rs = pstm.executeQuery();
//			return rs.next();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ������ ����");
//
//		} 
//		factory.close(conn, pstm);
//		return false;
//	}
//
	/** Ư�� ȸ�� ����ȸ */
	public MemberDto select(String userid) {
		SqlSession session = factory.getSqlSession();
		MemberDto dto = null;
		try {
			dto = session.selectOne("memberselectone", userid);
			System.out.println(dto.getAddr());
		} finally {
			session.close();
		}
		return dto;
	}
//	/**
//	 * ȸ�� �� ��ȸ
//	 * @param userid
//	 * @return ������ �μ���ü, ���н� null
//	 */
//	public MemberDto select(String userid) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select * from members where userid='user01';
//		//String sql = "select * from members where userid=?";
//		String sql = queryResource.getString("Member.selectone");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			rs = pstm.executeQuery();
//			String username = null;
//			String userpw = null;
//			String addr = null;
//			String mobile = null;
//			String preference = null;
//			
//			if (rs.next()) {
//				username = rs.getString("username");
//				userpw = rs.getString("userpw");
//				addr = rs.getString("addr");
//				mobile = rs.getString("mobile");
//				preference = rs.getString("preference");
//				MemberDto dto = new MemberDto(userid, username, userpw, addr, mobile, preference);
//				return dto;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ����ȸ ����");
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	//�α��� ��й�ȣ �� 
	public String selectPw(String userid) {
		SqlSession session = factory.getSqlSession();
		String result = null;
		try {
			result = session.selectOne("selectPw2", userid);
		} finally {
			session.close();
		}
		return result;
	}
	
//	/**
//	 * ��й�ȣ ã��
//	 * @param userid
//	 * @return ��й�ȣ 
//	 */
//	public String selectPw(String userid) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select userpw from members where userid='user01';
//		String sql = "select userpw from members where userid=?";
//		
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			rs = pstm.executeQuery();
//			String userpw = null;
//		
//			if (rs.next()) {
//				userpw = rs.getString("userpw");
//				return userpw;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� �α��� ����");
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
	
	public String selectId(String username,String mobile) {
		HashMap<String,String> hashmap = new HashMap<>();
		hashmap.put("name", username);
		hashmap.put("mobile1", mobile);
		
		SqlSession session = factory.getSqlSession();
		String result = null;
		try {
			result = session.selectOne("selectid", hashmap);
			
		
		} finally {
			session.close();
		}
		
		return result;
	}
//	/**
//	 * ���̵� ã��
//	 * @param username
//	 * @param mobile
//	 * @return ���̵� 
//	 */
//	public String selectId(String username,String mobile) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select userid from members where username='�̹���' and mobile='010-3060-8952';
//		//String sql = "select userid from members where username=? and mobile=?";
//		String sql = queryResource.getString("Member.selectid");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, username);
//			pstm.setString(2, mobile);
//			rs = pstm.executeQuery();
//			String userid = null;
//
//			if (rs.next()) {
//				userid = rs.getString("userid");
//				return userid;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ���̵���ȸ ����");
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	public String selectPw(String userid,String mobile) {
		HashMap<String ,String> hashmap = new HashMap<>();
		hashmap.put("userid", userid);
		hashmap.put("mobile", mobile);
		String result=null;
		SqlSession session = factory.getSqlSession();
		try {
			result=session.selectOne("selectpw",hashmap);
			
		}finally {
			session.close();
		}
		return result;
		
	}
//	/**
//	 * ��й�ȣ ã��
//	 *
//	 * @param userid
//	 * @param mobile
//	 * @return ��й�ȣ
//	 */
//	public String selectPw(String userid,String mobile) {
//		
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select userpw from members where userid='user02' and mobile='010-3060-8952';
//		//String sql = "select userpw from members where userid=? and mobile=?";
//		String sql = queryResource.getString("Member.selectpw");
//		
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1, userid);
//			pstm.setString(2, mobile);
//			rs = pstm.executeQuery();
//			String userpw = null;
//			
//			if (rs.next()) {
//				userpw = rs.getString("userpw");
//				return userpw;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��й�ȣ ��ȸ ����");
//			
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	/** 
	 * ��ü �� ��ȸ 
	 * @return list
	 */
	public List<MemberDto> selectAll() {
		SqlSession session = factory.getSqlSession();
		List<MemberDto> list = null;
		try {
			list = session.selectList("memberselectlist",list);
			
		} finally {
			session.close();
		}
		return list;
	}
//	/**
//	 * ȸ�� ��ü ��ȸ
//	 * @return
//	 */
//	public ArrayList<MemberDto> selectAll() {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select * from members order by date;
//		//String sql = "select * from members";
//		String sql = queryResource.getString("Member.selectlist");
//
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			rs = pstm.executeQuery();
//			
//			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
//			
//			String userid =null;
//			String username = null;
//			String userpw = null;
//			String addr = null;
//			String mobile = null;
//			String preference = null;
//			
//			while(rs.next()) {
//				userid = rs.getString("userid");
//				username = rs.getString("username");
//				userpw = rs.getString("userpw");
//				addr = rs.getString("addr");
//				mobile = rs.getString("mobile");
//				preference = rs.getString("preference");
//				
//				list.add(new MemberDto(userid, username, userpw, addr, mobile, preference));
//				
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��ü��ȸ ����");
//		} 
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	
	public List<MemberDto> selectPref(String preference){
		SqlSession session = factory.getSqlSession();
		List<MemberDto> list = null;
		try {
			//list = session.selectList("selectsamepreference", dto);
			
			MemberDto dto = new MemberDto();
			dto.setPreference(preference);
			list= session.selectList("selectsamepreference", dto);
		}finally {
			session.close();
		}
		return list;
	}
//	/**
//	 * ���� ��ȣ�����о��� ��� ��ȸ �޼���
//	 * @param preference
//	 * @return
//	 */
//	public ArrayList<MemberDto> selectPref(String preference) {
//		
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		// select * from members where preference like ('%�̼�%'); 
//		//String sql = "select * from members where preference like (?)";
//		String sql = queryResource.getString("Member.selectsamepreference");
//		
//		try {
//			conn = factory.getConnection();
//			pstm = conn.prepareStatement(sql);
//			pstm.setString(1,"%"+preference+"%");
//			rs = pstm.executeQuery();
//			ArrayList<MemberDto> list = new ArrayList<MemberDto>();
//			
//			String userid =null;
//			String username = null;
//			String userpw = null;
//			String addr = null;
//			String mobile = null;
//		
//			while(rs.next()) {
//				userid = rs.getString("userid");
//				username = rs.getString("username");
//				userpw = rs.getString("userpw");
//				addr = rs.getString("addr");
//				mobile = rs.getString("mobile");
//				
//				list.add(new MemberDto(userid, username, userpw, addr, mobile, preference));
//				
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : ȸ�� ��ü��ȸ ����");
//			
//		} 
//		factory.close(conn, pstm);
//		return null;
//	}
//	
}
