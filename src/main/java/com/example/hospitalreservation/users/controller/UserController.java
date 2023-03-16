package com.example.hospitalreservation.users.controller;

import com.example.hospitalreservation.users.dto.UserDto;
import com.example.hospitalreservation.users.dto.request.SignUpDto;
import com.example.hospitalreservation.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("user/signup")
    public String signupForm(){

        return "user/signupForm";
    }
    @PostMapping("/user/signup")
    public String signupForm(SignUpDto signUpDto){
        userService.createUser(signUpDto);

        return "redirect:/";
    }
}
