package com.dan.api_example.service;

import com.dan.api_example.common.entity.BaseResponseStatus;
import com.dan.api_example.entity.User;
import com.dan.api_example.exception.BaseException;
import com.dan.api_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    //이메일로 유저찾기

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new BaseException(BaseResponseStatus.NON_EXIST_USER));
    }
}
