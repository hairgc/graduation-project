package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.UserLost;
import com.github.mahui53541.graduation.service.UserLostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/13 13:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/lost")
public class UserLostController {
    @Autowired
    private UserLostService userLostService;

    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public Object add(@RequestBody UserLost userLost){
        return ResponseEntity.ok(userLostService.insert(userLost));
    }

}
