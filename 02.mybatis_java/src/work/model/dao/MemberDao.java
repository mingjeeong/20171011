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
	 * </pre>ㅣ
	 * 
	 * @author kosta
	 *
	 */
public class MemberDao {
	/** singleton pattern : 해당 클래스 타입의 인스턴스 변수 final 선언 */
	private static final MemberDao INSTANCE = new MemberDao() ;
	
	/** singleton pattern : private constructor 
	 *  하나의 인스턴스 생성 후에 내부에서 객체를 생성시에 예외 발생
	 *  즉, 반드시 하나의 인스턴스만으로 객체 서비스를 위한 대비책 
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
//	/** db연결을 위한 property 멤버변수 선언 */
//	private String driver ;
//	private String url ;
//	private String username ;
//	private String password ;
//	private ResourceBundle bundle;
//
//	//sql 외부 지원 파일 멤버변수 선언
//	private ResourceBundle queryResource;
//	
//	/**
//	 * 기본 생성자
//	 */
//	private MemberDao() {
//		//외부 지원 파일 가져와서 멤버변수 초기화 수행
//		queryResource =ResourceBundle.getBundle("conf/query");
//
//	}
//	
//	public static MemberDao getInstance() {
//		return instance;
//	}
//
	/**
	 * 회원 추가
	 * @param dto
	 * @return 성공 1 실패 0
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
//	 * 회원 레코드 추가 메소드
//	 * 
//	 * @param dto
//	 * @return 실패시 0 , 성공시  등록된 레코드 행수 반환
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
//			pstm = conn.prepareStatement(sql);//미완성 정적 sql통로
//			pstm.setString(1, userid);
//			pstm.setString(2, username);
//			pstm.setString(3, userpw);
//			pstm.setString(4, addr);
//			pstm.setString(5, mobile);
//			pstm.setString(6, preference);//완벽한 정적 sql통로
//	
//			return pstm.executeUpdate(); //sql 수행 :완벽한 통로이므로 sql지정해서는 안됨
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : 회원등록오류");
//		} 
//		factory.close(conn, pstm);
//		return 0;
//	}
//
//		
	/*
	 * 중복 아이디 검색 메서드
	 * -- CRUD 에서 수행전 내부에서 호출 사용 : private 
	 * -- 회원가입시 가입 요청전에 아이디 중복확인시에 사용 : public
	 * -- 단일값 : boolean, 등급(String)
	 * 
	 * @param userid
	 * @return 존재시 true, 미존재시 false
	 * 
	 * 1. SqlSessionFactory에게 SqlSession 객체 요청
	 * 2. SqlSession 객체를 통해서 sql-mapper.xml에 작성된 sql 구문 수행요청
	 * 3. 수행결과 처리
	 * 4. SqlSession 객체는 해당 메서드 수행후에 자원반납되어야함 
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
//	 * 아이디 중복 조회 메서드
//	 * @param userid
//	 * @return 존재하면 true 존재하지 않으면 false
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
//			System.out.println("error : 회원등록 중복조회 오류");
//		} 
//		factory.close(conn, pstm);
//		
//		return false;
//	}
//	

//	/**
//	 * 회원 비밀번호 변경 메소드  
//	 * @param dto
//	 * @param pw
//	 * @return 성공시 1, 실패시 0 리턴
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
//			pstm = conn.prepareStatement(sql);// 미완성 정적 sql통로
//			pstm.setString(1, pw);
//			pstm.setString(2, userid);// 완벽한 정적 sql통로
//
//			return pstm.executeUpdate(); // sql 수행 :완벽한 통로이므로 sql지정해서는 안됨
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : 회원 비밀번호 변경 오류");
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
//	 * 비밀번호 변경
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
//			pstm = conn.prepareStatement(sql);// 미완성 정적 sql통로
//			pstm.setString(1, pw);
//			pstm.setString(2, id);// 완벽한 정적 sql통로
//			
//			return pstm.executeUpdate(); // sql 수행 :완벽한 통로이므로 sql지정해서는 안됨
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : 회원 비밀번호 변경 오류");
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
//	 * 회원정보 변경
//	 * @param pw
//	 * @param addr
//	 * @param mobile
//	 * @param preference
//	 * @param id
//	 * @return 성공 1리턴 실패0리턴
//	 */
//	public int update(String pw , String addr ,String mobile,String preference ,String id) {
//	
//		Connection conn = null;
//		// Statement stm = null;
//		PreparedStatement pstm = null;
//		
//		// update members set userpw='12345',addr ='경기도',mobile='010-7421-8900',preference='' where userid='user04';
//		//String sql = "update members set userpw=?,addr =?,mobile=?,preference=?  where userid=?";
//		String sql = queryResource.getString("Member.update");
//		
//		try {
//			conn = factory.getConnection();
//			// stm = conn.createStatement();
//			// return stm.executeUpdate(sql);
//			pstm = conn.prepareStatement(sql);// 미완성 정적 sql통로
//			pstm.setString(1, pw);
//			pstm.setString(2, addr);// 완벽한 정적 sql통로
//			pstm.setString(3, mobile);
//			pstm.setString(4, preference);
//			pstm.setString(5, id);
//			
//			return pstm.executeUpdate(); // sql 수행 :완벽한 통로이므로 sql지정해서는 안됨
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("error : 회원 비밀번호 변경 오류");
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
//	 * 회원 삭제 메소드 (탈퇴)
//	 * @param userid
//	 * @return 삭제성공 true 실패시 false 반환
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
//			System.out.println("error : 회원삭제 오류");
//
//		} 
//		factory.close(conn, pstm);
//		return false;
//	}
//
	/** 특정 회원 상세조회 */
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
//	 * 회원 상세 조회
//	 * @param userid
//	 * @return 성공시 부서객체, 실패시 null
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
//			System.out.println("error : 회원 상세조회 오류");
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	//로그인 비밀번호 비교 
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
//	 * 비밀번호 찾기
//	 * @param userid
//	 * @return 비밀번호 
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
//			System.out.println("error : 회원 로그인 오류");
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
//	 * 아이디 찾기
//	 * @param username
//	 * @param mobile
//	 * @return 아이디 
//	 */
//	public String selectId(String username,String mobile) {
//
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		//select userid from members where username='이민정' and mobile='010-3060-8952';
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
//			System.out.println("error : 회원 아이디조회 오류");
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
//	 * 비밀번호 찾기
//	 *
//	 * @param userid
//	 * @param mobile
//	 * @return 비밀번호
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
//			System.out.println("error : 회원 비밀번호 조회 오류");
//			
//		}
//		factory.close(conn, pstm);
//		return null;
//	}
//	
	/** 
	 * 전체 고객 조회 
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
//	 * 회원 전체 조회
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
//			System.out.println("error : 회원 전체조회 오류");
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
//	 * 같은 선호도서분야인 사람 조회 메서드
//	 * @param preference
//	 * @return
//	 */
//	public ArrayList<MemberDto> selectPref(String preference) {
//		
//		Connection conn = null;
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		
//		// select * from members where preference like ('%미술%'); 
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
//			System.out.println("error : 회원 전체조회 오류");
//			
//		} 
//		factory.close(conn, pstm);
//		return null;
//	}
//	
}
