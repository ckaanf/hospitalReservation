package com.example.hospitalreservation.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignUpDto(
        @NotBlank(message = "아이디 입력은 필수입니다.")
        @Length(max=30, message = "아이디 길이는 30자를 넘으면 안됩니다.")
        String userId,
        @NotBlank(message = "이름 입력은 필수입니다.")
        @Length(max=30, message = "이름 길이는 30자를 넘으면 안됩니다.")
        String name,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=\\S+$).{8,20}$") //숫자, 알파벳, 특수문자(!@#$%^&*) 포함 8자 이상 20자 이하)
        String password
) {
        public static SignUpDto of(String userId, String name,String password){
                return new SignUpDto(userId, name, password);
        }

}
