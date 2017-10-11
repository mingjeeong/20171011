package work.model.dto;

import java.io.Serializable;

/**
 * <pre>
 * ##dtoŬ���� ����
 *  data transfer object pattern 
 *  ������ �����ؼ� ���� 
 *  encapsulation, ����ȭ��ü
 *  ������ : equals(), hashCode(),toString 
 *  ������ �ߺ� ���� 
 *  ���̺� ���� �����ؼ� �ۼ� 
 *  �ڹ� ������ ��Ģ/ DB SQL �÷��� ��Ģ
 * </pre>
 * 
 * @author kosta
 * @version ver.1.0
 * @since jdk1.4
 *
 */

public class MemberDto implements Serializable {
	/** ȸ�� ���̵� */
	public String userid;
	/** ȸ�� ��й�ȣ */
	private String userpw;
	/** ȸ�� �̸� */
	private String username;
	/** ȸ�� �ּ� */
	private String addr; //address
	/** ȸ�� ��ȣ */
	private String mobile;
	/** ȸ�� ��ȣ �о� */
	private String preference;

	/** �⺻������ */
	public MemberDto() {

	}

	/**
	 * �ʼ������� �ʱ�ȭ ������
	 * @param userid
	 * @param userpw
	 */
	public MemberDto(String userid, String userpw) {

		this.userid = userid;
		this.userpw = userpw;
	}
	public MemberDto(String userid) {
		this.userid = userid;
	}

	/**
	 * ��ü ������ �ʱ�ȭ ������
	 * @param userid
	 * @param userpw
	 * @param username
	 * @param addr
	 * @param mobile
	 * @param preference
	 */
	public MemberDto(String userid, String username, String userpw,  String addr, String mobile, String preference) {
	
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.addr = addr;
		this.mobile = mobile;
		this.preference = preference;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((preference == null) ? 0 : preference.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((userpw == null) ? 0 : userpw.hashCode());
		return result;
	}

	@Override

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDto other = (MemberDto) obj;	
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("���̵� : "+userid);
		builder.append(",\t");
		builder.append("��й�ȣ : "+userpw);
		builder.append(",\t");
		builder.append("�̸� : "+username);
		builder.append(",\t");
		builder.append("�ּ� : "+addr);
		builder.append(",\t");
		builder.append("�޴�����ȣ : "+mobile);
		builder.append(",\t");
		builder.append("��ȣ ���� �о� : "+preference);
		return builder.toString();
	}
	
	

	
	

}
