package com.keyworld.projectboard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLocks is a Querydsl query type for Locks
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocks extends EntityPathBase<Locks> {

    private static final long serialVersionUID = 1304441265L;

    public static final QLocks locks = new QLocks("locks");

    public final QAuditingFields _super = new QAuditingFields(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath description = createString("description");

    public final StringPath feature = createString("feature");

    public final ListPath<String, StringPath> filePathList = this.<String, StringPath>createList("filePathList", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath language = createBoolean("language");

    public final StringPath material = createString("material");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final NumberPath<Long> number = createNumber("number", Long.class);

    public final StringPath path1 = createString("path1");

    public final StringPath path2 = createString("path2");

    public final StringPath purpose = createString("purpose");

    public final StringPath surface = createString("surface");

    public final StringPath title = createString("title");

    public QLocks(String variable) {
        super(Locks.class, forVariable(variable));
    }

    public QLocks(Path<? extends Locks> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocks(PathMetadata metadata) {
        super(Locks.class, metadata);
    }

}

