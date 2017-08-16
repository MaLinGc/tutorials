package com.ml.security;

import com.ml.models.UserInfo;
import com.ml.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug(" ---------------  MyUserDetailService loadUserByUsername--------------- ");
        log.debug(" username : {} ", username);
        UserInfo user = userService.getUserByName(username);
        if (user == null) {
            log.info("User not found!");
            throw new UsernameNotFoundException("Username not found");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, getGrantedAuthorities(user));
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserInfo user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user.getUsername().equals("admin"))
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        else
            authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}
