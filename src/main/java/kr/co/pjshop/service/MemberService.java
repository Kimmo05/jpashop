package kr.co.pjshop.service;


import kr.co.pjshop.dto.MemberInfoDto;
import kr.co.pjshop.dto.MemberPageDto;
import kr.co.pjshop.dto.MyPageDto;
import kr.co.pjshop.dto.ProfileDto;
import kr.co.pjshop.entity.Member;
import kr.co.pjshop.entity.SearchMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {

    Member findMemberById(Long id);
//    Pk를 이용한 회원 찾기

    Member findMemberByLoginId(String loginId);

    Long joinUser(MemberInfoDto memberInfoDto);
//    회원가입 메소드



    void updateProfile(String loginId, ProfileDto profileDto);
//    개인 정보 수정 메소드

    Long changePassword(Long id, String password);
//    비밀번호 변경 메소드

    MyPageDto showMySimpleInfo(String loginId);
//    내정보(마일리지, 등급, 이름) 보여주는 메소드

    boolean doubleCheckId(String registerId);
//    회원 중복 체크 메소드

    void deleteMemberByLoginId(String loginId);
//    회원 탈퇴 기능 메소드

    Long deleteById(Long id);
//    Pk를 이용한 회원 삭제 기능

    ProfileDto showProfileData(String loginId);
//    개인 상세정보 보여주는 메소드

    Page<Member> findAllMemberByOrderByRegTime(Pageable pageable);
//    등록 순서에 따라 회원 조회하는 메소드

    MemberPageDto findAllMemberByPaging(Pageable pageable);

    MemberPageDto findAllMemberByConditionByPaging(SearchMember searchMember, Pageable pageable);

    int getVisitCount();
//    전체 방문자 수 구하는 기능
}
