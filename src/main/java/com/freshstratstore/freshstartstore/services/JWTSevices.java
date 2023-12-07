package com.freshstratstore.freshstartstore.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTSevices {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails userDetails);
}
