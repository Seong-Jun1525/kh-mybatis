package com.kh.mybatis.member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDAO {

	public int insertMember(SqlSession sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public Member selectMember(SqlSession sqlSession, Member m) {
		/**
		 * selectOne : 한 행 조회
		 * selectList : 여러 행 조회
		 */
		return sqlSession.selectOne("memberMapper.selectMember", m);
	}

	public int updateMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}

	public int deleteMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.deleteMember", m);
	}

	public int updateMemberPwd(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMemberPwd", m);
	}

	public int updatePassword(SqlSession sqlSession, HashMap<String, String> data) {
		return sqlSession.update("memberMapper.updatePwd", data);
	}

	public int countMemberByUserId(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("memberMapper.selectCheckId", userId);
	}

	public int selectMemberNo(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("memberMapper.selectMemberNo", userId);
	}
	
}
