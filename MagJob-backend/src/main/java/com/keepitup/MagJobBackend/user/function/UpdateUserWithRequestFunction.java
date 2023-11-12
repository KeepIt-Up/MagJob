package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.PatchUserRequest;
import com.keepitup.MagJobBackend.user.entity.User;

import java.util.function.BiFunction;

public class UpdateUserWithRequestFunction implements BiFunction<User, PatchUserRequest, User> {

    @Override
    public User apply(User entity, PatchUserRequest request) {
        return User.builder()
                .id(entity.getId())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .password(entity.getPassword())
                .birthDate(entity.getBirthDate())
                .build();
    }
}
