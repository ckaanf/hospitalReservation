package com.example.hospitalreservation.users.controller;

import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("/user/signup")
    public String signupForm(){

        return "user/signupForm";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "user/loginForm";
    }

    @PostMapping("/user/login")
    public String login(){
        return "index";
    }

    @GetMapping("user/loginForm")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/loginForm";
    }

    @PostMapping("/user/signup")
    public String signupForm(SignUpDto signUpDto){
        userService.createUser(signUpDto);
        System.out.println("회원 가입이 완료 되었습니다.");
        return "/user/loginForm";
    }
}
