package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.FoundMapper;
import com.github.mahui53541.graduation.model.Found;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:05
 * @Version: 1.0
 */
@Service("foundService")
public class FoundService extends BaseService<FoundMapper,Found> {
    /**
     * 查询失物招领
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Found> queryByPage(Integer pageNum, Integer pageSize){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<Found> p=new PageInfo(foundMapper.queryByPage(rowBounds));
        return p;
    }
}
