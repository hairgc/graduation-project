package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.User;
import com.github.mahui53541.graduation.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 15:31
 * @Version: 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;
    /**
     * 用户登陆
     * @return
     * @throws AuthenticationException
     */
    @GetMapping (value = "/login")
    public ResponseEntity createAuthenticationToken(@RequestParam("username")String username,
                                                    @RequestParam("password")String password,
                                                    @RequestParam("type")Integer type) throws AuthenticationException {
        final HashMap<String,Object> data = authService.login(username, password,type);
        return ResponseEntity.ok(data);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public Object registerUser(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));
    }
}
