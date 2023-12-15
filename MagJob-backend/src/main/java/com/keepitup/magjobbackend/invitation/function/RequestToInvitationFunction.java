package com.keepitup.magjobbackend.invitation.function;

import com.keepitup.magjobbackend.invitation.dto.PostInvitationRequest;
import com.keepitup.magjobbackend.invitation.entity.Invitation;
import com.keepitup.magjobbackend.invitation.entity.InvitationId;
import com.keepitup.magjobbackend.organization.entity.Organization;
import com.keepitup.magjobbackend.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RequestToInvitationFunction implements Function<PostInvitationRequest, Invitation> {
    @Override
    public Invitation apply(PostInvitationRequest request) {
        return Invitation.builder()
                .id(InvitationId.builder()
                        .organizationId(request.getOrganization())
                        .userId(request.getUser())
                        .build())
                .user(User.builder()
                        .id(request.getUser())
                        .build())
                .organization(Organization.builder()
                        .id(request.getOrganization())
                        .build())
                .build();
    }
}
