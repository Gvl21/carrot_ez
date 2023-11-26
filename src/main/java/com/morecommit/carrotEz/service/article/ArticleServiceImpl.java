package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.ArticleImage;
import com.morecommit.carrotEz.repository.ArticleImageRepository;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ArticleImageRepository articleImageRepository;

    @Override
    public ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email) {
        try {
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (!existedEmail) return ArticleResponseDto.notExistUser();

            Article article = dto.createArticle();

            articleRepository.save(article);

            Long articleId = article.getId();
            List<String> articleImageList = dto.getArticleImageList();
            List<ArticleImage> articleImages = new ArrayList<>();
            for (String image : articleImageList) {
                ArticleImage articleImage = new ArticleImage(articleId, image);
                articleImages.add(articleImage);
            }
            articleImageRepository.saveAll(articleImages);
            

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ArticleResponseDto.success();
    }
}
