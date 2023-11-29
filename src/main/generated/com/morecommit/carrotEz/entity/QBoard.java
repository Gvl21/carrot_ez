package com.morecommit.carrotEz.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1641488196L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Integer> boardNumber = createNumber("boardNumber", Integer.class);

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> favoriteCount = createNumber("favoriteCount", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewContent = createNumber("viewContent", Integer.class);

    public final StringPath writeDatetime = createString("writeDatetime");

    public final StringPath writerEmail = createString("writerEmail");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

