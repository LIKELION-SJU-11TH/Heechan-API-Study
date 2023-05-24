package com.dan.api_example.exception;

import com.dan.api_example.common.entity.BaseResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 일반적인 예외 처리와는 달리 예외가 발생한 상태를 명시적으로 표현
// 하기 위해서 사용자 정의 예외 클래스읜 BaseException이 필요

public class BaseException extends RuntimeException {
    private BaseResponseStatus status;


    public BaseException(BaseResponseStatus status) {
        super(status.getMessage()); // ? 왜있음?
        this.status = status;//예외를 설정하는 과정
    }
}
