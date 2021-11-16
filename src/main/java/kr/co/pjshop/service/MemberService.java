package kr.co.pjshop.service;

import kr.co.pjshop.constant.Role;
import kr.co.pjshop.dto.MemberDto;
import kr.co.pjshop.dto.MemberPageDto;
import kr.co.pjshop.dto.MyPageDto;
import kr.co.pjshop.dto.ProfileDto;
import kr.co.pjshop.entity.Member;
import kr.co.pjshop.entity.SearchMember;
import kr.co.pjshop.repository.MemberRepository;
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
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이메일중복,이미 가입된 회원입니다.");
        }
    }

    private void validateDuplicateMemberId(Member member){
        Optional<Member> findMember = memberRepository.findByloginId(member.getLoginId());
        if(findMember != null){
            throw new IllegalStateException("아이디중복,이미 가입된 회원입니다.");
        }
    }
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByloginId(loginId);

        Member userEntity = userEntityWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(loginId)) { //로그인아이디가 admin이면 role이 어드민
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
            userEntity.setRole(Role.ADMIN);
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
            userEntity.setRole(Role.MEMBER);
        }
        return new User(userEntity.getLoginId(), userEntity.getPassword(), authorities);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
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

    public Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("해당하는 회원이 존재하지 않습니다")
        );
    }

    @Transactional
    public Long deleteById(Long id) {
        memberRepository.deleteById(id);
        return id;
    }
    @Transactional
    public void updateProfile(String loginId, ProfileDto profileDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Member findMember = memberRepository.findByloginId(loginId).orElseThrow(
                () -> new IllegalStateException("해당하는 회원을 찾을 수 없습니다")
        );
        findMember.setName(profileDto.getName());
        findMember.setLoginId(profileDto.getLoginId());
        findMember.setPassword(passwordEncoder.encode(profileDto.getPassword()));
        findMember.setPhoneNumber(profileDto.getPhoneNumber());
        findMember.setEmail(profileDto.getEmail());
    }

    @Transactional
    public Long changePassword(Long id, String password) {
        Member oneMember = findMemberById(id);
        oneMember.setPassword(password);
        return oneMember.getId();
    }
    public MyPageDto showMySimpleInfo(String loginId) {

        MyPageDto myPageDto = new MyPageDto();

        Member findMember = memberRepository.findByloginId(loginId).orElseThrow(
                () -> new IllegalStateException("해당하는 회원이 존재하지 않습니다")
        );

        myPageDto.setName(findMember.getName());
        myPageDto.setEmail(findMember.getEmail());
        myPageDto.setPhoneNumber(findMember.getPhoneNumber());

        return myPageDto;
    }
}