package com.example.hospitalreservation.users.controller;

import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String indexRedirecting(){

        return "redirect:reservation";
    }


    @GetMapping("/user/signup")
    public String signupForm(){

        return "user/signupForm";
    }
    @PostMapping("/user/signup")
    public String signupForm(SignUpDto signUpDto){

        userService.createUser(signUpDto);
        System.out.println(signUpDto);
        System.out.println("회원 가입이 완료 되었습니다.");

        return "/user/loginForm";
    }

    @GetMapping("/user/signin")
    public String loginForm(){

        return "user/loginForm";
    }


//    @PostMapping("/user/signup")
//    public ResponseEntity signUp(
//            @RequestBody SignUpDto signUpDto){
//        UserDto userDto = userService.createUser(signUpDto);
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(userDto), HttpStatus.OK);
//    }
}
