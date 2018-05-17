package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.LostMapper;
import com.github.mahui53541.graduation.model.Lost;
import com.github.mahui53541.graduation.util.FileUploadUtil;
import com.github.mahui53541.graduation.vo.LostAdminVO;
import com.github.mahui53541.graduation.vo.LostUserVO;
import com.github.mahui53541.graduation.vo.LostVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:05
 * @Version: 1.0
 */
@Service("lostService")
public class LostService extends BaseService<LostMapper,Lost> {
    @Autowired
    private FileUploadUtil fileUploadUtil;
    /**
     * 查询失物招领
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Lost> queryByPage(Integer pageNum, Integer pageSize, String keyword, Date startDate, Date endDate){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<Lost> p=new PageInfo(lostMapper.queryByPage(keyword,startDate,endDate,rowBounds));
        return p;
    }

    /**
     * 平台查询失物招领
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<LostAdminVO> queryAllByPage(Integer pageNum, Integer pageSize, String keyword, Date startDate, Date endDate){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<LostAdminVO> p=new PageInfo(lostMapper.queryAllByPage(keyword,startDate,endDate,rowBounds));
        return p;
    }


    /**
     * 获取详情
     * @param id
     * @return
     */
    public LostUserVO getLostDetail(Integer id){
        return lostMapper.getLostDetail(id);
    }


    /**
     * 发布
     * @param lostVO
     * @return
     */
    public int postLost(LostVO lostVO){
        Lost lost=new Lost();
        BeanUtils.copyProperties(lostVO,lost);
        if(lostVO.getFile()!=null&&lostVO.getFile().trim().length()>0){
            String pictureName= UUID.randomUUID().toString();
            if(fileUploadUtil.upload(pictureName,lostVO.getFile())){
                lost.setLostPicture(pictureName);
            }
        }
        return lostMapper.insert(lost);
    }
}
