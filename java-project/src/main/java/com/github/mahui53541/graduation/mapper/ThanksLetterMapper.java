package com.github.mahui53541.graduation.mapper;

import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.vo.ThanksLetterVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ThanksLetterMapper extends Mapper<ThanksLetter> {
    /**
     * 查询最近50条
     * @return
     */
    List<ThanksLetter> query();

    /**
     * 平台查询
     * @param rowBounds
     * @return
     */
    List<ThanksLetterVO> queryAllByPage(@Param("rowBounds") RowBounds rowBounds);

    /**
     * 获取详情
     * @param id
     * @return
     */
    ThanksLetterVO getById(@Param("id")int id);
}