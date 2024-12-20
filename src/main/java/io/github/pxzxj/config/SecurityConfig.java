package io.github.pxzxj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin().and().
                httpBasic().and().
                authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .headers().frameOptions().sameOrigin().and()
                .build();
    }
}
