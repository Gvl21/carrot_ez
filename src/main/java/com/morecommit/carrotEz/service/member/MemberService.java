package com.morecommit.carrotEz.service.member;

import com.morecommit.carrotEz.dto.response.member.GetMemberDetailDto;
import com.morecommit.carrotEz.dto.response.member.GetMemberResponseDto;
import com.morecommit.carrotEz.dto.request.member.MemberSignInRequestDto;
import com.morecommit.carrotEz.dto.response.member.MemberSignInResponseDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.entity.Article;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.jwt.JwtProvider;
import com.morecommit.carrotEz.repository.ArticleRepository;
import com.morecommit.carrotEz.repository.MemberRepository;
import com.morecommit.carrotEz.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final JwtProvider jwtProvider;
    private final FileService fileService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    public Member saveMember(Member member, MultipartFile file) {
        validateDuplicateMember(member);

        // 이미지 파일 저장 로직
        if(file != null){
            String fileName = saveImage(file);
            member.setMemberImageUrl(fileName);
        }

        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("가입된 회원입니다.");
        }
    }

    // 파일서비스의 의존성을 주입받아 파일 업로드시 받는 url을 반환 + 파일 업로드
    private String saveImage(MultipartFile file){
        return fileService.upload(file);
        }

   // 로그인 로직
    public ResponseEntity<? super MemberSignInResponseDto> signIn(MemberSignInRequestDto dto){
        String token = null;
        try{
            String email = dto.getEmail();
            Member member = memberRepository.findByEmail(email);
            if (member == null) return MemberSignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = member.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return MemberSignInResponseDto.signInFailed();

            token = jwtProvider.create(email);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sign-in 서비스 에러");
        }
        return MemberSignInResponseDto.success(token);
    }

    // 멤버 정보 받아오기
    public ResponseEntity<? super GetMemberResponseDto> getSignInMember(String email){
        Member member = null;
        try{
            member = memberRepository.findByEmail(email);
            if (member == null) return GetMemberResponseDto.notExistUser();

        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMemberResponseDto.success(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new UsernameNotFoundException(email + "은 없는 email입니다.");
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    public ResponseEntity<? super GetMemberDetailDto> getMemberDetail(String email) {
        try{
            Member member = memberRepository.findByEmail(email);
            if (member == null) return GetMemberDetailDto.notExistUser();
            List<Article> createdArticleList = articleRepository.findTop6ByCreatedByOrderByRegTimeDesc(member.getEmail());
            return GetMemberDetailDto.success(member, createdArticleList);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

}
