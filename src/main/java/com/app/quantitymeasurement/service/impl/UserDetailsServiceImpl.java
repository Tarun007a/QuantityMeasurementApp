package com.app.quantitymeasurement.service.impl;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Trying login with username " + username);
        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
        log.info("user found!");
        return user;
    }
}

