package com.keepitup.magjobbackend.member.controller.impl;

import com.keepitup.magjobbackend.member.controller.api.MemberController;
import com.keepitup.magjobbackend.member.dto.*;
import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.member.function.*;
import com.keepitup.magjobbackend.member.service.api.MemberService;
import com.keepitup.magjobbackend.organization.entity.Organization;
import com.keepitup.magjobbackend.organization.service.api.OrganizationService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@Log
public class MemberDefaultController implements MemberController {
    private final MemberService service;
    private final OrganizationService organizationService;
    private final MembersToResponseFunction membersToResponse;
    private final MemberToResponseFunction memberToResponse;
    private final RequestToMemberFunction requestToMember;
    private final UpdateMemberWithRequestFunction updateMemberWithRequest;

    @Autowired
    public MemberDefaultController(
            MemberService service,
            OrganizationService organizationService,
            MembersToResponseFunction membersToResponse,
            MemberToResponseFunction memberToResponse,
            RequestToMemberFunction requestToMember,
            UpdateMemberWithRequestFunction updateMemberWithRequest
    ) {
        this.service = service;
        this.organizationService = organizationService;
        this.membersToResponse = membersToResponse;
        this.memberToResponse = memberToResponse;
        this.requestToMember = requestToMember;
        this.updateMemberWithRequest = updateMemberWithRequest;
    }

    @Override
    public GetMembersResponse getMembers() {
        return membersToResponse.apply(service.findAllByIsStillMember(true));
    }

    @Override
    public GetMembersResponse getMembersByOrganization(BigInteger organizationId) {
        Optional<Organization> organizationOptional = organizationService.find(organizationId);

        Organization organization = organizationOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return membersToResponse.apply(service.findAllByOrganization(organization));
    }

    @Override
    public GetMemberResponse getMember(BigInteger id) {
        return service.findByIdAndIsStillMember(id, true)
                .map(memberToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetMemberResponse createMember(PostMemberRequest postMemberRequest) {
        Optional<List<Organization>> organizations = service.findAllOrganizationsByUser(postMemberRequest.getUser());
        Optional<Organization>  organization = organizationService.find(postMemberRequest.getOrganization());

        if (organization.isPresent() && organizations.isPresent()
                && organizations.get().contains(organization.get())
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            service.create(requestToMember.apply(postMemberRequest));
        }
        return service.findByUserId(postMemberRequest.getUser())
                .map(memberToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteMember(BigInteger id) {
        service.findByIdAndIsStillMember(id, true)
                .ifPresentOrElse(
                        member -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @Override
    public GetMemberResponse updateMember(BigInteger id, PatchMemberRequest patchMemberRequest) {
        service.findByIdAndIsStillMember(id, true)
                .ifPresentOrElse(
                        member -> service.update(updateMemberWithRequest.apply(member, patchMemberRequest)),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
        return getMember(id);
    }
}

