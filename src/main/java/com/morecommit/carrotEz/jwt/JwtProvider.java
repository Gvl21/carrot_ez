package com.morecommit.carrotEz.jwt;

import com.morecommit.carrotEz.constant.Role;
import com.morecommit.carrotEz.entity.Member;
import com.morecommit.carrotEz.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final MemberRepository memberRepository;
    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String create(String email) {
        // 사용자의 역할 정보 조회 (예시: 회원 정보에서 Role 가져오기)
        Member member = memberRepository.findByEmail(email);
        List<Role> roles = Collections.singletonList(member.getRole());

        // Role을 SimpleGrantedAuthority로 변환하여 authorities 리스트에 추가
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())) // "ROLE_" 추가
                .toList();

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        // 인가 범위 문자열 반환 : "ROLE_ADMIN ROLE_USER"
        String scope = authorities // 인가범위 => 콜렉션
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .claim("auth", scope)
                .compact();
        return jwt;
    }
    public String validate(String jwt){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}