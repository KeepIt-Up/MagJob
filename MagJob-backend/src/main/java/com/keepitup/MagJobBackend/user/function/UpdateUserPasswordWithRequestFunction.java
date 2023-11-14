package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.PutPasswordRequest;
import com.keepitup.MagJobBackend.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
@Component
public class UpdateUserPasswordWithRequestFunction implements BiFunction<User, PutPasswordRequest, User> {
    @Override
    public User apply(User entity, PutPasswordRequest request) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phoneNumber(entity.getPhoneNumber())
                .birthDate(entity.getBirthDate())
                .password(request.getPassword())
                .build();
    }
}
