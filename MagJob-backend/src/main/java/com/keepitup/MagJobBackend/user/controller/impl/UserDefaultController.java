package com.keepitup.MagJobBackend.user.controller.impl;

import com.keepitup.MagJobBackend.user.controller.api.UserController;
import com.keepitup.MagJobBackend.user.dto.*;
import com.keepitup.MagJobBackend.user.entity.User;
import com.keepitup.MagJobBackend.user.function.*;
import com.keepitup.MagJobBackend.user.service.api.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

@RestController
@Log
public class UserDefaultController implements UserController {
    private final UserService service;
    private final UserToResponseFunction userToResponse;
    private final UsersToResponseFunction usersToResponse;
    private final RequestToUserFunction requestToUser;
    private final UpdateUserWithRequestFunction updateUserWithRequest;
    private final UpdateUserPasswordWithRequestFunction updateUserPasswordWithRequestFunction;

    @Autowired
    public UserDefaultController(
            UserService service,
            UserToResponseFunction userToResponse,
            UsersToResponseFunction usersToResponse,
            RequestToUserFunction requestToUser,
            UpdateUserWithRequestFunction updateUserWithRequest,
            UpdateUserPasswordWithRequestFunction updateUserPasswordWithRequestFunction
    ) {
        this.service = service;
        this.userToResponse = userToResponse;
        this.usersToResponse = usersToResponse;
        this.requestToUser = requestToUser;
        this.updateUserWithRequest = updateUserWithRequest;
        this.updateUserPasswordWithRequestFunction = updateUserPasswordWithRequestFunction;
    }

    @Override
    public GetUsersResponse getUsers() {
        return usersToResponse.apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(BigInteger id) {
        return service.find(id)
                .map(userToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void login(LoginUserRequest loginUserRequest) {
        if (service.authenticate(loginUserRequest.getEmail(), loginUserRequest.getPassword())) {
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void createUser(PostUserRequest postUserRequest) {
        service.register(requestToUser.apply(postUserRequest));
    }

    @Override
    public void deleteUser(BigInteger id) {
        service.find(id)
                .ifPresentOrElse(
                        profession -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @Override
    public void updateUser(BigInteger id, PatchUserRequest patchUserRequest) {
        User user = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        service.update(updateUserWithRequest.apply(user, patchUserRequest));
    }

    public void updateUserPassword(BigInteger id, PutPasswordRequest putPasswordRequest) {
        User user = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        service.update(updateUserPasswordWithRequestFunction.apply(user, putPasswordRequest));
    }
}
