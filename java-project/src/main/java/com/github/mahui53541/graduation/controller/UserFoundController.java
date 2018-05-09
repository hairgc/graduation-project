package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.UserFound;
import com.github.mahui53541.graduation.service.UserFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return ResponseEntity.ok(userFoundService.insert(userFound));
    }

    /**
     * 标为已读
     * @param messageId
     * @param type
     * @return
     */
    @GetMapping(value = "/markmessage/{messageId}/{type}")
    @PreAuthorize("hasRole('USER')")
    public Object markMessage(@PathVariable("messageId") int messageId,
                             @PathVariable("type") int type){
        return ResponseEntity.ok(userFoundService.markMessage(messageId,type));
    }

    /**
     * 获取未读信息
     * @param userId
     * @return
     */
    @GetMapping(value = "/countmessage/{userId}")
    @PreAuthorize("hasRole('USER')")
    public Object countMessage(@PathVariable("userId") int userId){
        return ResponseEntity.ok(userFoundService.getMessage(false,userId).size());
    }

    /**
     * 获取消息
     * @param userId
     * @param isRead
     * @return
     */
    @GetMapping(value = "/message/{userId}/{isRead}")
    @PreAuthorize("hasRole('USER')")
    public Object getMessage(@PathVariable("userId") int userId,
                             @PathVariable("isRead") int isRead){
        return ResponseEntity.ok(userFoundService.getMessage(isRead!=0,userId));
    }
}
