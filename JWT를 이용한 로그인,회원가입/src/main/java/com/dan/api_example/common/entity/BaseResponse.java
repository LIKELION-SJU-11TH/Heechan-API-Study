package com.dan.api_example.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    //null인 객체는 직렬화시 문자열로 변환 x
    private T result;

    // 요청에 성공한 경우 (1번 생성자)
    public BaseResponse(T result) {
        this.isSuccess = true;
        this.message = "요청에 성공하였습니다.";
        this.code = 200;
        this.result = result;
    }

    // 요청에 실패한 경우 (2번 생성자)
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }



}
