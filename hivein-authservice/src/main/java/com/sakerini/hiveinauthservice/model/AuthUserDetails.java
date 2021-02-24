package com.sakerini.hiveinauthservice.model;

import com.sakerini.hiveinauthservice.model.entity.Authority;
import com.sakerini.hiveinauthservice.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean active;
    private Set<GrantedAuthority> authorities;

    public AuthUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isActive();
        authorities = new HashSet<>();
        for (Authority authority : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getRole()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }
}
