package work.controller;

import java.util.ArrayList;

import work.model.dto.MemberDto;
import work.model.service.Service;

/**
 * <pre>
 * ##Controller 클래스 역할
 * 
 *1.요청파악 : 메서드 구현
 *2.요청데이터 추출 : 메서드 아규먼트 전달
 *3.데이터 검증 : 
 *4.Model 요청의뢰
 *5.Controller에서 Model 요청 처리결과 받아서 필요한 설정
 *6.응답 결과 전송
 *7.응답 결과 출력(사용)
 * </pre>
 * 
 * @author kosta
 *
 */

public class Controller {
	// 요청을 처리하는 모델(서비스) 객체 변수
	private Service s = new Service();

	// 부서등록 요청 제어 메서드 (부서객체) : 반환타입 결과메세지 String
	// 메서드 형식 선언 signature

	public boolean dupli(String id) {
		if (id.trim().length() > 20 || id.trim().length() < 5) {
			System.out.println("Error : 아이디는 5~20자리 입력해주세요\n");
			return true;
		}

		if (s.duplicate(id)) {

			System.out.println("Error : 이미 등록된 아이디 입니다. 아이디를 다시 입력해주세요.");

			return true;

		} else {
			System.out.println("사용 가능한 아이디 입니다.");
			return false;
		}
	}

	/**
	 * 부서등록 요청 제어 메서드
	 * 
	 * @param dto
	 * @return 등록 결과 메세지
	 */

	public String insert(MemberDto dto) {
		StringBuilder returnValue = new StringBuilder();

		if (dto == null) {
			returnValue.append("Error : 변수가 존재하지 않음");
			return returnValue.toString();
		}

		if (dto.getUserid() == null || dto.getUserid().trim().length() == 0)
			returnValue.append("Error : 아이디를 입력하세요 \n");

		if (dto.getMobile() == null || dto.getMobile().trim().length() == 0)
			returnValue.append("Error :  핸드폰 번호를 입력하세요 \n");
		else if (dto.getMobile().trim().length() != 13)
			returnValue.append("Error : 핸드폰 번호는'-'을 포함하여 입력하세요\n");

		if (dto.getAddr() == null || dto.getAddr().trim().length() == 0)
			returnValue.append("Error : 주소를 입력하세요\n");

		if (dto.getUserpw() == null || dto.getUserpw().trim().length() == 0)
			returnValue.append("Error : 비밀번호를 입력하세요\n");
		else if (dto.getUserpw().trim().length() > 20 || dto.getUserpw().trim().length() < 8)
			returnValue.append("Error : 비밀번호는 8~20자리 입력해 주세요\n");

		if (dto.getPreference() == null || dto.getUserpw().trim().length() == 0)
			returnValue.append("Error : 선호하는 도서 분야를 입력해주세요");

		if (returnValue.length() != 0) {
			return returnValue.toString();
		}

		// 요청처리를 위한 데이터 검증 완료
		int result = s.add(dto);

		// 6.응답결과 전송
		if (result == 1) {
			return "1success :회원가입이 정상 완료되었습니다";
		} else {
			return "2false : 회원가입이 정상적으로 이루어지지 않았습니다";

		}

	}
	
	

	/**
	 * 부서 상세조회(부서번호) : 부서객체
	 * 
	 * @param deptno
	 * @return
	 */
	public String get(int deptno) {
		return null;
	}

	
	/**
	 * 부서 전체조회() : 부서객체들
	 * 
	 * @param dto
	 * @return
	 */
	public ArrayList<MemberDto> getAll(MemberDto dto) {
		return null;
	}

	/**
	 * 부서 변경 (부서명,부서위치,부서번호) : 결과메세지
	 * 
	 * @param dname
	 * @param dloc
	 * @param deptno
	 */
	public void set(String dname, String dloc, int deptno) {

	}

	/**
	 * 부서 삭제(부서번호) : 결과메세지
	 * 
	 * @param deptno
	 */
	public void remove(int deptno) {

	}

	/**
	 * 부서명만 변경
	 * 
	 * @param dname
	 */
	public void setName(String dname) {

	}

	/**
	 * 같은 지역의 부서 전체 조회
	 * 
	 * @param loc
	 */
	public void getLoc(String loc) {

	}

}
