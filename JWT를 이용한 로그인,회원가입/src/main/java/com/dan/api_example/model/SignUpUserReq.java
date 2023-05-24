package com.dan.api_example.model;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpUserReq {


    @NotBlank(message = "이름을 입력해주세요")
    private String name;


    private int age;

    @Email
    private String email;

    @NotBlank(message="비밀번호는 필수 입력 값입니다.")
    private String password;
}
