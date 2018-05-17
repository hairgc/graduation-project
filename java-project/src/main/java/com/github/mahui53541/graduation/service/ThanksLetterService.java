package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.ThanksLetterMapper;
import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.vo.ThanksLetterVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:07
 * @Version: 1.0
 */
@Service("thanksLetterService")
public class ThanksLetterService extends BaseService<ThanksLetterMapper,ThanksLetter> {
    /**
     * 查询最近50条
     * @return
     */
    public List<ThanksLetter> query(){
        return thanksLetterMapper.query();
    }

    /**
     * 平台查询感谢信
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<ThanksLetterVO> queryAllByPage(Integer pageNum, Integer pageSize){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<ThanksLetterVO> p=new PageInfo(thanksLetterMapper.queryAllByPage(rowBounds));
        return p;
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    public ThanksLetterVO getById(int id){
        return thanksLetterMapper.getById(id);
    }
}
