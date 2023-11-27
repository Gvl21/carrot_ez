package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.common.ArticleList;
import com.morecommit.carrotEz.dto.request.board.ArticleRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.board.ArticleResponseDto;
import com.morecommit.carrotEz.dto.response.board.GetArticleAllResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.ArticleImage;
import com.morecommit.carrotEz.entity.Member;
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

    /**
     *
     * 수정 전의 코드 (멤버 정보 주입전)
     */
//    @Override
//    public ResponseEntity<? super GetArticleAllResponseDto> getArticleList() {
//        List<Article> articleList = new ArrayList<>();
//        try{
//            articleList = articleRepository.findByOrderByRegTimeDesc();
//            for (Article article : articleList) {
//                // 일단 여기까진 잘 나옴
//                System.out.println(article.getTitle());
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//            return ResponseDto.databaseError();
//        }
//        return GetArticleAllResponseDto.success(articleList);
//    }

    @Override
    public ResponseEntity<? super GetArticleAllResponseDto> getArticleList() {
        try{
            List<Article> articles = articleRepository.findByOrderByRegTimeDesc();
            List<ArticleList> articleListWithMemberInfo = ArticleList.getListWithMemberInfo(articles, memberRepository);
            return GetArticleAllResponseDto.success(articleListWithMemberInfo);

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

    }



}
