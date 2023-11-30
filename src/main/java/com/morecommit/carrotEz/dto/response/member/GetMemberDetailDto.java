package com.morecommit.carrotEz.dto.response.member;

import com.morecommit.carrotEz.common.ResponseCode;
import com.morecommit.carrotEz.common.ResponseMessage;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.Member;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMemberDetailDto extends ResponseDto {
    private final String email;
    private final String nickname;
    private final String area;
    private final String category;
    private final String memberImageUrl;
    private final List<Article> createdArticleList;

    public GetMemberDetailDto(String code, String message, Member member, List<Article> createdArticleList) {
        super(code, message);
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.area = member.getArea();
        this.category = member.getCategory();
        this.memberImageUrl = member.getMemberImageUrl();
        this.createdArticleList = createdArticleList;

    }
    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<? super GetMemberDetailDto> success(Member member, List<Article> createdArticleList) {
        GetMemberDetailDto result = new GetMemberDetailDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, member, createdArticleList);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
