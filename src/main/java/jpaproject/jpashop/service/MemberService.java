package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.domain.SearchMember;
import jpaproject.jpashop.dto.MemberFormDto;
import jpaproject.jpashop.dto.MemberPageDto;
import org.springframework.data.domain.Pageable;


public interface MemberService {

     Long joinUser(MemberFormDto memberFormDto);
//    회원가입 메소드
    Member findMemberByLoginId(String loginId);

    Member findMemberById(Long id);
    //    Pk를 이용한 회원 찾기
    boolean doubleCheckId(String registerId);
    //  회원 중복 체크
    MemberPageDto findAllMemberByPaging(Pageable pageable);

    MemberPageDto findAllMemberByConditionByPaging(SearchMember searchMember, Pageable pageable);
}
