package com.study.oauth.oauth.config;


import com.study.oauth.oauth.service.CustomOAuth2AuthorizedClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.headers( httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login", "/css/**", "/", "/img/**", "/download/**", "/static/**", "/h2-console/**").permitAll()
                        .requestMatchers("/oauth2/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                                .loginPage("/login")
                                .defaultSuccessUrl("/main")
                                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                        .userService(customOAuth2AuthorizedClientService)
                                )
                                .failureUrl("/login?error=true")
                                .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")));

        return http.build();
    }


    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> authorities;
    }

}
