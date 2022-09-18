package com.wellware.osara.config.security;

import com.wellware.osara.data.entities.Authorities;
import com.wellware.osara.data.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails(User user){
        super(user);
    }


    @Override
    public Set<Authorities> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
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
