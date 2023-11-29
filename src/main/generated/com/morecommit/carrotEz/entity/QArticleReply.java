package com.morecommit.carrotEz.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleReply is a Querydsl query type for ArticleReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticleReply extends EntityPathBase<ArticleReply> {

    private static final long serialVersionUID = 835481854L;

    public static final QArticleReply articleReply = new QArticleReply("articleReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regTime = _super.regTime;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QArticleReply(String variable) {
        super(ArticleReply.class, forVariable(variable));
    }

    public QArticleReply(Path<? extends ArticleReply> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleReply(PathMetadata metadata) {
        super(ArticleReply.class, metadata);
    }

}

