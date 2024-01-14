package com.keepitup.magjobbackend.invitation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class InvitationId implements Serializable {
    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "organization_id")
    private BigInteger organizationId;

}
