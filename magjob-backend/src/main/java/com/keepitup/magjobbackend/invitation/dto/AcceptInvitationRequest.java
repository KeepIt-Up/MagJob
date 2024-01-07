package com.keepitup.magjobbackend.invitation.dto;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class AcceptInvitationRequest {

    private BigInteger organization;
    private BigInteger user;

    private String pseudonym;

}
