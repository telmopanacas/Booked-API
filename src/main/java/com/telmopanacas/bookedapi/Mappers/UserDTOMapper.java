package com.telmopanacas.bookedapi.Mappers;

import com.telmopanacas.bookedapi.DTOs.UserDTO;
import com.telmopanacas.bookedapi.Security.User.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getDisplayName(),
                user.getEmail()
        );
    }
}
