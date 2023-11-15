package com.keepitup.MagJobBackend.user.controller.api;

import com.keepitup.MagJobBackend.user.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

public interface UserController {


    @GetMapping("api/users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetUsersResponse getUsers();

    @GetMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetUserResponse getUser(
            @PathVariable("id")
            BigInteger id
    );

    @PostMapping("/api/users/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    void login(
            @RequestBody
            LoginUserRequest loginUserRequest
    );

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    void createUser(
            @RequestBody
            PostUserRequest postUserRequest
    );

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(
            @PathVariable("id")
            BigInteger id
    );

    @PatchMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    void updateUser(
            @PathVariable("id")
            BigInteger id,
            @RequestBody
            PatchUserRequest patchUserRequest
    );

    @PutMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateUserPassword(
            @PathVariable("id")
            BigInteger id,
            @RequestBody
            PutPasswordRequest putPasswordRequest
    );

}
