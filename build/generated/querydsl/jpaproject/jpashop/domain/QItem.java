package jpaproject.jpashop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 90776048L;

    public static final QItem item = new QItem("item");

    public final QBaseTime _super = new QBaseTime(this);

    public final StringPath color = createString("color");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath firstCategory = createString("firstCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath itemDetail = createString("itemDetail");

    public final StringPath itemInfo = createString("itemInfo");

    public final StringPath itemNm = createString("itemNm");

    public final EnumPath<jpaproject.jpashop.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", jpaproject.jpashop.constant.ItemSellStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath secondCategory = createString("secondCategory");

    public final StringPath size = createString("size");

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public final StringPath thirdCategory = createString("thirdCategory");

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

