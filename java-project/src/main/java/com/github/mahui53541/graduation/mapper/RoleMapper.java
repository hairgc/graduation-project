package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    /**
     * 获取管理员角色
     * @param adminId
     * @return
     */
    List<Role> getByAdminId(@Param("adminId") Integer adminId);

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    List<Role> getByUserId(@Param("userId") Integer userId);
}