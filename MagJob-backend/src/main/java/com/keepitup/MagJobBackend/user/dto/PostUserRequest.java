package com.keepitup.MagJobBackend.user.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    private LocalDate birthDate;
}
