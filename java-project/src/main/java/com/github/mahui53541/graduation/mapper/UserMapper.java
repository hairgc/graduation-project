package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    /**
     * 分页查询
     * @param rowBounds
     * @return
     */
    List<User> queryByPage(@Param("keyword") String keyword, RowBounds rowBounds);
}