package com.keepitup.magjobbackend.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class AuthenticationResponse {
    private String jwt;
    private GetUserResponse user;
}
