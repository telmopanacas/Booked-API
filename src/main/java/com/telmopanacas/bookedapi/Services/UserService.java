package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.UserDTO;
import com.telmopanacas.bookedapi.Mappers.UserDTOMapper;
import com.telmopanacas.bookedapi.Security.User.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userDTOMapper)
                .orElseThrow(() -> new IllegalStateException("Utilizador com email "+ email + " não existe."));
    }
}
