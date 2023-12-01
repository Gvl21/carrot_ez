package com.morecommit.carrotEz.service.article;

import com.morecommit.carrotEz.common.ArticleList;
import com.morecommit.carrotEz.common.ReplyListItem;
import com.morecommit.carrotEz.dto.request.article.ArticleReplyRequestDto;
import com.morecommit.carrotEz.dto.request.article.ArticleRequestDto;
import com.morecommit.carrotEz.dto.request.article.PatchArticleRequestDto;
import com.morecommit.carrotEz.dto.request.article.PostBoardRequestDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.dto.response.article.*;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.ArticleImage;
import com.morecommit.carrotEz.entity.ArticleReply;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.repository.ArticleImageRepository;
import com.morecommit.carrotEz.repository.ArticleReplyRepository;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import com.morecommit.carrotEz.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final ArticleImageRepository articleImageRepository;
    private final ArticleReplyRepository articleReplyRepository;
    private final FileService fileService;


    @Override
    public ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email, List<MultipartFile> file) {
        try {
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (!existedEmail) return ArticleResponseDto.notExistUser();

            Article article = ArticleRequestDto.createArticle(dto);

            List<ArticleImage> articleImages = new ArrayList<>();
            List<String> articleImageUrls = new ArrayList<>();

            for (MultipartFile image : file) {
                String uploadedImage = fileService.upload(image);
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

            return ArticleResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super ArticleResponseDto> saveArticle(ArticleRequestDto dto, String email) {
        try {
            boolean existedEmail = memberRepository.existsByEmail(email);
            if (!existedEmail) return ArticleResponseDto.notExistUser();
            Article article = ArticleRequestDto.createArticle(dto);
            articleRepository.save(article);
            return ArticleResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    /**
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
        try {
            List<Article> articles = articleRepository.findByOrderByRegTimeDesc();
            List<ArticleList> articleListWithMemberInfo = ArticleList.getListWithMemberInfo(articles, memberRepository);
            return GetArticleAllResponseDto.success(articleListWithMemberInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

    }
    @Override
    public ResponseEntity<? super GetArticleAllResponseDto> getArticleListToMain() {
        try {
            List<Article> articles = articleRepository.findTop9ByOrderByRegTimeDesc();
            List<ArticleList> articleListWithMemberInfo = ArticleList.getListWithMemberInfo(articles, memberRepository);
            return GetArticleAllResponseDto.success(articleListWithMemberInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PatchArticleResponseDto>patchArticleUpdate(PatchArticleRequestDto dto, Long articleId, String email, List<MultipartFile>file) {
        try{
            Member member = memberRepository.findByEmail(email);
            if (member == null) return PatchArticleResponseDto.notExistUser();

            Article article = articleRepository.findById(articleId).orElse(null);
            if (article == null) return PatchArticleResponseDto.notExistUser();

            boolean equalWriter = article.getCreatedBy().equals(email);
            if (!equalWriter) return PatchArticleResponseDto.noPermission();

            List<ArticleImage> articleImages = new ArrayList<>();
            List<String> articleImageUrls = dto.getImageUrls();

            for (MultipartFile image : file) {
                String uploadedImage = fileService.upload(image);
                ArticleImage articleImage = new ArticleImage(uploadedImage);
                articleImages.add(articleImage);
                articleImageUrls.add(uploadedImage);
            }
           article.setArticleImageList(articleImageUrls);
            articleImageRepository.deleteById(articleId);
            articleImageRepository.saveAll(articleImages);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchArticleResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetArticleResponseDto> getArticle(Long articleId) {
        Article article = null;
        Member member = new Member();
//        List<ArticleImageResponseDto> articleImageList = new ArrayList<>();
        List<ArticleImage> imageList = articleImageRepository.findByArticleId(articleId);
        try {
            article = articleRepository.findById(articleId).orElse(null);
            if (article == null) return GetArticleResponseDto.notExistBoard();
//            articleImageList = ArticleImageResponseDto.fromEntityList(imageList);
            member = memberRepository.findByEmail(article.getCreatedBy());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetArticleResponseDto.success(article, imageList, member);
    }

    @Override
    public ResponseEntity<? super ArticleReplyResponseDto> saveReply(ArticleReplyRequestDto dto, Long articleId, String email) {
        try {
            Article article = articleRepository.findById(articleId).orElse(null);
            if (article == null) return ArticleReplyResponseDto.notExistBoard();

            Member member = memberRepository.findByEmail(email);
            if (member == null) return ArticleReplyResponseDto.notExistUser();

            ArticleReply articleReply = new ArticleReply(dto, articleId);
            articleReplyRepository.save(articleReply);

            article.increaseReplyCount();
            articleRepository.save(article);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ArticleReplyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetArticleReplyResponseDto> getArticleReply(Long articleId) {
        List<ReplyListItem> replyList = new ArrayList<>();

        try {
            Article article = articleRepository.findById(articleId).orElse(null);
            if (article == null) return GetArticleReplyResponseDto.notExistBoard();

            List<ArticleReply> replies = articleReplyRepository.findByArticleIdOrderByRegTimeAsc(articleId);
            for (ArticleReply reply : replies) {
                ReplyListItem item = new ReplyListItem();
                item.setContent(reply.getContent());
                // 멤버 관련 정보 담기
                Member member = memberRepository.findByEmail(reply.getCreatedBy());
                item.setNickname(member.getNickname());
                item.setMemberImgUrl(member.getMemberImageUrl());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                item.setRegTime(reply.getRegTime().format(formatter));
                replyList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetArticleReplyResponseDto.success(replyList);
    }


}