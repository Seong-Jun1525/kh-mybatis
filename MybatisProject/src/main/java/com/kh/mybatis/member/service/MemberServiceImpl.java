package com.kh.mybatis.member.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDAO;
import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.template.Template;

public class MemberServiceImpl implements MemberService {

	private MemberDAO mDAO = new MemberDAO();
	
	@Override
	public int insertMember(Member m) {
		/**
		 * 기존(JDBC) 방식
		 * - Connection 객체 생성
		 * - DAO에게 Connection 객체와 전달받은 데이터 전달(요청)
		 * - 결과에 따라 (DMl) -> 트랜잭션 처리
		 * - Connection 객체 반납
		 * - 결과 리턴
		 */
		SqlSession sqlSession = Template.getSqlSession();
		int result = mDAO.insertMember(sqlSession, m);
		
		System.out.println(result);
		
		if(result > 0) sqlSession.commit();
		// rollback은 생략 가능 (여러 개의 DML을 실행시켰을 경우에는 작성해야함)
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public Member selectMember(Member m) {
		/**
		 * SqlSession 객체 생성
		 * DAO 객체에게 작업 요청 후 결과 받기
		 * SqlSession 객체 반납
		 * 결과리턴
		 */
		
		SqlSession sqlSession = Template.getSqlSession();
		Member loginUser = mDAO.selectMember(sqlSession, m);
		
		sqlSession.close();
		
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = mDAO.updateMember(sqlSession, m);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public int deleteMember(String userId, String userPwd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Member m = new Member(userId, userPwd);
//		HashMap<String, String> hMap = new HashMap<>();
//		hMap.put("userId", userId);
//		hMap.put("userPwd", userPwd);
		
		int result = mDAO.deleteMember(sqlSession, m);

		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		return result;
	}

	@Override
	public int updateMemberPwd(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = mDAO.updateMemberPwd(sqlSession, m);

		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public int updatePassword(String userId, String userPwd, String changeUserPwd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		HashMap<String, String> data = new HashMap<>();
		
		// HashMap을 사용하면 내가 지정한 키값으로 SQL문의 # {} 안에 작성할 수 있다
		data.put("id", userId);
		data.put("currentPwd", userPwd);
		data.put("newPwd", changeUserPwd);
		
		int result = mDAO.updatePassword(sqlSession, data);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public int countMemberByUserId(String userId) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = mDAO.countMemberByUserId(sqlSession, userId);
		
		sqlSession.close();
		
		return result;
	}

}
