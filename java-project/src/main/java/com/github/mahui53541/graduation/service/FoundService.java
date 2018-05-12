package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.FoundMapper;
import com.github.mahui53541.graduation.model.Found;
import com.github.mahui53541.graduation.util.FileUploadUtil;
import com.github.mahui53541.graduation.vo.FoundUserVO;
import com.github.mahui53541.graduation.vo.FoundVO;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private FileUploadUtil fileUploadUtil;
    /**
     * 查询失物招领
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Found> queryByPage(Integer pageNum, Integer pageSize,String keyword,Date startDate,Date endDate){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<Found> p=new PageInfo(foundMapper.queryByPage(keyword,startDate,endDate,rowBounds));
        return p;
    }

    /**
     * 获取详情
     * @param id
     * @return
     */
    public FoundUserVO getFoundDetail(Integer id){
        return foundMapper.getFoundDetail(id);
    }



    /**
     * 发布
     * @param foundVO
     * @return
     */
    public int postFound(FoundVO foundVO){
        Found found=new Found();
        BeanUtils.copyProperties(foundVO,found);
        if(foundVO.getFile()!=null&&foundVO.getFile().trim().length()>0){
            String pictureName=UUID.randomUUID().toString();
            if(fileUploadUtil.upload(pictureName,foundVO.getFile())){
                found.setFoundPicture(pictureName);
            }
        }
        return foundMapper.insert(found);
    }
}
