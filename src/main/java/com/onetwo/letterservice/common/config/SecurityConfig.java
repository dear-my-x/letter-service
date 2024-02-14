package com.onetwo.letterservice.common.config;

import onetwo.mailboxcommonconfig.common.RequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public RequestMatcher requestMatcher() {
        return new RequestMatcher() {
            @Override
            public List<MvcRequestMatcher> getMvcRequestMatcherArray() {
                return List.of();
            }
        };
    }
}
