package com.lds.authentication.security;

import com.lds.authentication.domain.UserModel;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String getEmailFromToken(String token);

    Date getExpirationFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(final UserDetails userDetails,
                         final String fullName,
                         final UserModel.UserRole role,
                         final String email);

    String createToken(Map<String, Object> claims, String subject);
}