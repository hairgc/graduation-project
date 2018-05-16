package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.AdminMapper;
import com.github.mahui53541.graduation.model.Admin;
import com.github.mahui53541.graduation.model.AdminRole;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:03
 * @Version: 1.0
 */
@Service("adminService")
public class AdminService extends BaseService<AdminMapper,Admin>{
    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Admin> queryByPage(Integer pageNum, Integer pageSize, String keyword){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<Admin> p=new PageInfo(adminMapper.queryByPage(keyword,rowBounds));
        return p;
    }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    public int addAdmin(Admin admin){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(admin.getPassword()));
        admin.setDeleted(false);
        adminMapper.insert(admin);
        AdminRole adminRole=new AdminRole();
        adminRole.setAdminId(admin.getId());
        adminRole.setRoleId(2);
        return adminRoleMapper.insert(adminRole);
    }
}
