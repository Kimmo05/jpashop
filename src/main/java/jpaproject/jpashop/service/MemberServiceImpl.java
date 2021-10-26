package jpaproject.jpashop.service;


import jpaproject.jpashop.constant.Role;
import jpaproject.jpashop.domain.Address;
import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.domain.SearchMember;
import jpaproject.jpashop.dto.MemberDto;
import jpaproject.jpashop.dto.MemberFormDto;
import jpaproject.jpashop.dto.MemberPageDto;
import jpaproject.jpashop.exception.LoginIdNotFoundException;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements UserDetailsService, MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new LoginIdNotFoundException("해당하는 회원이 존재하지 않습니다")
        );
    }

    @Override
    public Member findMemberByLoginId(String loginId) {
        return memberRepository.findByloginId(loginId).orElseThrow(
                () -> new LoginIdNotFoundException("해당하는 회원이 존재하지 않습니다")
        );

    }
    @Transactional
    @Override
    public Long joinUser(MemberFormDto memberFormDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberFormDto.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));

        return memberRepository.save(memberFormDto.toEntity()).getId();
    }
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Optional<Member> userEntityWrapper = memberRepository.findByloginId(loginId);

        Member userEntity = userEntityWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(loginId)) { //로그인아이디가 admin@example.com이면 role이 어드민
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
            userEntity.setRole(Role.ADMIN);
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
            userEntity.setRole(Role.MEMBER);
        }

        int visitCount = userEntity.getVisitCount();
        userEntity.setVisitCount(++visitCount);

        return new User(userEntity.getLoginId(), userEntity.getPassword(), authorities);
    }
//    상세정보를 조회하는 메소드이며 사용자의 계정정보와 권한을 갖는 UserDetails 인터페이스를 반환해야한다.
//    매개변수는 로그인 시 입력한 아이디이다. 엔티티의 PK를 뜻하는게 아니고 유저를 식별할 수 있는 어떤 값을 의미한다.
//    스프링 시큐리티에서는 username라는 이름으로 사용한다.
//    로그인을 하는 form에서 name = "username"으로 요청해야 한다.
//    authorities.add~ 롤을 부여하는 코드이다. 롤 부여 방식에는 여러가지가 있지만, 회원가입할 때 Role을 정할 수 있도록 Role Entity를 만들어서 매핑하는 것이 좋다.
//    return은 스프링 시큐리티에서 제공하는 UserDetails를 구현한 User를 반환한다.
//    생성자의 각 매개변수는 순서대로 아이디, 비밀번호, 권한리스트이다.


    @Transactional(readOnly = true)
    @Override
    public boolean doubleCheckId(String registerId) {
        Optional<Member> findMember = memberRepository.findByloginId(registerId);
        return findMember.isPresent();
    } //이부분은 회원중복체크부분 아직 사용 안함

    @Override
    public MemberPageDto findAllMemberByPaging(Pageable pageable) {
        MemberPageDto memberPageDto = new MemberPageDto();
        Page<MemberDto> memberBoards = memberRepository.searchAll(pageable);
        int homeStartPage = Math.max(1, memberBoards.getPageable().getPageNumber());
        int homeEndPage = Math.min(memberBoards.getTotalPages(), memberBoards.getPageable().getPageNumber() + 5);

        memberPageDto.setMemberBoards(memberBoards);
        memberPageDto.setHomeStartPage(homeStartPage);
        memberPageDto.setHomeEndPage(homeEndPage);

        return memberPageDto;
    }

    @Override
    public MemberPageDto findAllMemberByConditionByPaging(SearchMember searchMember, Pageable pageable) {
        MemberPageDto memberPageDto = new MemberPageDto();
        Page<MemberDto> memberBoards = memberRepository.searchByCondition(searchMember, pageable);

        int startPage = Math.max(1, memberBoards.getPageable().getPageNumber() - 2);
        int endPage = Math.min(memberBoards.getTotalPages(), startPage + 4);

        memberPageDto.setMemberBoards(memberBoards);
        memberPageDto.setHomeStartPage(startPage);
        memberPageDto.setHomeEndPage(endPage);

        return memberPageDto;
    }

    @Override
    public Page<Member> findAllMemberByOrderByCreatedAt(Pageable pageable) {
        return memberRepository.findAllByOrderByCreatedAt(pageable);
    }
}
