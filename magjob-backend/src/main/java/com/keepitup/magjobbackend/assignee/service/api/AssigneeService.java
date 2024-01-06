package com.keepitup.magjobbackend.assignee.service.api;

import com.keepitup.magjobbackend.assignee.entity.Assignee;
import com.keepitup.magjobbackend.assignee.entity.AssigneeId;
import com.keepitup.magjobbackend.member.entity.Member;
import com.keepitup.magjobbackend.task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface AssigneeService {
    List<Assignee> findAll();

    Optional<Assignee> find(AssigneeId id);

    List<Assignee> findAllByMember(Member member);

    List<Assignee> findAllByTask(Task task);

    void create(Assignee assignee);

    void delete(AssigneeId id);

    void update(Assignee assignee);
}
