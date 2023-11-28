package com.morecommit.carrotEz.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ArticleReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private Long articleId;

    //      포함을 해야하나 말아야하나
//    private String nickname;

    private String content;
}
