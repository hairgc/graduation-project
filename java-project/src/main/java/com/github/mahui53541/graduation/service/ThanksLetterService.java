package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.ThanksLetterMapper;
import com.github.mahui53541.graduation.model.ThanksLetter;
import com.github.mahui53541.graduation.vo.ThanksLetterVO;
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
     * 获取详情
     * @param id
     * @return
     */
    public ThanksLetterVO getById(int id){
        return thanksLetterMapper.getById(id);
    }
}
