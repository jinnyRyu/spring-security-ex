package org.zerock.ex2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import org.zerock.ex2.entity.User;
import org.zerock.ex2.repository.UserRepository;

@Controller
public class IndexController {
    

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({ "", "/" })//security를 적용하면 프로젝트로 들어오는 모든 주소는 인증을 거치도록 자동 설정한다. 
    public @ResponseBody String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){

        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String mamager(){
        return "manager";
    }

    //
    @GetMapping("/loginForm")
    public  String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public  String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public @ResponseBody RedirectView join(User user){
        user.setRole("ROLE_USER");
        //security로그인 불가능 pw암호화가 되어있지 않아서,userRepository.save(user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);

        return new RedirectView("/loginForm");//themleaf reidrect 사용함수 
    }

    @Secured("ROLE_ADMIN")//EnableGlobalMethodSecurity 어노테이션 활성화 시켜야 사용가능
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "personal info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")// data()가 실행되기 직전에 실행
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "data info";
    }

}
