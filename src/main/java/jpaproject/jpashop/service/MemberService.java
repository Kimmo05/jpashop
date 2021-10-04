package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Member;

public interface MemberService {

    Member findMemberById(Long id);
//    Pk를 이용한 회원 찾기

    Member findMemberByLoginId(String loginId);
// 로그인아이디

}
