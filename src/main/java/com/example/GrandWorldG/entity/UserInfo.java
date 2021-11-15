package com.example.GrandWorldG.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 * User info entity that is used for security authentication.
 *
 * @since 11/12/2021
 * @author Hobbs.Heting.Zhao
 */
@Getter
@Setter
public class UserInfo implements UserDetails {

    private Long userId;

    private String username;

    private String password;

    private Timestamp createTime;

    private Timestamp lastUpdateTime;

    private String domainUsername;

    private String socialSecurityNumber;

    //Role name in database must start with "ROLE_" to conform spring security standard.
    private String roleName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(this.getRoleName()));
        return authorityList;
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
