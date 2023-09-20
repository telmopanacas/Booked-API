package com.telmopanacas.bookedapi.Security.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Security.Configuration.JwtService;
import com.telmopanacas.bookedapi.Security.User.Role;
import com.telmopanacas.bookedapi.Security.User.User;
import com.telmopanacas.bookedapi.Security.User.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request, HttpServletResponse response) {
        if ( userRepository.findByDisplayName(request.getDisplayName()).isPresent()) {
            throw new ApiRequestException("Username already in use.");
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ApiRequestException("Email already in use.");
        }

        User user = new User(request.getDisplayName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(604800); // 7 days in seconds
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage());
        }
        /*
        Se chegarmos aqui então é porque o authenticate em cima funcionou e o user está autenticado.
         */
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(604800); // 7 days in seconds
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader("Cookie");
        final String refreshToken;
        final String userEmail;
        if( authHeader == null || !authHeader.startsWith("refresh_token=")) {
            throw new ApiRequestException("Cookie with refresh_token not found.");
        }
        refreshToken = authHeader.substring(14);
        userEmail = jwtService.extractUsername(refreshToken);

        // Vamos ver se o userEmail não é null e se o utilizador já se encontra autenticado ou não
        if( userEmail != null ) {
            UserDetails userDetails = userRepository.findByEmail(userEmail).orElseThrow(
                    () -> new UsernameNotFoundException("User not found while refreshing token.")
            );

            // Se o token for válido, criamos outro access token
            if(jwtService.isTokenValid(refreshToken, userDetails)) {
                var accesToken = jwtService.generateToken(userDetails);
                AuthenticationResponse authenticationResponse = new AuthenticationResponse(
                        accesToken,
                        refreshToken
                );
                new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Refresh token expired.");
            }
        }
    }
}
