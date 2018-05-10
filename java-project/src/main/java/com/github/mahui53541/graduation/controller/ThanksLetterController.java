package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.service.ThanksLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:13
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/thanks/letter")
public class ThanksLetterController {
    @Autowired
    private ThanksLetterService thanksLetterService;

    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public Object add(@RequestBody ThanksLetter thanksLetter){
        return ResponseEntity.ok(thanksLetterService.insert(thanksLetter));
    }
}
