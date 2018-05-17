package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.Lost;
import com.github.mahui53541.graduation.vo.LostAdminVO;
import com.github.mahui53541.graduation.vo.LostUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface LostMapper extends Mapper<Lost> {
    List<Lost> queryByPage(@Param("keyword") String keyword,
                            @Param("startDate")Date startDate,
                            @Param("endDate")Date endDate,
                            @Param("rowBounds") RowBounds rowBounds);

    List<LostAdminVO> queryAllByPage(@Param("keyword") String keyword,
                                     @Param("startDate")Date startDate,
                                     @Param("endDate")Date endDate,
                                     @Param("rowBounds") RowBounds rowBounds);
    LostUserVO getLostDetail(@Param("id")Integer id);
}