package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.PostUserRequest;
import com.keepitup.MagJobBackend.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestToUserFunction implements Function<PostUserRequest, User> {

    @Override
    public User apply(PostUserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .build();
    }
}
