package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {
    Optional<Member> findByloginId(String loginId);

    Page<Member> findAllByOrderByCreatedAt(Pageable pageable);

    @Query("select sum(m.visitCount) from Member m")
    int visitCountResult();

    void deleteByLoginId(String loginId);
}
