package com.keepitup.magjobbackend.member.repository.api;

import com.keepitup.magjobbackend.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, BigInteger> {
    List<Member> findAllByPseudonym(String pseudonym);
}
