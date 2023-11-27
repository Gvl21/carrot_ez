package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.ArticleImage;
import com.morecommit.carrotEz.repository.ArticleImageRepository;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import com.morecommit.carrotEz.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ArticleImageRepository articleImageRepository;
    private final FileService fileService;

    @Override
    public ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email, List<MultipartFile> file) {
        try {
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (!existedEmail) return ArticleResponseDto.notExistUser();

            Article article = dto.createArticle();

            List<ArticleImage> articleImages = new ArrayList<>();
            List<String> articleImageUrls = new ArrayList<>();
            for (MultipartFile image : file) {
                String uploadedImage  = fileService.upload(image);
                ArticleImage articleImage = new ArticleImage(uploadedImage);
                articleImages.add(articleImage);
                articleImageUrls.add(uploadedImage);
            }
            article.setArticleImageList(articleImageUrls);
            articleRepository.save(article);
            Long articleId = article.getId();
            for (ArticleImage articleImage : articleImages) {
                articleImage.setArticleId(articleId);
            }
            articleImageRepository.saveAll(articleImages);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ArticleResponseDto.success();
    }
}
