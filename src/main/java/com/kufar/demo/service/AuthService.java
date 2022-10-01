package com.kufar.demo.service;

import com.kufar.demo.entity.JWTAuthentication;
import com.kufar.demo.entity.JWTRequest;
import com.kufar.demo.entity.JWTResponse;
import com.kufar.demo.entity.User;
import com.kufar.demo.repo.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//todo what is AuthException and why I use BadCredentialsException?
@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private JWTProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();

    public JWTResponse login(@NonNull JWTRequest authRequest) {
        User user = userRepository.findByLogin(authRequest.getLogin());
        if (user == null) {
            throw new BadCredentialsException("Unknown user login " + authRequest.getLogin());
        }
        if (user.getPassword().equals(authRequest.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshToken);
            return new JWTResponse(accessToken, refreshToken, user.getId());
        } else {
            throw new BadCredentialsException("Incorrect password");
        }
    }

    /*public JWTResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String login = claims.getSubject();
            String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                User user = userService.getByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JWTResponse(accessToken, null);
            }
        }
        return new JWTResponse(null, null);
    }*/

    public JWTResponse refresh(@NonNull String refreshToken) {
        boolean validated = jwtProvider.validateRefreshToken(refreshToken);
        if (validated) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByLogin(login);
                if (user == null) {
                    throw new BadCredentialsException("Unknown user login " + login);
                }
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JWTResponse(accessToken, newRefreshToken, user.getId());
            }
        }
        throw new BadCredentialsException("Invalid JWT token");
    }

    public JWTAuthentication getAuthInfo() {
        return (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}

