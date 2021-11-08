package kr.co.pjshop.repository;


import kr.co.pjshop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>,MemberRepositoryCustom {

    Member findByEmail(String email);

    Optional<Member> findByloginId(String loginId);
}