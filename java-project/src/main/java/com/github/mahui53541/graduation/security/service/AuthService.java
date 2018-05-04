package com.github.mahui53541.graduation.security.service;

import com.github.mahui53541.graduation.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * java类简单作用描述
 *
 * @Description: 登录验证
 * @Author: MaHui
 * @CreateDate: 2018/5/4 15:44
 * @Version: 1.0
 */
@Service("authService")
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public HashMap<String,Object> login(String username, String password,Integer type) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username+"#"+type, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String,Object> data=new HashMap<>();
        data.put("token","Bearer "+token);
        data.put("user",userDetails);
        return data;
    }
}
