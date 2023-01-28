package com.mapStruct.blogging.config;

import com.mapStruct.blogging.entity.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

import com.mapStruct.blogging.entity.User;

public class UserInfoUserDetail implements UserDetails {

    private String userName;
    private String password;
    private List<GrantedAuthority> authorityList;

    public UserInfoUserDetail(User user) {
        userName = user.getUserName();
        password = user.getPassword();
        authorityList = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
