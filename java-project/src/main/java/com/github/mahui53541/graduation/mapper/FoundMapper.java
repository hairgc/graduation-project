package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.Found;
import com.github.mahui53541.graduation.vo.FoundAdminVO;
import com.github.mahui53541.graduation.vo.FoundUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface FoundMapper extends Mapper<Found> {
    List<Found> queryByPage(@Param("keyword") String keyword,
                            @Param("startDate")Date startDate,
                            @Param("endDate")Date endDate,
                            @Param("rowBounds") RowBounds rowBounds);

    List<FoundAdminVO> queryAllByPage(@Param("keyword") String keyword,
                                      @Param("startDate")Date startDate,
                                      @Param("endDate")Date endDate,
                                      @Param("rowBounds") RowBounds rowBounds);

    FoundUserVO getFoundDetail(@Param("id")Integer id);
}