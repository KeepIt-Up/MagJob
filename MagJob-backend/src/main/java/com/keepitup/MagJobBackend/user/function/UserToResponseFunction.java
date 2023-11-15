package com.keepitup.MagJobBackend.user.function;

import com.keepitup.MagJobBackend.user.dto.GetUserResponse;
import com.keepitup.MagJobBackend.user.entity.User;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToResponseFunction implements Function<User, GetUserResponse> {

    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .build();
    }
}
