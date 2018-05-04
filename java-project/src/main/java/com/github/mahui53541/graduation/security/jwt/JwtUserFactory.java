package com.github.mahui53541.graduation.security.jwt;

import com.github.mahui53541.graduation.mapper.RoleMapper;
import com.github.mahui53541.graduation.model.Admin;
import com.github.mahui53541.graduation.model.Role;
import com.github.mahui53541.graduation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 14:21
 * @Version: 1.0
 */
@Component
public class JwtUserFactory {
    @Autowired
    private RoleMapper roleMapper;
    public JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getSex(),
                user.getPhoneNumber(),
                user.getNickname(),
                user.getDeleted(),
                mapToGrantedAuthorities(user.getId(),false)
        );
    }

    public JwtUser create(Admin admin) {
        return new JwtUser(
                admin.getId(),
                admin.getUsername(),
                admin.getPassword(),
                admin.getSex(),
                admin.getPhoneNumber(),
                admin.getNickname(),
                admin.getDeleted(),
                mapToGrantedAuthorities(admin.getId(),true)
        );
    }
    private List<GrantedAuthority> mapToGrantedAuthorities(Integer userId,Boolean isAdmin) {
        List<Role> roles;
        if(isAdmin){
            roles=roleMapper.getByAdminId(userId);
        }else{
            roles=roleMapper.getByUserId(userId);
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(Role role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleSn()));
        }
        return grantedAuthorities;
    }
}
