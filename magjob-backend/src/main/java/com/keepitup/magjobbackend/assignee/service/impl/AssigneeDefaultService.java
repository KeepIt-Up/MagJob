package com.keepitup.magjobbackend.assignee.service.impl;

import com.keepitup.magjobbackend.assignee.entity.Assignee;
import com.keepitup.magjobbackend.assignee.entity.AssigneeId;
import com.keepitup.magjobbackend.assignee.repository.api.AssigneeRepository;
import com.keepitup.magjobbackend.assignee.service.api.AssigneeService;
import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.task.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssigneeDefaultService implements AssigneeService {
    private final AssigneeRepository assigneeRepository;

    @Autowired
    public AssigneeDefaultService(AssigneeRepository assigneeRepository) {
        this.assigneeRepository = assigneeRepository;
    }


    @Override
    public List<Assignee> findAll() {
        return assigneeRepository.findAll();
    }

    @Override
    public Optional<Assignee> find(AssigneeId id) {
        return assigneeRepository.findById(id);
    }

    @Override
    public List<Assignee> findAllByMember(Member member) {
        return assigneeRepository.findAllByMember(member);
    }

    @Override
    public List<Assignee> findAllByTask(Task task) {
        return assigneeRepository.findAllByTask(task);
    }

    @Override
    public void create(Assignee assignee) {
        assigneeRepository.save(assignee);
    }

    @Override
    public void delete(AssigneeId id) {
        assigneeRepository.findById(id).ifPresent(assigneeRepository::delete);
    }

    @Override
    public void update(Assignee assignee) {
        assigneeRepository.save(assignee);
    }
}
