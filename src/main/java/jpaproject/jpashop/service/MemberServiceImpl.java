package jpaproject.jpashop.service;


import jpaproject.jpashop.domain.Member;
import jpaproject.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Member findMemberById(Long id) {
        return null;
    }

    @Override
    public Member findMemberByLoginId(String loginId) {
        return null;
    }
}
