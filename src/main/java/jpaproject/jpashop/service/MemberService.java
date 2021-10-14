package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.dto.MemberFormDto;

public interface MemberService {

    Long signUp(MemberFormDto memberFormDto);
    //회원가입


    boolean doubleCheckId(String registerId);
    //  회원 중복 체크

}
