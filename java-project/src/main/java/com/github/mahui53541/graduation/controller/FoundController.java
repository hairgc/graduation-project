package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.service.FoundService;
import com.github.mahui53541.graduation.vo.FoundUserVO;
import com.github.mahui53541.graduation.vo.FoundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:12
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/found")
public class FoundController {
    @Autowired
    private FoundService foundService;

    /**
     * 发布
     * @return
     * @RequestParam(name = "file",required = false) MultipartFile file,
     */
    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER')")
    public Object postFound(@RequestBody FoundVO foundVO){
        return ResponseEntity.ok(foundService.postFound(foundVO));
    }
    /**
     * 查询失物招领信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/{pageNum}/{pageSize}")
    public Object findByPage(@PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize,
                             @RequestParam(name = "keyword",required = false) String keyword,
                             @RequestParam(name = "startDate",required = false)Date startDate,
                             @RequestParam(name = "endDate",required = false)Date endDate){
        return foundService.queryByPage(pageNum,pageSize,keyword,startDate,endDate);
    }

    /**
     * 查询失物招领详情信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public FoundUserVO getFoundDetail(@PathVariable("id") int id){
        return foundService.getFoundDetail(id);
    }
}
