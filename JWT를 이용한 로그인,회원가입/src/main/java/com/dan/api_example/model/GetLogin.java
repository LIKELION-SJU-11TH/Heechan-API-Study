package com.dan.api_example.model;

import lombok.Getter;

@Getter
public class GetLogin {
    private String email;
    private String password;

    public GetLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
