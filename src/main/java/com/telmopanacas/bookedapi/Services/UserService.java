package com.telmopanacas.bookedapi.Services;

import com.telmopanacas.bookedapi.DTOs.UserDTO;
import com.telmopanacas.bookedapi.Exceptions.ApiRequestException;
import com.telmopanacas.bookedapi.Mappers.UserDTOMapper;
import com.telmopanacas.bookedapi.Models.Avaliacao;
import com.telmopanacas.bookedapi.Security.User.User;
import com.telmopanacas.bookedapi.Security.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                .orElseThrow(() -> new ApiRequestException("User with email "+ email + " doesn't exist."));
    }

    public List<Integer> getUserUpvotedReviews(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("Invalid user id."));
        return user.getUpvotedAvaliacoesIds();
    }
    public List<Integer> getUserDownvotedReviews(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiRequestException("Invalid user id."));
        return user.getDownvotedAvaliacoesIds();
    }
}
