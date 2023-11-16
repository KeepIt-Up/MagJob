package com.keepitup.magjobbackend.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
