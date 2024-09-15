package com.interview.tao.controller;

import com.interview.tao.common.UserNotFoundException;
import com.interview.tao.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> userLogin(@RequestBody UserRequest userRequest) throws UserNotFoundException {
        UserResponse response = userService.verifyUser(userRequest.username(), userRequest.password());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID userId) throws UserNotFoundException
    {
        UserResponse userResponse = userService.getUserResponse(userId);
        return ResponseEntity.ok(userResponse);
    }

}
