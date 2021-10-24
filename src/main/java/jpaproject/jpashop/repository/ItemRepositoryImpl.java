package jpaproject.jpashop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaproject.jpashop.domain.QItem;
import jpaproject.jpashop.domain.SearchItem;
import jpaproject.jpashop.dto.ItemDto;
import jpaproject.jpashop.dto.QItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    public ItemRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemDto> searchAllItem(Pageable pageable) {
        QueryResults<ItemDto> results = queryFactory
                .select(new QItemDto(
                        QItem.item.id,
                        QItem.item.itemName,
                        QItem.item.firstCategory,
                        QItem.item.price,
                        QItem.item.saleStatus,
                        QItem.item.imgUrl,
                        QItem.item.color,
                        QItem.item.rep,
                        QItem.item.itemIdx
                ))
                .from(QItem.item)
                .where(QItem.item.rep.eq(true))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<ItemDto> searchAllItemByCondition(SearchItem search, Pageable pageable) {
        if (search.getCmode().equals("whole")) {
            QueryResults results = queryFactory
                    .select(new QItemDto(
                            QItem.item.id,
                            QItem.item.itemName,
                            QItem.item.firstCategory,
                            QItem.item.price,
                            QItem.item.saleStatus,
                            QItem.item.imgUrl,
                            QItem.item.color,
                            QItem.item.rep,
                            QItem.item.itemIdx
                    ))
                    .from(QItem.item)
                    .where(QItem.item.rep.eq(true), saleStatusEq(search.getSalestatus()), itemNameEq(search.getItem_name()))
                    .orderBy(QItem.item.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();

            List<ItemDto> content = results.getResults();
            long total = results.getTotal();

            return new PageImpl<>(content, pageable, total);
        } else {
            QueryResults results = queryFactory
                    .select(new QItemDto(
                            QItem.item.id,
                            QItem.item.itemName,
                            QItem.item.firstCategory,
                            QItem.item.price,
                            QItem.item.saleStatus,
                            QItem.item.imgUrl,
                            QItem.item.color,
                            QItem.item.rep,
                            QItem.item.itemIdx
                    ))
                    .from(QItem.item)
                    .where(QItem.item.rep.eq(true), saleStatusEq(search.getSalestatus()), cmodeEq(search.getCmode()), itemNameEq(search.getItem_name()))
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetchResults();

            List<ItemDto> content = results.getResults();
            long total = results.getTotal();

            return new PageImpl<>(content, pageable, total);
        }
    }
    private BooleanExpression saleStatusEq(String saleStatusCondition) {
        if (StringUtils.isEmpty(saleStatusCondition)) {
            return null;
        }
        return QItem.item.saleStatus.equalsIgnoreCase(saleStatusCondition);
    }
    private BooleanExpression cmodeEq(String cmodeCondition) {
        if (StringUtils.isEmpty(cmodeCondition)) {
            return null;
        }
        return QItem.item.firstCategory.equalsIgnoreCase(cmodeCondition);
    }

    private BooleanExpression itemNameEq(String itemNameCondition) {
        if (StringUtils.isEmpty(itemNameCondition)) {
            return null;
        }
        return QItem.item.itemName.likeIgnoreCase("%" + itemNameCondition + "%");
    }



}
