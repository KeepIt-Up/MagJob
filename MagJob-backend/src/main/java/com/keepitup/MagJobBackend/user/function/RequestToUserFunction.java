package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.PostUserRequest;
import com.keepitup.MagJobBackend.user.entity.User;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

import java.util.function.BiFunction;
@Component
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
                .birthDate(request.getBirthDate())
                .build();
    }
}
