package com.github.mahui53541.graduation.controller;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/4/29 20:06
 * @Version: 1.0
 */

import com.github.mahui53541.graduation.model.User;
import com.github.mahui53541.graduation.security.service.AuthService;
import com.github.mahui53541.graduation.service.UserService;
import com.github.mahui53541.graduation.vo.UserPwdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public Object updateUser(@RequestBody User user){
        user.setUsername(null);
        user.setPassword(null);
        user.setDeleted(null);
        return ResponseEntity.ok(userService.updateByPrimaryKeySelective(user));
    }

    @PutMapping(value = "/changePwd")
    @PreAuthorize("hasRole('USER')")
    public Object changePwd(@RequestBody UserPwdVO userPwdVO){
        return ResponseEntity.ok(userService.updatePwd(userPwdVO));
    }

    @GetMapping(value = "/isExist/{username}")
    public Object isExist(@PathVariable("username")String username){
        User user=new User();
        user.setUsername(username);
        return ResponseEntity.ok(userService.selectOne(user)!=null);
    }
}
