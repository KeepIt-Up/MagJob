package com.keepitup.magjobbackend.invitation.controller.impl;

import com.keepitup.magjobbackend.invitation.controller.api.InvitationController;
import com.keepitup.magjobbackend.invitation.dto.GetInvitationResponse;
import com.keepitup.magjobbackend.invitation.dto.GetInvitationsResponse;
import com.keepitup.magjobbackend.invitation.dto.PostInvitationRequest;
import com.keepitup.magjobbackend.invitation.entity.Invitation;
import com.keepitup.magjobbackend.invitation.entity.InvitationId;
import com.keepitup.magjobbackend.invitation.function.InvitationToResponseFunction;
import com.keepitup.magjobbackend.invitation.function.InvitationsToResponseFunction;
import com.keepitup.magjobbackend.invitation.function.RequestToInvitationFunction;
import com.keepitup.magjobbackend.invitation.service.api.InvitationService;
import com.keepitup.magjobbackend.user.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Log
public class InvitationDefaultController implements InvitationController {

    private final InvitationService service;
    private final InvitationsToResponseFunction invitationsToResponse;
    private final InvitationToResponseFunction invitationToResponse;
    private final RequestToInvitationFunction requestToInvitation;

    @Autowired
    public InvitationDefaultController(InvitationService service,
                                       InvitationsToResponseFunction invitationsToResponse,
                                       InvitationToResponseFunction invitationToResponse,
                                       RequestToInvitationFunction requestToInvitation
    ) {
        this.service = service;
        this.invitationsToResponse = invitationsToResponse;
        this.invitationToResponse = invitationToResponse;
        this.requestToInvitation = requestToInvitation;
    }


    @Override
    public GetInvitationResponse getInvitation(BigInteger userId, BigInteger organizationId) {
        return service.findByUserAndOrganization(userId, organizationId)
                .map(invitationToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetInvitationsResponse getInvitationsByUser(BigInteger userId) {
        return service.findAllByUserAndIsActive(userId, true)
                .map(invitationsToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetInvitationsResponse getInvitationsByOrganization(BigInteger organizationId) {
        return service.findAllByOrganizationAndIsActive(organizationId, true)
                .map(invitationsToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetInvitationResponse sendInvitation(PostInvitationRequest request) {
        Optional<Invitation> invitation = service.findByUserAndOrganization(request.getUser(), request.getOrganization());
        if (invitation.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        else {
            service.create(requestToInvitation.apply(request));
        }

        return service.findByUserAndOrganization(request.getUser(), request.getOrganization())
                .map(invitationToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteInvitation(BigInteger userId, BigInteger organizationId) {
        service.findByUserAndOrganization(userId, organizationId)
                .ifPresentOrElse(
                        invitation -> service.delete(userId, organizationId),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
