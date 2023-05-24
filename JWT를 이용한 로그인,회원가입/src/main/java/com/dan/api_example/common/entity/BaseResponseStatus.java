package com.dan.api_example.common.entity;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    //열거형으로 상수 선언 BaseResponseStatus.NO_AUTH로 작성하면
    //isSuccess, code, message에는 각각 false, 401, 권한이 없습니다로 필드값이 부여된다.
    SCCUESS(true, 200, "요청에 성공하였습니다"),
    NO_AUTH(false, 401, "권한이 없습니다."),
    DATABASE_ERROR(false, 500, "데이터베이스 에러가 발생했습니다."),
    NON_EXIST_USER(false,500,"유저가 존재하지 않습니다."),
    EMPTY_DB(false,500,"서버에 회원가입한 유저가 존재하지 않습니다."),
    ALREADY_EXIST(false,500,"이미 존재하는 이메일 입니다."),
    WRONG_PASSWORD(false,402,"비밀번호가 틀립니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
