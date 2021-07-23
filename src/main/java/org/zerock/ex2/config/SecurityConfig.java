package org.zerock.ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//spring security 필터가 spring 필터체인에 등록된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean//해당메서드의 리턴되는 오브젝트를ioc로 등록해줌
    public BCryptPasswordEncoder encodePwd(){


        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
        .antMatchers("/user/**").authenticated()
        .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginPage("/loginForm")
        .loginProcessingUrl("/login")//login주소가 호출되면 security가 대신 로그인 진행해줌 controller에 만들지 않음
        .defaultSuccessUrl("/");
    }
    
}
