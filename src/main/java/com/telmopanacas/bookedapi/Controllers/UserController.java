package com.telmopanacas.bookedapi.Controllers;

import com.telmopanacas.bookedapi.DTOs.UserDTO;
import com.telmopanacas.bookedapi.Models.UserRequest;
import com.telmopanacas.bookedapi.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserDTO getUser(@RequestBody UserRequest userRequest) {
        return userService.getUserByEmail(userRequest.getEmail());
    }

    @GetMapping(path = "/{userId}/upvoted")
    public List<Integer> getUpvotedReviews(@PathVariable Integer userId) {
        return userService.getUserUpvotedReviews(userId);
    }
    @GetMapping(path = "/{userId}/downvoted")
    public List<Integer> getDownvotedReviews(@PathVariable Integer userId) {
        return userService.getUserDownvotedReviews(userId);
    }


}
