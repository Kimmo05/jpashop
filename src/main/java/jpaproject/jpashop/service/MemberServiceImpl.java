package jpaproject.jpashop.service;


import jpaproject.jpashop.constant.Role;
import jpaproject.jpashop.domain.Address;
import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.dto.MemberFormDto;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements UserDetailsService, MemberService{
    private final MemberRepository memberRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


   public Long signUp(MemberFormDto memberFormDto ){

       validateDuplicateMember(memberFormDto.getLoginId());

        //회원정보저장하는곳

       Member member = Member.builder()
               .address(new Address(memberFormDto.getDetailStreet(),memberFormDto.getStreet(),memberFormDto.getZipcode()))
               .loginId(memberFormDto.getLoginId())
               .email(memberFormDto.getEmail())
               .name(memberFormDto.getName())
               .password(passwordEncoder.encode(memberFormDto.getPassword()))
               .phoneNumber(memberFormDto.getPhoneNumber())
               .build();
       Long memberId = memberRepository.save(member).getId();
       return memberId;
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

    private void validateDuplicateMember(String loginId) {
        Optional<Member> findMember= memberRepository.findByloginId(loginId);
        if(findMember.isPresent())
            throw new IllegalStateException("이미 존재하는 회원입니다");
    }


    private Member validateExistMember(Optional<Member> member) {
       if(!member.isPresent())
           throw new IllegalStateException("존재하지 않는 유저입니다,");
       return member.get();
    }

    public Member findMember(Long Id) {
        Member member = validateExistMember(memberRepository.findById(Id));

        return member;
    }
    @Transactional(readOnly = true)
    @Override
    public boolean doubleCheckId(String registerId) {
        Optional<Member> findMember = memberRepository.findByloginId(registerId);
        return findMember.isPresent();
    } //이부분은 회원중복체크부분 아직 사용 안함
    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }
}
