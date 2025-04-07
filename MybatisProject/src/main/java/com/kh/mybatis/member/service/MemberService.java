package com.kh.mybatis.member.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {
	// 회원가입
	int insertMember(Member m);
	
	// 로그인
	Member selectMember(Member m);
	
	// 회원 정보 수정
	int updateMember(Member m);
	
	// 비밀번호 변경
	int updateMemberPwd(Member m);
	int updatePassword(String userId, String userPwd, String changeMemberPwd);
	
	// 회원 탈퇴
	int deleteMember(String userId, String userPwd);
	
	// 중복 아이디 체크
	int countMemberByUserId(String userId);
	
	// 회원 번호
	int selectMemberNo(String userId);
}
