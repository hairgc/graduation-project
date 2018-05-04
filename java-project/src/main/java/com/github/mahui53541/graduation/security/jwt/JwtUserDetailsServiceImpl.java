package com.github.mahui53541.graduation.security.jwt;

import com.github.mahui53541.graduation.mapper.AdminMapper;
import com.github.mahui53541.graduation.mapper.UserMapper;
import com.github.mahui53541.graduation.model.Admin;
import com.github.mahui53541.graduation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户鉴权(jwt)
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 14:17
 * @Version: 1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtUserFactory jwtUserFactory;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if("1".equals(s.split("#")[1])){
            //普通用户
            User user=new User();
            user.setUsername(s.split("#")[0]);
            user=userMapper.selectOne(user);
            if(user==null)
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
            else
                return jwtUserFactory.create(user);
        }else if("2".equals(s.split("#")[1])){
            //管理员
            Admin admin=new Admin();
            admin.setUsername(s.split("#")[0]);
            admin=adminMapper.selectOne(admin);
            if(admin==null)
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
            else
                return jwtUserFactory.create(admin);
        }else{
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        }
    }
}
