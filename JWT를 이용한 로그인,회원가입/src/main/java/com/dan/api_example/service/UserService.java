package com.dan.api_example.service;

import com.dan.api_example.common.entity.BaseResponseStatus;
import com.dan.api_example.token.JwtTokenProvider;
import com.dan.api_example.entity.User;
import com.dan.api_example.exception.BaseException;
import com.dan.api_example.model.GetUserRes;
import com.dan.api_example.model.SignUpUserReq;
import com.dan.api_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //의존성 주입
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public boolean checkEmail(String email){
        List<User> userList=userRepository.findAll();

        for(User user: userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public void createUser(SignUpUserReq signUpUserReq) {

        User new_user = User.builder()
                .age(signUpUserReq.getAge())
                .name(signUpUserReq.getName())
                .email(signUpUserReq.getEmail())
                .password(passwordEncoder.encode(signUpUserReq.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();


        userRepository.save(new_user);
    }

    /**
     * 유저 전체 조회
     */
    public List<GetUserRes> getUsers() throws BaseException{

        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            throw new BaseException(BaseResponseStatus.EMPTY_DB);
        }

        List<GetUserRes> userRes = new ArrayList<>();
        for (User user : userList) {
            GetUserRes getUserEntity = new GetUserRes(user);
            userRes.add(getUserEntity);
        }

        /*
        간단한 방법.
        List<GetUserRes> userRes = userList.stream().map(GetUserRes::new).collect(Collectors.toList());
        */

        return userRes;
    }


    //id로 유저 찾기
    public GetUserRes getUser(int id)throws BaseException{
        Optional<User> user=userRepository.findById((long)id);

        if(user.isEmpty()){
            throw new BaseException(BaseResponseStatus.NON_EXIST_USER);
        }

        GetUserRes getUserRes=new GetUserRes(
            user.get()
        );

        return getUserRes;
    }



    /* 1. 먼저 브라우저에서 Login요청을 한다.
       2. 서버에서 JWT를 발급한다.
       3. 발급한 JWT를 브라우저로 보낸다.
       4. 이후 요청시 발급받은 JWT를 함께 보낸다.
       5. 서버에서 JWT에 포함된 Signature를 확인 후 user정보를 request에 담아준다.
       6. 서버에서 브라우저의 요청을 처리한다.
       7. 브라우저로 response를 보낸다.
    *
    *
    *
    * */
    public String checkUser(String email, String password)throws BaseException{
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new BaseException(BaseResponseStatus.NON_EXIST_USER);
        }
        if(!passwordEncoder.matches(password,user.get().getPassword())){
            throw new BaseException(BaseResponseStatus.WRONG_PASSWORD);
        }
        return jwtTokenProvider.createToken(user.get().getUsername(),user.get().getRoles());
    }



}
