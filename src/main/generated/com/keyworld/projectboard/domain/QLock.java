package com.keyworld.projectboard.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLock is a Querydsl query type for Lock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLock extends EntityPathBase<Lock> {

    private static final long serialVersionUID = -789205246L;

    public static final QLock lock = new QLock("lock");

    public final StringPath feature = createString("feature");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath language = createBoolean("language");

    public final StringPath material = createString("material");

    public final NumberPath<Long> number = createNumber("number", Long.class);

    public final StringPath purpose = createString("purpose");

    public final StringPath surface = createString("surface");

    public final StringPath title = createString("title");

    public QLock(String variable) {
        super(Lock.class, forVariable(variable));
    }

    public QLock(Path<? extends Lock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLock(PathMetadata metadata) {
        super(Lock.class, metadata);
    }

}

