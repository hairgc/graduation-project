package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.UserFound;
import com.github.mahui53541.graduation.service.UserFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/8 17:07
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/found")
public class UserFoundController {
    @Autowired
    private UserFoundService userFoundService;

    @PostMapping(value = "")
    public Object add(@RequestBody UserFound userFound){
        return userFoundService.insert(userFound);
    }

}
