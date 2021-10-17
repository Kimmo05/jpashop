package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.dto.MemberFormDto;


public interface MemberService {

     Long joinUser(MemberFormDto memberFormDto);
//    회원가입 메소드
    Member findMemberByLoginId(String loginId);

    Member findMemberById(Long id);
    //    Pk를 이용한 회원 찾기
    boolean doubleCheckId(String registerId);
    //  회원 중복 체크

}
