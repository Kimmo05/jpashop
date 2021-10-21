package jpaproject.jpashop.repository;

import jpaproject.jpashop.domain.SearchMember;
import jpaproject.jpashop.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberRepositoryCustom {
    Page<MemberDto> searchAll(Pageable pageable);

    Page<MemberDto> searchByCondition(SearchMember search, Pageable pageable);
}
