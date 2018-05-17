package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.service.ThanksLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 发布
     * @param thanksLetter
     * @return
     */
    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public Object add(@RequestBody ThanksLetter thanksLetter){
        return ResponseEntity.ok(thanksLetterService.insert(thanksLetter));
    }

    /**
     * 获取前50条
     * @return
     */
    @GetMapping(value = "")
    public Object get(){
        return ResponseEntity.ok(thanksLetterService.query());
    }

    /**
     * 平台查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/admin/{pageNum}/{pageSize}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object findAllByPage(@PathVariable("pageNum") int pageNum,
                                @PathVariable("pageSize") int pageSize){
        return thanksLetterService.queryAllByPage(pageNum,pageSize);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Object getDetail(@PathVariable("id") int id){
        return ResponseEntity.ok(thanksLetterService.getById(id));
    }

    /**
     * 删除或恢复
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object deleteOrRecovery(@PathVariable("id") int id){
        ThanksLetter letter=thanksLetterService.selectByPrimaryKey(id);
        letter.setDeleted(!letter.getDeleted());
        return ResponseEntity.ok(thanksLetterService.updateByPrimaryKey(letter));
    }
}
