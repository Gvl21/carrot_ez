package com.morecommit.carrotEz.service;

import com.morecommit.carrotEz.dto.member.GetMemberResponseDto;
import com.morecommit.carrotEz.dto.member.MemberSignInRequestDto;
import com.morecommit.carrotEz.dto.member.MemberSignInResponseDto;
import com.morecommit.carrotEz.dto.response.ResponseDto;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.jwt.JwtProvider;
import com.morecommit.carrotEz.repository.MemberRepository;
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


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new IllegalStateException("가입된 회원입니다.");
        }
    }

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


//    public TokenDto signin(MemberSignInDto memberSignInDto) {
//        UsernamePasswordAuthenticationToken authenticationToken = memberSignInDto.toAuthentication();
//
//        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
//
//        return tokenProvider.generateTokenDto(authentication);
//    }
}
