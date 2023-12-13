package com.morecommit.carrotEz.common;

import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticleList {

    private Long articleId;
    private String title;
    private String content;
    private String area;
    private String category;
    private String regTime;
    private String updateTime;
    private String createdBy;
    private String nickname;
    private String profileImage;
    private int replyCount;

    public ArticleList(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.area = article.getArea();
        this.category = article.getCategory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.regTime = article.getRegTime().format(formatter);
        this.updateTime = article.getUpdateTime().format(formatter);
        this.createdBy = article.getCreatedBy();
        this.replyCount = article.getReplyCount();
    }
    // 정적 메소드 추가
    public static List<ArticleList> getListWithMemberInfo(List<Article> articles, MemberRepository memberRepository) {
        List<ArticleList> list = new ArrayList<>();
        for (Article article : articles) {
            ArticleList articleList = new ArticleList(article);
            Member member = memberRepository.findByEmail(articleList.getCreatedBy());
            if (member != null) {
                articleList.setNickname(member.getNickname());
                articleList.setProfileImage(member.getMemberImageUrl());
            }
            list.add(articleList);
        }
        return list;
    }

}