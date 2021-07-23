package org.zerock.ex2.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.zerock.ex2.entity.User;

import net.bytebuddy.asm.Advice.Return;

//security가 login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인 진행이 완료되면  시큐리티 session을 만들어준다(/security contextholder에서 세션정보를저장) 
//오브젝트 >> authentication 타입객체 
//authentication 안에 user정보가 있어야한다
// user 오브젝트 타입 >> userdetailes 타입객체 
//security session > authentication > userDetails(principaldetails)

public class PrincipalDetails implements UserDetails{

    private User user;//콤포지션
    public PrincipalDetails(User user){
        this.user= user;
    }

    //해당 user의 권한을 리턴하는곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //user.getRole(); string이라서 ㄴ
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority(){
            @Override
            public String getAuthority() {
                
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        //계정 만료, 아니오 true
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정잠금
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 1년? 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ex 사이트에서 회원 휴먼계정
        return true;
    }
    
}
