package com.morecommit.carrotEz.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleImage is a Querydsl query type for ArticleImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticleImage extends EntityPathBase<ArticleImage> {

    private static final long serialVersionUID = 827393903L;

    public static final QArticleImage articleImage = new QArticleImage("articleImage");

    public final NumberPath<Long> articleNumber = createNumber("articleNumber", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public QArticleImage(String variable) {
        super(ArticleImage.class, forVariable(variable));
    }

    public QArticleImage(Path<? extends ArticleImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleImage(PathMetadata metadata) {
        super(ArticleImage.class, metadata);
    }

}

