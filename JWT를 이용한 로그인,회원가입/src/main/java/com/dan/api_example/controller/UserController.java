package com.dan.api_example.controller;

import com.dan.api_example.common.entity.BaseResponse;
import com.dan.api_example.common.entity.BaseResponseStatus;
import com.dan.api_example.exception.BaseException;
import com.dan.api_example.model.GetLogin;
import com.dan.api_example.model.GetUserRes;
import com.dan.api_example.model.SignUpUserReq;
import com.dan.api_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<String> checkUser(@RequestBody GetLogin getLogin){
        try{
            String token=userService.checkUser(getLogin.getEmail(), getLogin.getPassword());
            return new BaseResponse<>(token);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
    @PostMapping("/signup")

    public BaseResponse<String> createUser(@RequestBody @Valid SignUpUserReq signUpUserReq) {
        if(userService.checkEmail(signUpUserReq.getEmail())){
            return new BaseResponse<>(BaseResponseStatus.ALREADY_EXIST);
        }
        else{
            userService.createUser(signUpUserReq);
            return new BaseResponse<>(BaseResponseStatus.SCCUESS);
        }
    }

    @GetMapping("/")
    public BaseResponse<List<GetUserRes>> getUser() {
        try{
            List<GetUserRes> getUserRes = userService.getUsers();
            return new BaseResponse<>(getUserRes);
            //리스트가 있다면 그값들로 반응을 보낸다.
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }

    @GetMapping("/read-one")
    public BaseResponse<GetUserRes> getUserOne(@RequestParam("id") int id){
        try{
            GetUserRes getUserRes=userService.getUser(id);
            return new BaseResponse<>(getUserRes);
            //유저가 잘 있으면 유저가 잘 있다는 상태메시지
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
            //유저가 없다면 유저가 없다는 상태메시지
        }
    }
    /**
     * 단일 유저 조회
     * 구현해 보세요
     *
     * @RequestParam으로 userId 받아와서 조회
     */


}
