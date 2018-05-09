package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.UserFound;
import com.github.mahui53541.graduation.vo.MessageVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserFoundMapper extends Mapper<UserFound> {
    List<MessageVO> getMessage(@Param("isRead")Boolean isRead,
                               @Param("userId")Integer userId);
}