package jpaproject.jpashop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBase is a Querydsl query type for Base
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QBase extends EntityPathBase<Base> {

    private static final long serialVersionUID = 90549678L;

    public static final QBase base = new QBase("base");

    public final QBaseTime _super = new QBaseTime(this);

    public final StringPath createBy = createString("createBy");

    public final StringPath modifiedBy = createString("modifiedBy");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QBase(String variable) {
        super(Base.class, forVariable(variable));
    }

    public QBase(Path<? extends Base> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBase(PathMetadata metadata) {
        super(Base.class, metadata);
    }

}

