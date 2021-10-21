package jpaproject.jpashop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaproject.jpashop.domain.QMember;
import jpaproject.jpashop.domain.SearchMember;
import jpaproject.jpashop.dto.MemberDto;
import jpaproject.jpashop.dto.QMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {

        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<MemberDto> searchAll(Pageable pageable) {
        QueryResults<MemberDto> results = queryFactory
                .select(new QMemberDto(
                        QMember.member.id,
                        QMember.member.name,
                        QMember.member.loginId,
                        QMember.member.role,
                        QMember.member.phoneNumber,
                        QMember.member.visitCount,
                        QMember.member.orderCount,
                        QMember.member.createdAt
                ))
                .from(QMember.member)
                .orderBy(QMember.member.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MemberDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MemberDto> searchByCondition(SearchMember search, Pageable pageable) {
        QueryResults<MemberDto> results = null;

        if (search.getSearchCondition().equals("userid")) {
            results = queryFactory
                    .select(new QMemberDto(
                            QMember.member.id,
                            QMember.member.name,
                            QMember.member.loginId,
                            QMember.member.role,
                            QMember.member.phoneNumber,
                            QMember.member.visitCount,
                            QMember.member.orderCount,
                            QMember.member.createdAt
                    ))
                    .from(QMember.member)
                    .where(loginIdEq(search.getSearchKeyword()))
                    .orderBy(QMember.member.createdAt.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        } else if (search.getSearchCondition().equals("username")) {
            results = queryFactory
                    .select(new QMemberDto(
                            QMember.member.id,
                            QMember.member.name,
                            QMember.member.loginId,
                            QMember.member.role,
                            QMember.member.phoneNumber,
                            QMember.member.visitCount,
                            QMember.member.orderCount,
                            QMember.member.createdAt
                    ))
                    .from(QMember.member)
                    .where(nameEq(search.getSearchKeyword()))
                    .orderBy(QMember.member.createdAt.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();
        }

        List<MemberDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression loginIdEq(String loginIdCondition) {
        if (StringUtils.isEmpty(loginIdCondition)) {
            return null;
        }
        return QMember.member.loginId.likeIgnoreCase("%" + loginIdCondition + "%");
    }

    private BooleanExpression nameEq(String nameCondition) {
        if (StringUtils.isEmpty(nameCondition)) {
            return null;
        }
        return QMember.member.name.likeIgnoreCase("%" + nameCondition + "%");
    }
}
