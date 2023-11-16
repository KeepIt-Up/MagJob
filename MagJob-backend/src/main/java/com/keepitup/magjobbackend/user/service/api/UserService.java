package com.keepitup.magjobbackend.user.service.api;

import com.keepitup.magjobbackend.user.entity.User;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> find(BigInteger id);

    Optional<User> find(String email);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);

    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    boolean authenticate(String email, String password);

    void register(User user);

    void delete(BigInteger id);

    void update(User user);
}
