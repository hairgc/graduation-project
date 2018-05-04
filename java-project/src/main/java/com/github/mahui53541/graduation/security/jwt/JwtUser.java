package com.github.mahui53541.graduation.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: MaHui
 * @CreateDate: 2018/5/4 13:52
 * @Version: 1.0
 */
public class JwtUser implements UserDetails {

    private final Integer id;
    /**
     * 用户名（学号）
     */
    private final String username;

    /**
     * 密码
     */
    private final String password;

    /**
     * 0:男1：女
     */
    private final Boolean sex;

    /**
     * 手机
     */
    private final String phoneNumber;

    /**
     * 用户昵称
     */
    private final String nickname;

    /**
     * 是否删除
     */
    private final Boolean deleted;


    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Integer id, String username, String password, Boolean sex, String phoneNumber, String nickname, Boolean deleted, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.deleted = deleted;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return !deleted;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getSex() {
        return sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public Boolean getDeleted() {
        return deleted;
    }
}
