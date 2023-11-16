package com.keepitup.magjobbackend.member.service.impl;

import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.member.repository.api.MemberRepository;
import com.keepitup.magjobbackend.member.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class MemberDefaultService implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberDefaultService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> find(BigInteger id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findAllByPseudonym(String pseudonym) {
        return memberRepository.findAllByPseudonym(pseudonym);
    }

    @Override
    public Boolean checkIfStillMember(BigInteger id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.map(Member::getIsStillMember).orElse(null);
    }

    @Override
    public void create(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void delete(BigInteger id) {
        memberRepository.findById(id).ifPresent(memberRepository::delete);
    }

    @Override
    public void update(Member member) {
        memberRepository.save(member);
    }
}
