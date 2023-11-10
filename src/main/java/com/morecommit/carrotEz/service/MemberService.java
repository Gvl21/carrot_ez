package com.morecommit.carrotEz.service;

import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.repository.MemberRepository;
        import lombok.RequiredArgsConstructor;
//        import org.springframework.security.core.userdetails.User;
//        import org.springframework.security.core.userdetails.UserDetails;
//        import org.springframework.security.core.userdetails.UserDetailsService;
//        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService/* implements UserDetailsService */{
    private MemberRepository memberRepository;

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
/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(email);

        if (member == null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder().username(member.getEmail())
                .password(member.getPassword()).roles(member.getRole().toString())
                .build();


    }
*/



}
