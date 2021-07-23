package org.zerock.ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity//spring security 필터가 spring 필터체인에 등록된다.
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//secured 어노테이션 활성화 ,preAuthorize 어노테이션 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean//해당메서드의 리턴되는 오브젝트를ioc로 등록해줌
    public BCryptPasswordEncoder encodePwd(){


        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
        .antMatchers("/user/**").authenticated()//인증만 되면 들어갈 수 있는 주소 
        .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll()
        .and()
        .formLogin()
        .loginPage("/loginForm")
        .loginProcessingUrl("/login")//login주소가 호출되면 security가 대신 로그인 진행해줌 controller에 만들지 않음
        .defaultSuccessUrl("/");//loginform으로 가서 로그인을 하면 /로 갈건데 특정 페이지로가서 로그인요청을 하게된다면 로그인 완료시 그 페이지로 이동시켜줌 
    }
    
}
