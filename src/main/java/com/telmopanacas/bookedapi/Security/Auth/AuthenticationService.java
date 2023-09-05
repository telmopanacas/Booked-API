package com.telmopanacas.bookedapi.Security.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telmopanacas.bookedapi.Security.Configuration.JwtService;
import com.telmopanacas.bookedapi.Security.User.Role;
import com.telmopanacas.bookedapi.Security.User.User;
import com.telmopanacas.bookedapi.Security.User.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    public AuthenticationResponse register(RegisterRequest request) {
        User user = new User(request.getDisplayName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), Role.USER);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        /*
        Se chegarmos aqui então é porque o authenticate em cima funcionou e o user está autenticado.
         */
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader("Authorization");
        final String refreshToken;
        final String userEmail;
        if( authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        // Vamos ver se o userEmail não é null e se o utilizador já se encontra autenticado ou não
        if( userEmail != null ) {
            UserDetails userDetails = userRepository.findByEmail(userEmail).orElseThrow(
                    () -> new UsernameNotFoundException("User not found while refreshing token")
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
                response.getWriter().write("Refresh token expired");
            }
        }
    }
}
