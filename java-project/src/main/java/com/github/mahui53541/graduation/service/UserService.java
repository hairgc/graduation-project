package com.github.mahui53541.graduation.service;

import com.github.mahui53541.graduation.vo.UserPwdVO;
import com.github.pagehelper.PageHelper;
import com.github.mahui53541.graduation.mapper.UserMapper;
import com.github.mahui53541.graduation.model.User;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/4/29 20:03
 * @Version: 1.0
 */
@Service("userService")
public class UserService extends BaseService<UserMapper,User>{

    /**
     * 修改密码
     * @param userPwdVO
     * @return
     */
    public HashMap<String,Object> updatePwd(UserPwdVO userPwdVO) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        HashMap<String,Object> result=new HashMap<>();
        User user=userMapper.selectByPrimaryKey(userPwdVO.getId());
        if(encoder.matches(userPwdVO.getOldPass(),user.getPassword())){
            user.setPassword(encoder.encode(userPwdVO.getNewPass()));
            userMapper.updateByPrimaryKey(user);
            result.put("status","success");
        }else{
            result.put("status","error");
        }
        return result;
    }

    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<User> queryByPage(Integer pageNum, Integer pageSize, String keyword){
        PageRowBounds rowBounds=new PageRowBounds((pageNum-1)*pageSize,pageSize);
        PageInfo<User> p=new PageInfo(userMapper.queryByPage(keyword,rowBounds));
        return p;
    }

}
