package com.keepitup.MagJobBackend.user.service.impl;

import com.keepitup.MagJobBackend.user.entity.User;
import com.keepitup.MagJobBackend.user.repository.api.UserRepository;
import com.keepitup.MagJobBackend.user.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class UserDefaultService implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDefaultService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> find(BigInteger id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> find(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return userRepository.findAllByFirstName(firstName);
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public List<User> findAllByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
    }

    @Override
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void delete(BigInteger id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
