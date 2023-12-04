package com.keepitup.magjobbackend.invitation.function;

import com.keepitup.magjobbackend.invitation.dto.GetInvitationsResponse;
import com.keepitup.magjobbackend.invitation.entity.Invitation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class InvitationsToResponseFunction implements Function<List<Invitation>, GetInvitationsResponse> {
    @Override
    public GetInvitationsResponse apply(List<Invitation> entities) {
        return GetInvitationsResponse.builder()
                .invitations(entities.stream()
                        .map(invitation -> GetInvitationsResponse.Invitation.builder()
                                .id(invitation.getId())
                                .build())
                        .toList())
                .build();
    }
}
