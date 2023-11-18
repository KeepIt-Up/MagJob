package com.keepitup.magjobbackend.member.service.api;

import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.organization.entity.Organization;
import com.keepitup.magjobbackend.user.entity.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    List<Member> findAll();
    Optional<Member> find(BigInteger id);

    List<Member> findAllByPseudonym(String pseudonym);

    Boolean checkIfStillMember(BigInteger id);

    Optional<List<User>> findAllUsersByOrganization(BigInteger organizationId);

    Optional<List<Organization>> findAllOrganizationsByUser(BigInteger userId);

    void create(Member member);

    void delete(BigInteger id);

    void update(Member member);
}
