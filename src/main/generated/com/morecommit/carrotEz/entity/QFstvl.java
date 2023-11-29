package com.morecommit.carrotEz.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFstvl is a Querydsl query type for Fstvl
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFstvl extends EntityPathBase<Fstvl> {

    private static final long serialVersionUID = -1637656557L;

    public static final QFstvl fstvl = new QFstvl("fstvl");

    public final StringPath Detail = createString("Detail");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath locationDtl = createString("locationDtl");

    public final StringPath name = createString("name");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QFstvl(String variable) {
        super(Fstvl.class, forVariable(variable));
    }

    public QFstvl(Path<? extends Fstvl> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFstvl(PathMetadata metadata) {
        super(Fstvl.class, metadata);
    }

}

