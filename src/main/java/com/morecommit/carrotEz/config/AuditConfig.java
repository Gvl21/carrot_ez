package com.morecommit.carrotEz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing   // JPA Auditing 기능 활성화
public class AuditConfig {


    // 등록자와 수정자를 처리해주는 AuditorAware를 스프링 빈으로 등록
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
