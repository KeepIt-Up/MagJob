package com.keepitup.magjobbackend.invitation.service.api;

import com.keepitup.magjobbackend.invitation.entity.Invitation;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Optional;

import java.util.List;

public interface InvitationService {
    Optional<Invitation> find(BigInteger id);

    Optional<List<Invitation>> findAllByUser(BigInteger userId);

    Optional<List<Invitation>> findAllByUserAndIsActive(BigInteger userId, Boolean isActive);

    Optional<List<Invitation>> findAllByOrganization(BigInteger organizationId);

    Optional<List<Invitation>> findAllByOrganizationAndIsActive(BigInteger organizationId, Boolean isActive);

    Optional<List<Invitation>> findAllByUserAndOrganization(BigInteger userId, BigInteger organizationId);

    List<Invitation> findAllByDateOfCreation(ZonedDateTime dateOfCreation);

    void create(Invitation invitation);

    Boolean checkIfActive(BigInteger id);

    void delete(BigInteger id);

    void update(Invitation invitation);
}
