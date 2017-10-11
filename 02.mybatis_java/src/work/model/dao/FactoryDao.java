package work.model.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ##Factory Pattern
 * 공장 객체 : db 연결객체 제공, 자원해제
 * dao pattern 적용
 * 
 * @author kosta
 *
 */

public class FactoryDao {
	
	private static FactoryDao instance = new FactoryDao();
	
	private FactoryDao() {

	}
	public static FactoryDao getInstance() {
		return instance;
	}
	
	/** mybatis : SqlSessionFactory 변수 */
	private static SqlSessionFactory factory;
	
	/** mybatis : SqlSessionFactory 객체 생성을 위한 초기화 작업 */
	static {
		String resource = "resources/mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.out.println("Error Message : " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch(IOException e) {
				System.out.println("Error Message : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public SqlSession getSqlSession() {
		return getSqlSession(false);
	}

	public SqlSession getSqlSession(boolean autoCommit) {
		return factory.openSession(autoCommit);
	}

}
