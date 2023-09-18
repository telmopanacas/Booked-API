package com.telmopanacas.bookedapi.Controllers;

import com.telmopanacas.bookedapi.DTOs.UserDTO;
import com.telmopanacas.bookedapi.Models.UserRequest;
import com.telmopanacas.bookedapi.Services.UserService;
import org.springframework.web.bind.annotation.*;

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
}
