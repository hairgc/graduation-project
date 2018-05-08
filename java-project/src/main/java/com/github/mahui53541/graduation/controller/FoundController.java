package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.service.FoundService;
import com.github.mahui53541.graduation.vo.FoundUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询失物招领信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/{pageNum}/{pageSize}")
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return foundService.queryByPage(pageNum,pageSize);
    }

    /**
     * 查询失物招领详情信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}")
    public FoundUserVO getFoundDetail(@PathVariable("id") int id){
        return foundService.getFoundDetail(id);
    }
}
