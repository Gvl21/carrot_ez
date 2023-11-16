
package com.morecommit.carrotEz.config;

import com.morecommit.carrotEz.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.io.IOException;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity  // 웹 보안
@Configuration      // 설정 정보 컴포넌트 등록 선언
@RequiredArgsConstructor
public class SecurityConfig {

    // 스프링 컨테이너 의존성 주입
    private final MemberService memberService;

    // http 요청에 대한 보안 설정
    @Bean       // 스프링 컨테이너 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 로그인 관련 설정
        http.formLogin(
                form -> form
                        .loginPage("/members/login")  // 기본 로그인 페이지 URL을 설정
                        .defaultSuccessUrl("/")      // 로그인에 성공했을 때 URL
                        .usernameParameter("email")  // 로그인에 사용할 매개변수 username -> email
                        .failureUrl("/members/login/error") // 실패했을 때 보낼 URL
        );

        // 로그아웃 관련 설정
        http.logout(
                logout -> logout
                        .logoutRequestMatcher(
                                // Ant 패턴 경로 문법 -> 해당 URL 접속 시 로그아웃됨
                                antMatcher("/members/logout"))
                        // 로그아웃이 성공한 경우 메인 페이지로 리다이렉트
                        .logoutSuccessUrl("/")
        );

        // 인가 (authorize)
        http.authorizeHttpRequests(
                authorize -> authorize
                        // Ant 패턴 경로 요청에 대한 매칭 수행
                        // ** : 모든 파일 및 경로에 대해
                        // 루트 경로는 모두가 접근 가능
                        .requestMatchers(antMatcher("/")).permitAll()
                        // 정적 파일 css, js, image 등은 모두 접근 가능
                        .requestMatchers(antMatcher("/css/**")).permitAll()
                        // 로그인, 로그아웃, 회원가입 페이지는 모두 접근 가능
                        .requestMatchers(antMatcher("/members/**")).permitAll()
                        // 이미지 파일은 모두 접근 가능
                        .requestMatchers(antMatcher("/images/**")).permitAll()
                        // 상품 경로는 모두 접근 가능
                        .requestMatchers(antMatcher("/item/**")).permitAll()
                        // /admin/ 이후의 url은 ADMIN 역할만 접근 가능
                        .requestMatchers(antMatcher("/admin/**"))
                        .hasAnyRole("ADMIN")
                        // 그 외 모든 요청은 인증되어야 한다.
                        .anyRequest().permitAll()
                );

        // 인증되지 않은 사용자가 들어왔을 때 예외처리
        // => 로그인 페이지 리다이렉트(302)
        // => 상태코드 UNAUTHORIZED 401
        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException)
                            throws IOException, ServletException {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                })
                );



        // CSRF 토큰 검증 무효화
         http.csrf().disable();
        return http.build();
    }

    // 해시 함수를 이용한 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

