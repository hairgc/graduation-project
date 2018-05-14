package com.github.mahui53541.graduation.security.service;

import com.github.mahui53541.graduation.mapper.UserMapper;
import com.github.mahui53541.graduation.mapper.UserRoleMapper;
import com.github.mahui53541.graduation.model.Role;
import com.github.mahui53541.graduation.model.User;
import com.github.mahui53541.graduation.model.UserRole;
import com.github.mahui53541.graduation.security.jwt.JwtTokenUtil;
import com.github.mahui53541.graduation.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private JwtUserFactory jwtUserFactory;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    /**
     * 登录
     * @param username
     * @param password
     * @param type
     * @return
     */
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

    /**
     * 注册
     * @return
     */
    public HashMap<String,Object> register(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setDeleted(false);
        user.setRegisterTime(new Date());
        userMapper.insert(user);
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(1);
        userRoleMapper.insert(userRole);
        final UserDetails userDetails = jwtUserFactory.create(user);
        final String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String,Object> data=new HashMap<>();
        data.put("token","Bearer "+token);
        data.put("user",userDetails);
        return data;
    }
}
