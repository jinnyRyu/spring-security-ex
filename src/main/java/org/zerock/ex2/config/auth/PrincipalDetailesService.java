package org.zerock.ex2.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.ex2.entity.User;
import org.zerock.ex2.repository.UserRepository;


//security 설정에서 loginProcessingUrl("/login")
//login 요청이오면 자동으로 UserDetailsService 타임으로 ioc 되어있는 loadUserByUsername 함수가 실행
//그냥 규칙인듯
@Service
public class PrincipalDetailesService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    //security session (authentication(내부에 userdetaiels))= authentication(내부에 userdetaiels) = userdetailes
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username    "+username);
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
