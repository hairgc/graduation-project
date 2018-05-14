package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.mapper.UserFoundMapper;
import com.github.mahui53541.graduation.model.Found;
import com.github.mahui53541.graduation.model.Lost;
import com.github.mahui53541.graduation.model.UserFound;
import com.github.mahui53541.graduation.model.UserLost;
import com.github.mahui53541.graduation.vo.MessageVO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:09
 * @Version: 1.0
 */
@Service("userFoundService")
public class UserFoundService extends BaseService<UserFoundMapper,UserFound> {
    public List<MessageVO> getMessage(Boolean isRead, Integer userId){
        return userFoundMapper.getMessage(isRead,userId);
    }

    /**
     * 确认
     * @param messageId
     * @param type
     * @return
     */
    public int confirmMessage(int messageId,int type){
        if(type==1){
            UserFound userFound=userFoundMapper.selectByPrimaryKey(messageId);
            userFound.setIsConfirm(true);
            Found found=foundMapper.selectByPrimaryKey(userFound.getFoundId());
            found.setFoundLostDatetime(new Date());
            found.setIsFound(true);
            found.setLostId(userFound.getUserId());
            foundMapper.updateByPrimaryKey(found);
            return userFoundMapper.updateByPrimaryKey(userFound);
        }else if(type==2){
            UserLost userLost=userLostMapper.selectByPrimaryKey(messageId);
            userLost.setIsConfirm(true);
            Lost lost=lostMapper.selectByPrimaryKey(userLost.getLostId());
            lost.setFoundDatetime(new Date());
            lost.setIsFound(true);
            lost.setFoundId(userLost.getUserId());
            lostMapper.updateByPrimaryKey(lost);
            return userLostMapper.updateByPrimaryKey(userLost);
        }
        return -1;
    }
    /**
     * 标记为已读
     * @param messageId
     * @param type
     * @return
     */
    public int markMessage(int messageId,int type){
        if(type==1){
            UserFound userFound=userFoundMapper.selectByPrimaryKey(messageId);
            userFound.setIsRead(true);
            return userFoundMapper.updateByPrimaryKey(userFound);
        }else if(type==2){
            UserLost userLost=userLostMapper.selectByPrimaryKey(messageId);
            userLost.setIsRead(true);
            return userLostMapper.updateByPrimaryKey(userLost);
        }
        return -1;
    }
}
