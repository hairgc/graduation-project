package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.Found;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FoundMapper extends Mapper<Found> {
    List<Found> queryByPage(RowBounds rowBounds);
}