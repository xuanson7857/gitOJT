package com.example.demo03.service.UserService;

import com.example.demo03.model.dto.request.SignInRequest;

public interface UserService {
    boolean signIn (SignInRequest signInRequest);
}
