package com.keepitup.magjobbackend.member.repository.api;

import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.organization.entity.Organization;
import com.keepitup.magjobbackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, BigInteger> {
    List<Member> findAllByPseudonym(String pseudonym);
    List<Member> findAllByOrganization(Organization organization);
    List<Member> findAllByUser(User user);
}
