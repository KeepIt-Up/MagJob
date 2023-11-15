package com.keepitup.MagJobBackend.user.dto;

import lombok.*;

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
    private String password;

}
