package com.keepitup.MagJobBackend.user.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    private BigInteger id;

    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private LocalDate birthDate;
}
