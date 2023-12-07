package com.freshstratstore.freshstartstore.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.freshstratstore.freshstartstore.repesitory.UserRepository;
import com.freshstratstore.freshstartstore.services.userService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class userServiceImpl implements userService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return (UserDetailsService) new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findbyEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            }
        };
    };
}
