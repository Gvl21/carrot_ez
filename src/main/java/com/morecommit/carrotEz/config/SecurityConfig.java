
package com.morecommit.carrotEz.config;

import com.morecommit.carrotEz.jwt.JwtAuthenticationFilter;
import com.morecommit.carrotEz.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity  // 웹 보안
@Configuration      // 설정 정보 컴포넌트 등록 선언
@RequiredArgsConstructor
public class SecurityConfig {


    // jwt
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // ?????????????????????????????
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((session) ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(antMatcher("/")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.POST,"/members/new")).permitAll()
                                .requestMatchers(antMatcher(HttpMethod.POST,"/members/signIn")).permitAll()
                                // 프로필 사진관련 인가는 딱히 필요없이 모든 사람이 볼 수 있어야 되니 허용하기
                                .requestMatchers(antMatcher(HttpMethod.GET,"/file/**")).permitAll()
                                // 축제정보 얻기 모두 허용
                                .requestMatchers(antMatcher(HttpMethod.GET,"/fstvl")).permitAll()
                                // 게시글 목록만 아무나 볼 수 있도록 하기
                                .requestMatchers(antMatcher(HttpMethod.GET,"/article/list")).permitAll()
                                .anyRequest().authenticated());
        // 인증되지 않은 사용자가 들어왔을 때 예외처리
        // => 로그인 페이지 리다이렉트(302)
        // => 상태코드 UNAUTHORIZED 401
        http
                .exceptionHandling(exception ->
                exception.authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException)
                            throws IOException, ServletException {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                })
        );
        http.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // 해시 함수를 이용한 비밀번호 암호화


//    class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{
//        @Override
//        public void commence(HttpServletRequest request, HttpServletResponse response,
//                             AuthenticationException authException) throws IOException, ServletException {
//            response.setContentType("application/json");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("{\"code\" : \"AF\", \"message\" : \"Authorization Failed.\"}");
//        }
//    }

}

