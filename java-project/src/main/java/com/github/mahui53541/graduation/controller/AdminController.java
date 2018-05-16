package com.github.mahui53541.graduation.controller;

import com.github.mahui53541.graduation.model.Admin;
import com.github.mahui53541.graduation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:11
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 查询管理云信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/{pageNum}/{pageSize}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object findByPage(@PathVariable("pageNum") int pageNum,
                             @PathVariable("pageSize") int pageSize,
                             @RequestParam(name = "keyword",required = false) String keyword){
        return adminService.queryByPage(pageNum,pageSize,keyword);
    }

    /**
     * 删除或恢复管理员
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object deleteOrRecovery(@PathVariable("id") int id){
        Admin admin=adminService.selectByPrimaryKey(id);
        admin.setDeleted(!admin.getDeleted());
        return ResponseEntity.ok(adminService.updateByPrimaryKey(admin));
    }

    /**
     * 新增管理员
     * @return
     */
    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public Object save(@RequestBody Admin admin){
        return ResponseEntity.ok(adminService.addAdmin(admin));
    }

    /**
     * 学号是否存在
     * @param username
     * @return
     */
    @GetMapping(value = "/isExist/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object isExist(@PathVariable("username")String username){
        Admin admin=new Admin();
        admin.setUsername(username);
        return ResponseEntity.ok(adminService.selectOne(admin)!=null);
    }
}
