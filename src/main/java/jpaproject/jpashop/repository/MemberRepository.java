package jpaproject.jpashop.repository;


import jpaproject.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {


}
