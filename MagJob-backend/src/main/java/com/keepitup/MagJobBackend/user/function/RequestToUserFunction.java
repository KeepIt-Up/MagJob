package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.PostUserRequest;
import com.keepitup.MagJobBackend.user.entity.User;

import java.math.BigInteger;

import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<BigInteger, PostUserRequest, User> {

    @Override
    public User apply(BigInteger id, PostUserRequest request) {
        return User.builder()
                .id(id)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }
}
