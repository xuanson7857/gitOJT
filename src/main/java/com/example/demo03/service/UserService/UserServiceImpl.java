package com.example.demo03.service.UserService;

import com.example.demo03.model.dto.request.SignInRequest;
import com.example.demo03.model.entity.User;
import com.example.demo03.responsitoty.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{
 @Autowired
 UserRepository userRepository;
 @Autowired
    HttpSession httpSession;
    @Override
    public boolean signIn(SignInRequest signInRequest) {
       User user = userRepository.findByUsername(signInRequest.getUsername()).orElse(null);
       if (user != null){
           if (user.getPassword().equals(signInRequest.getPassword())){
               System.out.println("dsdsd");
               httpSession.setAttribute("userLogin", user);
               return true;
           }
       }
       return false;
    }
}
