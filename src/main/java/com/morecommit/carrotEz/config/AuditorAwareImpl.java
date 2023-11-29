package com.morecommit.carrotEz.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

// Spring Data JPA에서 제공하는 Auditing 기능을 활용하기 위한 인터페이스 구현체
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // 스프링 시큐리티에서 인증정보를 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "UNIDENTIFIED USER";
        // 인증이 있으면 사용자 아이디를 등록자와 수정자로 지정
        if (authentication != null) {
            userId =  authentication.getName();
        }
        return Optional.of(userId);
    }
}

