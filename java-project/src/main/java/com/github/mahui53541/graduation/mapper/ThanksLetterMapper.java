package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.vo.ThanksLetterVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ThanksLetterMapper extends Mapper<ThanksLetter> {
    /**
     * 查询最近50条
     * @return
     */
    List<ThanksLetter> query();

    /**
     * 获取详情
     * @param id
     * @return
     */
    ThanksLetterVO getById(@Param("id")int id);
}